package com.example.donationsapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface StarbucksCardRepository extends JpaRepository <StarbucksCard, Long> {

    StarbucksCard findByCardNumber(String cardNumber);
    
}
