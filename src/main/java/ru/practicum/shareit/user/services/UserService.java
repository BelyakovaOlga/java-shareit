package ru.practicum.shareit.user.services;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;

@Service
public interface UserService {

    User create(User user);

    User update(long id, User user);

    void delete(long id);

    User findById(long id);
}
