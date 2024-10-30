package ru.practicum.shareit.request.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.dto.ItemForRequestDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestInfoDto {
    private Long id;
    private String description;
    private UserDto requestor;
    private LocalDateTime created;
    private List<ItemForRequestDto> items;
}
