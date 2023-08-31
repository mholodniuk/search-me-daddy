package com.mholodniuk.searchthedocs.management.room.mapper;

import com.mholodniuk.searchthedocs.management.document.mapper.DocumentMapper;
import com.mholodniuk.searchthedocs.management.room.Room;
import com.mholodniuk.searchthedocs.management.room.dto.CreateRoomRequest;
import com.mholodniuk.searchthedocs.management.room.dto.RoomDTO;
import com.mholodniuk.searchthedocs.management.room.dto.RoomResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomMapper {
    public static Room fromRequest(CreateRoomRequest createRoomRequest) {
        var room = new Room();
        room.setName(createRoomRequest.name());
        room.setIsPrivate(createRoomRequest.isPrivate());
        return room;
    }

    public static RoomDTO toDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .isPrivate(room.getIsPrivate())
                .createdAt(room.getCreatedAt())
                .modifiedAt(room.getModifiedAt())
                .build();
    }

    public static List<RoomDTO> toDTO(Collection<Room> rooms) {
        return rooms.stream().map(RoomMapper::toDTO).toList();
    }

    public static RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .isPrivate(room.getIsPrivate())
                .createdAt(room.getCreatedAt())
                .modifiedAt(room.getModifiedAt())
                .documents(DocumentMapper.toDTO(room.getDocuments()))
                .build();
    }
}
