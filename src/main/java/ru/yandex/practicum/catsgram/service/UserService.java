package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User create(User user) {
        // проверяем выполнение необходимых условий
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        if (users.values().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))){
            throw new DuplicatedDataException("Этот имейл уже используется");
        }

        // формируем дополнительные данные
        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());
        // сохраняем новую публикацию в памяти приложения
        users.put(user.getId(), user);
        return user;
    }

    public User update(User newUser) {
        // проверяем необходимые условия
        if (newUser.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        if (newUser.getEmail() == null || newUser.getUsername() == null || newUser.getPassword() == null){
            return newUser;
        }

        if (users.containsKey(newUser.getId())) {
            User oldUser = users.get(newUser.getId());
            if (newUser.getEmail() == null || newUser.getUsername().isBlank()) {
                throw new ConditionsNotMetException("Описание не может быть пустым");
            }

            if (users.values().stream()
                    .anyMatch(u -> u.getEmail().equals(newUser.getEmail()) && !Objects.equals(u.getId(), oldUser.getId()))){
                throw new DuplicatedDataException("Этот имейл уже используется");
            }

            // если публикация найдена и все условия соблюдены, обновляем её содержимое
            oldUser.setEmail(newUser.getEmail());
            return oldUser;
        }
        throw new NotFoundException("Пользователь с id = " + newUser.getId() + " не найден");
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}