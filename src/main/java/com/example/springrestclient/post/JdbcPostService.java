package com.example.springrestclient.post;

import java.util.List;
import java.util.Optional;

public interface JdbcPostService {
    List<JdbcPost> findAll();

    Optional<JdbcPost> findById(String id);

    void create(JdbcPost post);

    void update(JdbcPost post, String id);

    void delete(String id);

}
