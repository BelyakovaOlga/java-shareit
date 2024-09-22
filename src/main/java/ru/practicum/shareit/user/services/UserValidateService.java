package ru.practicum.shareit.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidatetionConflict;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserValidateService {
    private final UserRepository userRepository;

    public void validateData(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new ValidationException("Имя должен быть указано");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ValidationException("Имейл должен быть указан");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Некорректный имейл");
        }

    }

    public void existEmail(User user) {
        Collection<User> listUserSameEmail =  userRepository.getListUserSameEmail(user.getEmail(),user.getId());
        if (listUserSameEmail.size() != 0) {
            throw new ValidatetionConflict("Пользователь с таким email уже зарегестрирован");
        }
    }

    public void validateExistUser(long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Пользователь с {} не найден " + userId);
        }
    }
}
