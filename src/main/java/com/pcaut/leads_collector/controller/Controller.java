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

        List<String> lista = connectionService.connect();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value="/hh")
    public void hh() throws InterruptedException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Controller.findNumber(arr, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();

    }

    public static String findNumber(List<Integer> arr, int k) {
        String result = new String();
        for(int i =0; i < arr.size(); i++){
            Integer number = arr.get(i);

            if (number == k) {

                result = "YES";
            } else {
                result = "NO";
            }
        }
        System.out.println(result);
        return result;


    }
}
