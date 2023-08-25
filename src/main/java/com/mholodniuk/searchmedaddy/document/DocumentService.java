package com.mholodniuk.searchmedaddy.document;

import com.mholodniuk.searchmedaddy.document.dto.PhraseSearchResponse;
import com.mholodniuk.searchmedaddy.document.extract.ContentExtractor;
import com.mholodniuk.searchmedaddy.document.mapper.SearchResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {
    private final SearchService searchService;
    private final Map<String, ContentExtractor> contentExtractors;
    private final DocumentRepository documentRepository;

    public String indexDocument(byte[] file, String contentType, String filename) {
        var contentExtractor = contentExtractors.get(contentType);
        var content = contentExtractor.extract(file);

        var documents = IntStream.range(0, content.size())
                .mapToObj(pageIdx -> new SearchableDocument(filename, content.get(pageIdx), pageIdx + 1))
                .peek(document -> log.debug("Indexing page {} with content: {}", document.getPage(), document.getText()))
                .toList();

        documentRepository.saveAll(documents);
        log.info("Indexed {} page(s) of a file: {}", documents.size(), filename);

        return "Created";
    }

    public Optional<PhraseSearchResponse> searchDocument(String phrase) {
        try {
            var response = searchService.searchDocumentsByPhrase(phrase);
            var searchResponse = SearchResponseMapper.mapToDto(response);

            return Stream.of(searchResponse)
                    .filter(result -> result.hits().size() > 0)
                    .findAny();
        } catch (IOException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}