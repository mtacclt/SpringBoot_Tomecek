package com.spring.boot.demo.controller;

import com.spring.boot.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Autowired
    LogService service;

    @GetMapping("/return")
    public void dummyReturn() {
        System.out.println("dummyReturning method printing from inside");
    }

    @GetMapping("/throw")
    public void dummyThrow() throws Exception {
        System.out.println("dummyThrow method printing from inside");
        throw new Exception("Test Exception");
    }

    @GetMapping("/timing")
    public void timingMethod() {
        System.out.println("");
    }
}
