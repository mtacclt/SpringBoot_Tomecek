package com.spring.boot.demo.service;

import com.spring.boot.demo.model.Log;
import com.spring.boot.demo.repo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    LogRepository repo;

    public void insertLog(Log log) {
        repo.save(log);
    }
}
