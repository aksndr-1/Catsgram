package ru.yandex.practicum.catsgram.comparator;

import ru.yandex.practicum.catsgram.enums.SortOrder;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.Comparator;

public class PostsComparator implements Comparator<Post> {

    private final SortOrder sortOrder;

    public PostsComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Post a, Post b) {
        if (a.getPostDate().isBefore(b.getPostDate())) {
            return sortOrder == SortOrder.ASCENDING ? -1 : 1;
        } else if (a.getPostDate().isAfter(b.getPostDate())) {
            return sortOrder == SortOrder.ASCENDING ? 1 : -1;
        } else {
            return 0;
        }
    }
}
