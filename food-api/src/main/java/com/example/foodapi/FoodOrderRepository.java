package com.example.foodapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FoodOrderRepository extends JpaRepository <FoodOrder, Long> {
    
}
