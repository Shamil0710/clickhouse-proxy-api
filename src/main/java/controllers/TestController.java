package controllers;

//import clickhouse.ClickHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @Autowired
//    private ClickHouseService clickHouseService;

    @GetMapping("/get")
    public String testRequest() {
//        clickHouseService.testSqlRequest();
        return "DONE";
    }

    @GetMapping("/")
    public String get() {
        return "GET";
    }
}
