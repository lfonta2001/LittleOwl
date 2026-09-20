package com.lfonta.theowlery.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class GeneralConfig {

    @Bean
    public Dotenv dotenvConfig() {
        return Dotenv.configure().ignoreIfMalformed().load();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
