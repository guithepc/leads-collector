package com.pcaut.leads_collector.controller;

import com.pcaut.leads_collector.services.impl.ConnectionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/services")
public class Controller {

    @PostMapping(value="/connect")
    public ResponseEntity<List<String>> conncect() throws InterruptedException, IOException {
        ConnectionServiceImpl connectionService = new ConnectionServiceImpl();

        List<String> list = connectionService.connect();
        return ResponseEntity.ok(list);
    }
}
