package ru.practicum.shareit.request.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestAnswerDto {
    private String description;
    private LocalDateTime created;
    private List<ItemDto> items;
}
