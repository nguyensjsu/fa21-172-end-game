package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface designed to support interaction with orders in the database
interface DonationOrderRepository extends JpaRepository<DonationOrder, Long> 
{
    
}
