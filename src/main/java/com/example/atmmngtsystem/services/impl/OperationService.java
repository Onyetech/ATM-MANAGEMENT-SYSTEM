package com.example.atmmngtsystem.services.impl;

import com.example.atmmngtsystem.models.Operations;
import com.example.atmmngtsystem.repositories.OperationsRepository;
import com.example.atmmngtsystem.services.OperationsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperationService implements OperationsService {
    private OperationsRepository operationsRepository;

    public OperationService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public void saveOperation(Operations operation) {
        operationsRepository.save(operation);
    }

    @Override
    public Operations findByDateTime(LocalDateTime dateTime) {
        return operationsRepository.findByDateTime(dateTime);
    }

}
