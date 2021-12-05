package com.example.orders;

import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.lang.Nullable;

@RestController
@RequestMapping("/donation")
public class DonationOrderController 
{
    @Autowired
    private final DonationOrderRepository repository;

    class Message
    {
        private String status;

        public String getStatus()
        {
            return status;
        }

        public void setStatus(String msg)
        {
            status = msg;
        }
    }

    private HashMap<String, DonationOrder> orders = new HashMap<>();

    public DonationOrderController(DonationOrderRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/all")
    List<DonationOrder> all()
    {
        return repository.findAll();
    }

    @DeleteMapping("/test")
    Message deleteAll()
    {
        repository.deleteAllInBatch();
        orders.clear();
        Message msg = new Message();
        msg.setStatus("All Donation Orders Cleared!");
        return msg;
    }

    
}
