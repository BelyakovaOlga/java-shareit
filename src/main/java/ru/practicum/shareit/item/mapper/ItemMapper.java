package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        Long requestId = item.getRequest();
        ItemDto itemDto = new ItemDto(item.getId(),
                                      item.getName(),
                                      item.getDescription(),
                                      item.getAvailable(),
                                      requestId);
        return itemDto;
    }
}
