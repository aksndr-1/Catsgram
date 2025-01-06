package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
public class Post {
    private Long id;
    private long authorId;
    private String description;
    private Instant postDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return authorId == post.authorId && Objects.equals(id, post.id) && Objects.equals(description, post.description) && Objects.equals(postDate, post.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, description, postDate);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", description='" + description + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}