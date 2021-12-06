package com.example.orders;

import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
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

    @DeleteMapping("")
    Message deleteAll()
    {
        repository.deleteAllInBatch();
        orders.clear();
        Message msg = new Message();
        msg.setStatus("All Donation Orders Cleared!");
        return msg;
    }

    // Create a new Donation order
    @PostMapping("/order/register/{regid")
    @ResponseStatus(HttpStatus.CREATED)
    DonationOrder newOrder(@PathVariable String regid, @RequestBody DonationOrder order){
        System.out.println("Place Donation (Reg ID = " + regid + " ) => " + order);
        // Checks input 
        if(order.getTier().equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Donation Request!");
        }
        // Check for active order
        DonationOrder active = orders.get(regid);
        if(active != null){
            System.out.println("Place Donation (Reg ID = " + regid + " ) => " + active);
            if(active.getStatus().equals("Ready for Payment.")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Donation Exists!");
            }
        }
        // set price
        double price = 0.00;
        switch(order.getTier()){
            case "Tier 1":
                price = 1.00;
                break;
            case "Tier 2":
                price = 5.00;
                break;
            case "Tier 3":
                price = 10.00;
                break;
            case "Tier 4":
                price = 20.00;
                break;
            case "Tier 5":
                price = 50.00;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Tier!");
        } // end of switch case
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale = Math.pow(10,2);
        double rounded = Math.round(total * scale);
        order.setTotal(rounded);
        // save order
        order.setStatus("Ready for Payment.");
        DonationOrder newOrder = repository.save(order);
        orders.put(regid, newOrder);
        return newOrder;
    }

    // Clear active order
    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid){
        DonationOrder active = orders.get(regid);
        if(active != null){
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Active Donation Order Cleared!");
            return msg;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Donation Order Not Found!");
        }
    }
}
