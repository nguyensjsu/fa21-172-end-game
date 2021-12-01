package com.example.endgame;

class DonationNotFoundException extends RuntimeException 
{
    DonationNotFoundException(Long id) 
    {
        super("Could not find donation" + id);
    }
}
