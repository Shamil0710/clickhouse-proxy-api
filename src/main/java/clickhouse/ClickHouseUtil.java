package clickhouse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ClickHouseUtil {

    private static String clickhouseAddress;

    private static String clickhouseUsername;

    private static String clickhousePassword;

    private static String clickhouseDB;

    private static Integer clickhouseSocketTimeout;

    @Value("${clickhouse.address}")
    public  void setClickhouseAddress(String address) {
        ClickHouseUtil.clickhouseAddress = address;
    }
    @Value("${clickhouse.username}")
    public  void setClickhouseUsername(String username) {
        ClickHouseUtil.clickhouseUsername = username;
    }
    @Value("${clickhouse.password}")
    public  void setClickhousePassword(String password) {
        ClickHouseUtil.clickhousePassword = password;
    }
    @Value("${clickhouse.db}")
    public  void setClickhouseDB(String db) {
        ClickHouseUtil.clickhouseDB = db;
    }
    @Value("${clickhouse.socketTimeout}")
    public  void setClickhouseSocketTimeout(Integer socketTimeout) {
        ClickHouseUtil.clickhouseSocketTimeout = socketTimeout;
    }

    @Bean
    public static Connection getConn() {
        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(clickhouseUsername);
        properties.setPassword(clickhousePassword);
        properties.setDatabase(clickhouseDB);
        properties.setSocketTimeout(clickhouseSocketTimeout);
        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(clickhouseAddress,properties);
        try {
            conn = clickHouseDataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
