package com.mholodniuk.searchthedocs.management.room.dto;

import com.mholodniuk.searchthedocs.management.document.dto.DocumentDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record RoomResponse(
        Long id,
        String name,
        boolean isPrivate,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Collection<DocumentDTO> documents
) {
}
