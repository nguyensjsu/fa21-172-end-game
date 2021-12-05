package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

interface DonationOrderRepository extends JpaRepository<DonationOrder, Long> 
{
    
}
