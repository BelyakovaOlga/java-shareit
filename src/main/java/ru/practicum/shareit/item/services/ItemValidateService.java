package ru.practicum.shareit.item.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidatetionConflict;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemValidateService {
    private final ItemRepository itemRepository;

    public void validateData(Item item) {
        if (item.getName() == null || item.getName().isBlank()) {
            throw new ValidationException("Наименование должен быть указано");
        }
        if (item.getDescription() == null || item.getDescription().isBlank()) {
            throw new ValidationException("Описание должен быть указано");
        }
        if (item.getAvailable() == null) {
            throw new ValidationException("Доступность Item должен быть указан");
        }
    }

    public void validateExistItem(long itemId) {
        if (itemRepository.findById(itemId).isEmpty()) {
            throw new NotFoundException("Item с {} не найден " + itemId);
        }

    }

    public void validatePermit(long itemId, long userId) {
        if (itemRepository.findById(itemId).get().getOwnerId() != userId) {
            throw new ValidatetionConflict("Только владельец может радектиорвать Item, пользователь {} не является владельцем" + userId);
        }
    }

    public void validateParms(long userId) {
        if (userId == 0) {
            throw new ValidationException("Доступность Item должен быть указан");
        }
    }
}

