package com.example.springrestclient.post;

import java.time.LocalDate;

public record JdbcPost(String id, String title, String slug, LocalDate date, int timeToRead, String tags) {
}
