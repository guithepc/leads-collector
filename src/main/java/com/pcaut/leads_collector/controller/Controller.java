package com.pcaut.leads_collector.controller;

import com.pcaut.leads_collector.services.impl.ConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/services")
public class Controller {

    @GetMapping(value="/connect")
    public ResponseEntity<List<String>> conncect() throws InterruptedException, IOException {
        ConnectionServiceImpl connectionService = new ConnectionServiceImpl();

        List<String> lista = connectionService.connect();
        return ResponseEntity.ok(lista);
    }
}
