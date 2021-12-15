package com.example.atmmngtsystem.controllers;


import com.example.atmmngtsystem.enums.OperationType;
import com.example.atmmngtsystem.models.Card;
import com.example.atmmngtsystem.models.Operations;
import com.example.atmmngtsystem.services.impl.CardServiceImpl;
import com.example.atmmngtsystem.services.impl.OperationsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
public class BalanceController {

    private OperationsServiceImpl operationsService;
    private CardServiceImpl cardService;


    public BalanceController(OperationsServiceImpl operationsService, CardServiceImpl cardService) {
        this.operationsService = operationsService;
        this.cardService = cardService;
    }

    @PostMapping("/balance")
    public String balancePage(@SessionAttribute("cardNumber") long cardNumber, Model model) {
        Card card = cardService.getCard(cardNumber);

        Operations operations = new Operations(card, OperationType.BALANCE, LocalDateTime.now(),card.getBalance(),0.0);
        operationsService.saveOperation(operations);
        model.addAttribute("balance", card.getBalance());
        model.addAttribute("dateTime", operations.getDateTime());

        return "balance";
    }
}
