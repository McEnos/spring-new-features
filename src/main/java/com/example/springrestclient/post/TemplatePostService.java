package com.example.springrestclient.post;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplatePostService implements JdbcPostService{
    private static final Logger log = LoggerFactory.getLogger(TemplatePostService.class);
    private final JdbcTemplate jdbcTemplate;


    RowMapper<JdbcPost> rowMapper = (rs, rowNum) -> new JdbcPost(
            rs.getString("id"),
            rs.getString("title"),
            rs.getString("slug"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("time_to_read"),
            rs.getString("tags")
    );

    @Override
    public List<JdbcPost> findAll() {
        var sql = "SELECT id,title,slug,date,time_to_read,tags FROM post";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<JdbcPost> findById(String id) {
        var sql = "SELECT id,title,slug,date,time_to_read,tags FROM post WHERE id = ?";
        JdbcPost post = null;
        try {
            post = jdbcTemplate.queryForObject(sql,rowMapper,id);
        } catch (DataAccessException ex) {
            log.info("Post not found: " + id);
        }
        return Optional.ofNullable(post);
    }

    @Override
    public void create(JdbcPost post) {
        String sql = "INSERT INTO post(id,title,slug,date,time_to_read,tags) values(?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,post.id(),post.title(),post.slug(),post.date(),post.timeToRead(),post.tags());
        if(insert == 1) {
            log.info("New Post Created: " + post.title());
        }
    }

    @Override
    public void update(JdbcPost post, String id) {
        String sql = "update post set title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? where id = ?";
        int update = jdbcTemplate.update(sql,post.title(),post.slug(),post.date(),post.timeToRead(),post.tags(),id);
        if(update == 1) {
            log.info("Post Updated: " + post.title());
        }
    }

    @Override
    public void delete(String id) {
        String sql = "delete from post where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Post Deleted: " + id);
        }
    }


}
