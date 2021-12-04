package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

interface CharityOrderRepository extends JpaRepository<CharityOrder, Long> 
{
    
}
