package com.example.atmmngtsystem.services;

import com.example.atmmngtsystem.models.Operations;

import java.time.LocalDateTime;

public interface OperationsService {
    void saveOperation (Operations operation);
    Operations findByDateTime(LocalDateTime dateTime);
}
