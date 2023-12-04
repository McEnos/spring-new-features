package com.example.springrestclient.client;

import com.example.springrestclient.post.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface JsonPlaceHolderService {
    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post findById(@PathVariable  Integer id);

    @PostExchange("/posts")
    Post save(Post post);

    @DeleteExchange("/posts/{id}")
    void deleteById(@PathVariable Integer id);
}
