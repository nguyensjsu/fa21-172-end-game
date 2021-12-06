package com.example.donationsapi;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DonationsCardController {
    private final StarbucksCardRepository repository;

    DonationsCardController(StarbucksCardRepository repository){
        this.repository = repository;
    }

    //Create a new Starbucks card
    @PostMapping("/card")
    StarbucksCard newCard(){
        StarbucksCard newCard = new StarbucksCard(); // object that will persist

        Random random = new Random();
        int num = random.nextInt(900000000) + 100000000;
        int code = random.nextInt(900) + 100;

        newCard.setCardNumber(String.valueOf(num));
        newCard.setCardCode(String.valueOf(code));
        newCard.setBalance(20.00);
        newCard.setActivated(false);
        newCard.setStatus("New Card");
        return repository.save(newCard);
    }
    // Get the list of Starbucks cards
    @GetMapping("/card")
    List<StarbucksCard> all(){
        return repository.findAll();
    }

    //Delete all Starbucks cards
    @DeleteMapping("/card")
    void deleteAll(){
        repository.deleteAllInBatch();
    }

    // Get details of a Starbucks card
    @GetMapping("/card/{num}")
    StarbucksCard getOne(@PathVariable String num, HttpServletResponse response){
        StarbucksCard card = repository.findByCardNumber(num);
        if(card == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        }
        return card;
    }

    // Activate a Starbucks card
    @PostMapping("/card/activate/{num,/{code}")
    StarbucksCard activate(@PathVariable String num, @PathVariable String code, HttpServletResponse response){
        StarbucksCard card = repository.findByCardNumber(num);
        if(card == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
        }
        if(card.getCardCode().equals(code)){
            card.setActivated(true);
            repository.save(card);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Valid!");
        }
        return card;
    }
}
