package com.example.atmmngtsystem.repositories;

import com.example.atmmngtsystem.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    boolean existsByCardNumber(Long number);

    Card getCardByCardNumber(Long cardNumber);

    Card getCardByPin(Integer pin);

    Card getCardByCardNumberAndAndPin(Long cardNumber, Integer pin);


}
