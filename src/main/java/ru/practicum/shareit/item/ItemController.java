package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentInfoDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.services.ItemService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;
    static final String userParmHeader = "X-Sharer-User-Id";

    @GetMapping("/{id}")
    public ItemDto get(@RequestHeader(userParmHeader) long userId, @PathVariable long id) {
        log.info("==>Получение Item по id: {}", id);
        ItemDto itemDto = service.findById(id, userId);
        log.info("<==Возврат Item: {}", itemDto);
        return itemDto;
    }

    @GetMapping
    public Collection<ItemDto> getByOwnerId(@RequestHeader(userParmHeader) long userId) {
        log.info("==>Получение Item по Владельцу: {}", userId);
        Collection<ItemDto> itemsByOwner = service.findByOwner(userId);
        log.info("<==Получен Item по Владельцу: {}", userId);
        return itemsByOwner;
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemBySearch(@RequestParam String text) {
        log.info("==>Получение Item по поиску со словом : {}", text);
        Collection<ItemDto> itemsBySearch = service.findBySearch(text);
        log.info("==>Получен Item по поиску со словом : {}", text);
        return itemsBySearch;
    }

    @PostMapping
    public ItemDto create(@RequestHeader(userParmHeader) long userId,
                          @RequestBody @Valid ItemDto itemDto) {
        log.info("==>Создание Item: {} с владельцем {}", itemDto, userId);
        ItemDto newItemDto = service.create(itemDto, userId);
        log.info("<==Создан Item: {} с владельцем {}", newItemDto, userId);
        return newItemDto;
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader(userParmHeader) long userId,
                          @PathVariable long itemId,
                          @RequestBody ItemDto itemDto) {
        log.info("==>Обновление Item: {} владельца {}", itemDto, userId);
        itemDto.setId(itemId);
        ItemDto updItemDto = service.update(itemDto, userId);
        log.info("<==Обновлен Item: {} владельца {}", itemDto, userId);
        return updItemDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("==>Удаление Item по: {}", id);
        service.delete(id);
        log.info("<==Успешно удален Item по: {}", id);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@NotNull @RequestHeader(userParmHeader) long userId,
                                    @PathVariable long itemId,
                                    @RequestBody @Valid CommentInfoDto commentInfoDto) {
        log.info("==>Создание коментария к Item по: {}", itemId);
        CommentDto commentDto = service.createComment(itemId, userId, commentInfoDto);
        log.info("==>Создан коментарий к Item по: {}", itemId);
        return commentDto;
    }
}


