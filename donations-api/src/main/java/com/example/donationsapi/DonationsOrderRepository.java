package com.example.donationsapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface DonationsOrderRepository extends JpaRepository <DonationsOrder, Long> {
    
}
