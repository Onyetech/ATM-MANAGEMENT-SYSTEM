package com.example.atmmngtsystem.controllers;

import com.example.atmmngtsystem.enums.OperationType;
import com.example.atmmngtsystem.models.Card;
import com.example.atmmngtsystem.models.Operations;
import com.example.atmmngtsystem.services.impl.CardServiceImpl;
import com.example.atmmngtsystem.services.impl.OperationsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
public class WithdrawalsController {

    private CardServiceImpl cardService;
    private OperationsServiceImpl operationsService;

    public WithdrawalsController(CardServiceImpl cardService, OperationsServiceImpl operationsService) {
        this.cardService = cardService;
        this.operationsService = operationsService;
    }


    @PostMapping("/withdrawals")
    public String withdrawalsPage(@SessionAttribute("cardNumber") long cardNumber, @RequestParam("keyboardValues") double amountWithdraw, Model model) {
        Card card = cardService.getCard(cardNumber);

        if (amountWithdraw > card.getBalance()) {

            model.addAttribute("amountExceed", "Entered amount exceed your balance");
            return "errorpage";
        }

        //withdrawn operation
        double balanceUpdate = card.getBalance() - amountWithdraw;


        //saving updating card balance
        cardService.balanceUpdate(cardNumber, balanceUpdate);

        //writing operation to database

        Operations operation = new Operations(card, OperationType.WITHDRAWAL, LocalDateTime.now(),balanceUpdate,amountWithdraw);

        operationsService.saveOperation(operation);

        model.addAttribute("Operation", operationsService.findByDateTime(operation.getDateTime()));
        return "operationsreporst";
    }


    @GetMapping("/withdrawals")
    public String getWithdrawals() {
        return "withdrawals";
    }
}
