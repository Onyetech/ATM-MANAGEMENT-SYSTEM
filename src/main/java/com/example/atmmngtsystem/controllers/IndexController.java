package com.example.atmmngtsystem.controllers;


import com.example.atmmngtsystem.models.Card;
import com.example.atmmngtsystem.services.CardService;
import com.example.atmmngtsystem.services.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller

public class IndexController {


    private CardService cardService;

    @Autowired
    public IndexController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/index")
    public String indexPage(@RequestParam("keyboardValues") String number, Model model, HttpSession session) {

        //Getting values from home page and replace all"-" symbols

        long cardNumber = Long.parseLong(number.replaceAll("[-]", ""));

        //Checking if card exist
        if (!cardService.cardExist(cardNumber)) {

            model.addAttribute("wrongCardNumber", "Wrong card Number!");
            return "errorpage";
        }
        //Checking if card is blocked
        if (!cardService.cardStatus(cardNumber)) {
            model.addAttribute("cardBlocked", "Your card is blocked!");
            return "errorpage";

        }
        //writing cardNumber to session
        session.setAttribute("cardNumber", cardNumber);

        return "pinentry";
    }

    @PostMapping("/createCards")
    public String createCard(Model model){
        model.addAttribute("card", new Card());
        return "createCard";
    }

    @PostMapping("/login")
    public String creatingCard(@ModelAttribute("card") Card card, Model model){
        //check if the card exists
        //if it exists, send message to the UI
        if(cardService.cardExist(card.getCardNumber())){
            model.addAttribute("message", "CARD ALREADY EXIST");
            model.addAttribute("card", new Card());
            return "createCard";
        }

        // if it doest not exist, create the card
        Card createdCard = cardService.createCard(card);
        // send message to Ui
        model.addAttribute("message", "CARD CREATED");

        // redirect to login page

        System.out.println(card);
        return "index";
    }

}
