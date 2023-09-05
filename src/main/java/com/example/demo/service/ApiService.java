package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public String stringProcess(String name) {
        return String.format("%s 123", name);
    }
}
