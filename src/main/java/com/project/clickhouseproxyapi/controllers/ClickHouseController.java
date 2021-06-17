package com.project.clickhouseproxyapi.controllers;

import com.project.clickhouseproxyapi.Constants;
import com.project.clickhouseproxyapi.clickhouse.ClickHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контролер для получения запросов к кликхаусу
 */
@Slf4j
@RestController
public class ClickHouseController {
    private final ClickHouseService clickHouseService;

    @Autowired
    public ClickHouseController(ClickHouseService clickHouseService) {
        this.clickHouseService = clickHouseService;
    }

    @GetMapping(Constants.SQL_REQUEST)
    public void sqlRequest(@RequestBody String request) {
        log.info("Получение SQL запроса: {}", request);
        clickHouseService.retrievingBySqlQuery(request);
    }
}
