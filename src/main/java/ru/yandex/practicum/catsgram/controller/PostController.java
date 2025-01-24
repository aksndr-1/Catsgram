package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.enums.SortOrder;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final static List<String> sorts = List.of("asc", "desc", "ascending", "descending");

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable long postId) {
        return postService.findById(postId);
    }

    @GetMapping
    public Collection<Post> findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        sort = sort.toLowerCase();
        if (!sorts.contains(sort)) {
            throw new ParameterNotValidException("sort", "sort must be one of " + sorts);
        }

        if (size <= 0) {
            throw new ParameterNotValidException("size", "size must be positive");
        }

        if (from < 0) {
            throw new ParameterNotValidException("from", "from must be positive");
        }

        return postService.findAll(size, from, SortOrder.from(sort));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}