package com.example.springrestclient.post;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class JdbcPostController {

    private final JdbcPostService jdbcPostService;

    public JdbcPostController(@Qualifier("jdbcClientPostService") JdbcPostService jdbcPostService) {
        this.jdbcPostService = jdbcPostService;
    }

    @GetMapping("")
    List<JdbcPost> findAll() {
        return jdbcPostService.findAll();
    }

    @GetMapping("/{id}")
    Optional<JdbcPost> findById(@PathVariable String id) {
        return jdbcPostService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody JdbcPost post) {
        jdbcPostService.create(post);
    }

    @PutMapping("/{id}")
    void update(@RequestBody JdbcPost post, @PathVariable String id) {
        jdbcPostService.update(post, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        jdbcPostService.delete(id);
    }
}
