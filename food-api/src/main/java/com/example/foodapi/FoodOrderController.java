package com.example.foodapi;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FoodOrderController {
    private final FoodOrderRepository repository;

    @Autowired
    private FoodCardRepository cardsRepository;

    class Message{
        private String status;

        public String getStatus(){return status;}

        public void setStatus(String msg){status = msg;}
    }

    private HashMap<String, FoodOrder> orders = new HashMap<>();

    public FoodOrderController(FoodOrderRepository repository){
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<FoodOrder> all(){
        return repository.findAll();
    }

    @DeleteMapping("/orders")
    Message deleteAll(){
        repository.deleteAllInBatch();
        orders.clear();
        Message msg = new Message();
        msg.setStatus("All Orders Cleared!");
        return msg;
    }

    //Create a new Food order
    @PostMapping("/order/register/{regid")
    @ResponseStatus(HttpStatus.CREATED)
    FoodOrder newOrder(@PathVariable String regid, @RequestBody FoodOrder order){
        System.out.println("Placing Order (Reg ID = " + regid + " ) => " + order);
        // check input
        if(order.getOrder().equals("") || order.getSize().equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }
        // check for active order
        FoodOrder active = orders.get(regid);
        if(active != null){
            System.out.println("Active Order (Reg ID = " + regid + " ) => " + active);
            if(active.getStatus().equals("Ready for Payment.")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");

            }
        }
        // set price
        double price = 0.0;
        switch(order.getOrder()){
        case "Shake":
            switch(order.getSize()){
            case "Small":
                price = 2.19;
                break;
            case "Medium":
                price = 2.59;
                break;
            case "Large":
                price = 2.99;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "French Fries":
            switch(order.getSize()){
            case "Small":
            price = 1.39;
            break;
            case "Medium":
                price = 1.79;
                break;
            case "Large":
                price = 1.89;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
        case "Soda":
            switch(order.getSize()){
            case "Small":
                price = 1.00;
                break;
            case "Medium":
                price = 1.29;
                break;
                case "Large":
                price = 1.49;
                break;                        
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;    
        case "Orange Juice":
            switch(order.getSize()){
            case "Small":
                price = 1.59;
                break;
            case "Medium":
                price = 1.89;
                break;
                case "Large":
                price = 2.09;
                break;                        
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
            break;

        case "Premium Roast Coffee":
            switch(order.getSize()){
            case "Small":
                price = 1.00;
                break;
            case "Medium":
                price = 1.29;
                break;
            case "Large":
                price = 1.49;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;

        case "Iced Coffee":
            switch(order.getSize()){
            case "Small":
                price = 1.39;
                break;
            case "Medium":
                price = 1.79;
                break;
            case "Large":
                price = 1.99;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }    
            break;

        case "Cheeseburger":
            switch(order.getSize()){
            case "Regular":
                price = 1.00;
                break;          
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
            break;
                
        default:
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order!");
        }
        
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale = Math.pow(10, 2);
        double rounded = Math.round(total * scale);
        order.setTotal(rounded);
        // save order
        order.setStatus("Ready for Payment.");
        FoodOrder newOrder = repository.save(order);
        orders.put(regid, newOrder);
        return newOrder;
    }
    // Get details of Food card
    @GetMapping("/order/register/{regid}")
    FoodOrder getActiveOrder(@PathVariable String regid, HttpServletResponse response){
        FoodOrder active = orders.get(regid);
        if(active != null){
            return active;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }
    // Clear active order
    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid){
        FoodOrder active = orders.get(regid);
        if(active != null){
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Active Order Cleared!");
            return msg;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");

        }
    }

    // Process payment for the "active order"
    @PostMapping("/order/register/{regid}/pay/{cardnum}")
    FoodCard processOrder(@PathVariable String regid, @PathVariable String cardnum){
        System.out.println("Pay for Order:  Reg ID = " + regid + " Using Card = " + cardnum);
        FoodOrder active = orders.get(regid);
        if(active == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
        if(cardnum.equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Provided!");
        }
        if(active.getStatus().startsWith("Paid with Card")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Active Order!");
        }
        FoodCard card = cardsRepository.findByCardNumber(cardnum);
        if(card == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Found!");
        }
        if(!card.isActivated()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Activated!");
        }
        double price = active.getTotal();
        double balance = card.getBalance();
        if(balance - price < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds on Card!");
        }
        double newBalance = balance - price;
        card.setBalance(newBalance);
        String status = "Paid with Card:  " + cardnum + " Balance:  $" + newBalance + ".";
        active.setStatus(status);
        cardsRepository.save(card);
        return card;
    }
}
