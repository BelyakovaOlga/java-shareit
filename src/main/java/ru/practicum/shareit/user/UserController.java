package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.services.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService service;


    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        log.info("Запрос User по id: {}", id);
        User user = service.findById(id);
        log.info("Возврат user: {}", user);
        return user;
    }

    @PostMapping()
    public User create(@RequestBody User user) {
        log.info("Создание User: {}", user);
        return service.create(user);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        log.info("Обновление User: {}", user);
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("Удалениее User по: {}", id);
        service.delete(id);
        log.info("Успешно удален");
    }
}

