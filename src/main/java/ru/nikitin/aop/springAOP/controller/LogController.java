package ru.nikitin.aop.springAOP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nikitin.aop.springAOP.model.Log;
import ru.nikitin.aop.springAOP.services.LogService;

@RestController
@RequestMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    @Autowired
    final private LogService service;

    public LogController(LogService service) {
        this.service = service;
    }


    @ResponseBody
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Log getLogsById(@PathVariable Long id) {
        return service.getLogById(id);
    }
}
