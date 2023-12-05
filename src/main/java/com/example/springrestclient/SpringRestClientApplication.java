package com.example.springrestclient;

import com.example.springrestclient.client.JsonPlaceHolderService;
import com.example.springrestclient.post.JdbcPost;
import com.example.springrestclient.post.JdbcPostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDate;

@SpringBootApplication
public class SpringRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestClientApplication.class, args);
    }

    @Bean
    JsonPlaceHolderService jsonPlaceHolderService() {
        RestClient client = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                        RestClientAdapter.create(client)
                )
                .build();
        return factory.createClient(JsonPlaceHolderService.class);
    }

    @Bean
    CommandLineRunner commandLineRunner(@Qualifier("jdbcClientPostService") JdbcPostService jdbcPostService) {
        return args -> jdbcPostService.create(new JdbcPost("1234", "Hello World", "hello-world", LocalDate.now(), 1, "java, spring"));
    }

}
