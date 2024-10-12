package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;

@Builder
@Data
public class BookingApproveDto {
    @NotNull
    private Long id;
    private Item item;
    private Boolean approved;
}
