package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Image {
    private Long id;
    private long postId;
    private String originalFileName;
    private String filePath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return postId == image.postId && Objects.equals(id, image.id) && Objects.equals(originalFileName, image.originalFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, originalFileName);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", postId=" + postId +
                ", originalFileName='" + originalFileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}