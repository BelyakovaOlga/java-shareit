package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {
    Item create(Item item, long ownerId);

    Item update(long itemId,Item updItem,long userId);

    void delete(long id);

    Optional<Item> findById(long id);

    Collection<Item> findByOwner(long ownerId);

    Collection<Item> findBySearch(String text);
}
