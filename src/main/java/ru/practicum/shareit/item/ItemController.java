package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.services.ItemService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable long id) {
        log.info("Получение Item по id: {}", id);
        ItemDto item = service.findById(id);
        log.info("Возврат Item: {}", item);
        return item;
    }

    @GetMapping
    public Collection<ItemDto> getByOwnerId(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Получение Item по Владельцу: {}", userId);
        return service.findByOwner(userId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemBySearch(@RequestParam String text) {
        log.info("Получение Item по поиску со словом : {}", text);
        return service.findBySearch(text);
    }

    @PostMapping
    public Item create(@RequestHeader("X-Sharer-User-Id") long userId,
                       @RequestBody Item item) {
        log.info("Создание Item: {} с владельцем {}", item, userId);
        return service.create(item, userId);
    }

    @PatchMapping("/{itemId}")
    public Item update(@RequestHeader("X-Sharer-User-Id") long userId,
                       @PathVariable long itemId,
                       @RequestBody Item item) {
        log.info("Обновление Item: {} владельца {}", item, userId);
        return service.update(itemId, item, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
        log.info("Успешно удален Item по: {}", id);
    }
}

