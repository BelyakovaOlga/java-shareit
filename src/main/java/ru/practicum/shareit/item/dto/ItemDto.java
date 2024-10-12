package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.request.ItemRequest;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ItemDto {
    private  Long id;
    @NotBlank(message = "Наименование должен быть указано")
    private  String name;
    @NotBlank(message = "Описание должно быть указано")
    private  String description;
    @BooleanFlag
    @NotNull(message = "Доступность не может быть null")
    private  Boolean available;
    private ItemRequest request;
    private BookingDto lastBooking;
    private BookingDto nextBooking;
    private List<CommentDto> comments;
}


