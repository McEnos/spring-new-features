package com.example.springrestclient;

import com.example.springrestclient.client.JsonPlaceHolderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

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

}
