package com.example.atmmngtsystem.services.impl;


import com.example.atmmngtsystem.enums.CardStatus;
import com.example.atmmngtsystem.models.Card;
import com.example.atmmngtsystem.repositories.CardRepository;
import com.example.atmmngtsystem.services.CardService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {


    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    //    checking if card exist in database
    @Override
    public boolean cardExist(long cardNumber) {
        boolean exist = false;

        if (cardRepository.existsByCardNumber(cardNumber)) {
            exist = true;
        }
        return exist;
    }

    @Override
    public Card createCard(@NonNull Card card) {

        card.setCardStatus(CardStatus.ACTIVE);
        card.setBalance(500.00);

      return   cardRepository.save(card);
    }

    //checking if card status "blocked" or "normal"
    @Override
    public boolean cardStatus(long cardNumber) {

        Card card = cardRepository.getCardByCardNumber(cardNumber);
        boolean status = false;
        if (card.getCardStatus().toString().equals("NORMAL")) {
            status = true;
        }

        return status;
    }

    //checking if pin matches card number
    @Override
    public boolean isPinMatch(long cardNumber, int pinCode) {

        Card card = cardRepository.getCardByCardNumber(cardNumber);

        boolean pinMatch = false;

        if (card.getPin() == pinCode) {

            pinMatch = true;
        }

        return pinMatch;
    }

    //blocking the card by changing card status
    @Override
    public void blockTheCard(long cardNumber) {
        Card card = cardRepository.getCardByCardNumber(cardNumber);
        card.setCardStatus(CardStatus.BLOCKED);
        cardRepository.save(card);

    }

    @Override
    public Card getCard(long cardNumber) {
        return cardRepository.getCardByCardNumber(cardNumber);
    }

    @Override
    public void balanceUpdate(long cardNumber, double balanceUpdate) {
        Card card = cardRepository.getCardByCardNumber(cardNumber);
        card.setBalance(balanceUpdate);
        cardRepository.save(card);
    }

//    @Override
//    public void saveCardDetails() {
//        return cardRepository.getCardByCardNumberAndAndPin(cardRepository.getCardByCardNumber(), cardRepository.getCardByPin());
//    }

}
