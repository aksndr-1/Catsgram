package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "email")
public class User {
    private Long id;
    @NonNull
    private String username;
    private String email;
    private String password;
    private Instant registrationDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}