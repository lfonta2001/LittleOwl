package com.lfonta.littleowl.config;

import com.lfonta.littleowl.persistence.PersistenceModule;
import com.lfonta.littleowl.persistence.config.DatabaseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.http.HttpClient;

@Configuration
public class GeneralConfig {

    @Value("${littleowl.database.host}")
    private String host;
    @Value("${littleowl.database.port}")
    private int port;
    @Value("${littleowl.database.name}")
    private String database;
    @Value("${littleowl.database.username}")
    private String username;
    @Value("${littleowl.database.password}")
    private String password;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public PersistenceModule getPersistenceModule() {
        DatabaseProperties props = new DatabaseProperties(
                host,
                port,
                database,
                username,
                password
        );

        return new PersistenceModule(props);
    }
}
