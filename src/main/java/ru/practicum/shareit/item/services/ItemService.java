package ru.practicum.shareit.item.services;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

@Service
public interface ItemService {
    Item create(Item item, long ownerId);

    Item update(long id, Item user,long userId);

    void delete(long id);

    ItemDto findById(long id);

    Collection<ItemDto> findByOwner(long ownerId);

    Collection<ItemDto> findBySearch(String text);
}
