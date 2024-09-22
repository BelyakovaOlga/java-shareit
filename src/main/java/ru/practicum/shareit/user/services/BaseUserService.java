package ru.practicum.shareit.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {
    private final UserRepository userRepository;
    private final UserValidateService validateService;

    @Override
    public User create(User newUser) {
        validateService.validateData(newUser);
        validateService.existEmail(newUser);
        return userRepository.create(newUser);
    }

    @Override
    public User update(long id,User userUpd) {
        validateService.existEmail(userUpd);
        validateService.validateExistUser(id);
        return userRepository.update(id, userUpd);
    }

    @Override
    public void delete(long id) {
         userRepository.delete(id);
    }

    @Override
    public User findById(long id) {
        validateService.validateExistUser(id);
        return userRepository.findById(id).get();
    }
}
