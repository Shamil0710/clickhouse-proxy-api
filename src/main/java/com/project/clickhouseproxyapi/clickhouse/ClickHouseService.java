package com.project.clickhouseproxyapi.clickhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для взаимодействия с кликхаусом
 */
@Slf4j
@Service
public class ClickHouseService {
    private final Connection connection;

    @Autowired
    public ClickHouseService(Connection connection) {
        this.connection = connection;
    }

    //TODO Нужно ли использовать какой то промежуточный кешь или имеет смысл сразу писать в кафку?
    //Получаем список из пар столбец значение предавая SQL запрос (Пример запроса: SELECT COLUMNS(URL) FROM datasets.hits_v1)
    public List<Map<String, String>> retrievingBySqlQuery(String sqlQuery) {
        log.info("Выполнение SQL запроса к ClickHouse {}", sqlQuery);
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            List<Map<String, String>> list = new ArrayList<>();
            while (result.next()) {
                Map<String, String> hashMap = new HashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    hashMap.put(resultSetMetaData.getColumnName(i),
                            result.getString(resultSetMetaData.getColumnName(i)));
                }
                list.add(hashMap);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
