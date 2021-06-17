package com.project.clickhouseproxyapi.clickhouse.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конфигурационный класс для подключения к клик хаусу(В данный момент коннект происходит с MVP настройками)
 */
@Configuration
public class ClickHouseConnectionConfig {
    @Value("${clickhouse.datasourse}")
    private String dbUrl;

    @Bean
    public Connection getClickHouseConnection() {
        Connection connection = null;
        try {
            return connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
