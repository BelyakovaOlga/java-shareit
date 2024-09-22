package ru.practicum.shareit.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class BaseUserRepository implements UserRepository {
    final HashMap<Long, User> users;

    @Override
    public User create(User user) {
        user.setId(getId());
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public User update(long id, User userUpd) {
        User oldUser = users.get(id);
        if (userUpd.getEmail() != null && !userUpd.getEmail().equals(oldUser.getEmail())) {
            oldUser.setEmail(userUpd.getEmail());
        }
        if (userUpd.getName() != null && !userUpd.getName().equals(oldUser.getName())) {
            oldUser.setName(userUpd.getName());
        }

        users.put(id, oldUser);
        return users.get(id);
    }

    @Override
    public void delete(long id) {
        users.remove(id);
    }

    @Override
    public Optional<User> findById(long id) {
        User user = users.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public Collection<User> getListUserSameEmail(String email, long id) {
        Collection<User> listUser = users.values();
        Collection<User> resUser =
                listUser.stream()
                        .filter(user -> user.getEmail().equals(email) && user.getId() != id)
                        .toList();
        return resUser;
    }

    private long getId() {
        long lastId = users.values().stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0);
        return lastId + 1;
    }
}

