package com.example.atmmngtsystem.services;

import com.example.atmmngtsystem.models.Card;
import lombok.NonNull;

public interface CardService {

    boolean cardExist(long cardNumber);

    Card createCard(@NonNull Card card);

    boolean cardStatus(long cardNumber);

    boolean isPinMatch(long cardNumber, int pinCode);

    void blockTheCard(long cardNumber);

    Card getCard(long cardNumber);

    void balanceUpdate(long cardNumber, double balanceUpdate);

//    void creatingCard(long cardNumber, Integer pin);
}
