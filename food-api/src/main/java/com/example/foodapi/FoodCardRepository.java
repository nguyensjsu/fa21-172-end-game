package com.example.foodapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FoodCardRepository extends JpaRepository <FoodCard, Long> {

    FoodCard findByCardNumber(String cardNumber);
    
}
