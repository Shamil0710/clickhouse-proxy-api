package clickhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ClickHouseService {

    @Autowired
    public Connection connection;

    //Получаем список из пар столбец значение предавая SQL запрос
    public List<Map<String, String>> exeSql(String sqlQuery) {
        log.info("Выполнение SQL запроса к ClickHouse " + sqlQuery);
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
