package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentInfoDto {
    private String text;
    private Long authorId;
    private Long itemId;
    private LocalDateTime created;
}
