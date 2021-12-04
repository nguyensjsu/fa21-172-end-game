package com.example.orders;

class DonationNotFoundException extends RuntimeException 
{
    DonationNotFoundException(Long id) 
    {
        super("Could not find donation" + id);
    }
}
