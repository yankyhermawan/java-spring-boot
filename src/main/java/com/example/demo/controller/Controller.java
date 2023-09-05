package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ApiService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class Controller {

    private final ApiService apiService;

    @Autowired
    public Controller(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/hello")
    public String hello(String args[]) {
        return "Hello, World!";
    }

    @GetMapping("/querystring")
    public String queryString(
            @RequestParam(name = "param1", required = true) String param1,
            @RequestParam(name = "param2", required = true) String param2) {
        String response = String.format("%s %s", param1, param2);
        return response;
    }

    @GetMapping("/params/{id}/{name}")
    public String params(
            @PathVariable("id") String id,
            @PathVariable("name") String name) {
        String response = String.format("%s %s", id, name);
        return response;
    }

    @PostMapping("/data")
    public void data(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return;
    }

    @PostMapping("/name")
    public void name(@RequestBody String requestBody) throws com.fasterxml.jackson.core.JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(requestBody);
        String name = rootNode.get("name").asText();
        String result = apiService.stringProcess(name);
        System.out.println(result);
        return;

    }
}