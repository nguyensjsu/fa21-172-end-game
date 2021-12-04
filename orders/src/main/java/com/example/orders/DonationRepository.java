package com.example.orders;

import org.springframework.data.jpa.repository.JpaRepository;

interface DonationRepository extends JpaRepository<Donation, Long> 
{
    
}
