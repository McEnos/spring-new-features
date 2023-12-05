package com.example.springrestclient.post;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JdbcClientPostService implements  JdbcPostService{
    private final JdbcClient jdbcClient;


    @Override
    public List<JdbcPost> findAll() {
        return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post")
                .query(JdbcPost.class)
                .list();
    }

    @Override
    public Optional<JdbcPost> findById(String id) {
        return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post WHERE id = :id")
                .param("id", id)
                .query(JdbcPost.class)
                .optional();
    }

    @Override
    public void create(JdbcPost post) {
        int update = jdbcClient.sql("INSERT INTO post(id,title,slug,date,time_to_read,tags) values(?,?,?,?,?,?)")
                .params(List.of(post.id(), post.title(), post.slug(), post.date(), post.timeToRead(), post.tags()))
                .update();
        Assert.state(update == 1, "Failed to create post " + post.title());
    }

    @Override
    public void update(JdbcPost post, String id) {
        var updated = jdbcClient.sql("update post set title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? where id = ?")
                .params(List.of(post.title(), post.slug(), post.date(), post.timeToRead(), post.tags(), id))
                .update();

        Assert.state(updated == 1, "Failed to update post " + post.title());
    }

    @Override
    public void delete(String id) {
        var updated = jdbcClient.sql("delete from post where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete post " + id);
    }


}
