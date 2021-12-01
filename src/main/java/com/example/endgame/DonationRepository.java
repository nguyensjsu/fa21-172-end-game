package com.example.endgame;

import org.springframework.data.jpa.repository.JpaRepository;

interface DonationRepository extends JpaRepository<Donation, Long> 
{
    
}
