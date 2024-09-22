package ru.practicum.shareit.item.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.services.UserValidateService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BaseItemService implements  ItemService {
    private final ItemRepository itemRepository;
    private final ItemValidateService itemValidateService;
    private final UserValidateService userValidateService;

    @Override
    public Item create(Item newItem, long ownerId) {
        userValidateService.validateExistUser(ownerId);
        itemValidateService.validateData(newItem);
        return itemRepository.create(newItem, ownerId);
    }

    @Override
    public Item update(long itemId, Item itemUpd, long userId) {
        itemValidateService.validateParms(userId);
        userValidateService.validateExistUser(userId);
        itemValidateService.validateExistItem(itemId);

        Item itemOld = itemRepository.findById(itemId).get();
        return itemRepository.update(itemId, itemUpd, userId);
    }

    @Override
    public void delete(long id) {
        itemRepository.delete(id);
    }

    @Override
    public ItemDto findById(long id) {
        itemValidateService.validateExistItem(id);
        return ItemMapper.toItemDto(itemRepository.findById(id).get());
    }

    @Override
    public Collection<ItemDto> findByOwner(long ownerId) {
        userValidateService.validateExistUser(ownerId);
        return itemRepository.findByOwner(ownerId)
                .stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    @Override
    public Collection<ItemDto> findBySearch(String text) {

        return itemRepository.findBySearch(text)
                .stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }
}


