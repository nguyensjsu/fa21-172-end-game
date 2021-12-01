package com.example.endgame;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DonationController 
{
    private final DonationRepository donationRepository;
    private final DonationModelAssembler assembler;
  
    DonationController(DonationRepository donationRepository, DonationModelAssembler assembler) 
    {
        this.donationRepository = donationRepository;
        this.assembler = assembler;
    }
    
    // Get all donations
    @GetMapping("/donations")
    CollectionModel<EntityModel<Donation>> all() 
    {
        List<EntityModel<Donation>> donations = donationRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(donations, linkTo(methodOn(DonationController.class).all()).withSelfRel());
    }
    
    // Get a specific donation by id
    @GetMapping("/donations/{id}")
    EntityModel<Donation> one(@PathVariable Long id) 
    {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new DonationNotFoundException(id));
  
        return assembler.toModel(donation);
    }
    
    // Post a new donation
    @PostMapping("/donations")
    ResponseEntity<EntityModel<Donation>> newDonation(@RequestBody Donation donation) 
    {
        donation.setStatus(Status.IN_PROGRESS);
        Donation newDonation = donationRepository.save(donation);
  
        return ResponseEntity.created(linkTo(methodOn(DonationController.class).one(newDonation.getId())).toUri()).body(assembler.toModel(newDonation));
    }
    
    // Cancel a specific donation by id
    @DeleteMapping("/donations/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) 
    {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new DonationNotFoundException(id));
  
        if (donation.getStatus() == Status.IN_PROGRESS) 
        {
            donation.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(donationRepository.save(donation)));
        }
  
        return ResponseEntity //
            .status(HttpStatus.METHOD_NOT_ALLOWED) //
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
            .body(Problem.create() //
            .withTitle("Method not allowed") //
            .withDetail("You can't cancel an donation that is in the " + donation.getStatus() + " status"));
    }
    
    // Complete an id specific donation
    @PutMapping("/donations/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) 
    {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new DonationNotFoundException(id));
    
        if (donation.getStatus() == Status.IN_PROGRESS) 
        {
            donation.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(donationRepository.save(donation)));
        }
    
        return ResponseEntity //
            .status(HttpStatus.METHOD_NOT_ALLOWED) //
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
            .body(Problem.create() //
            .withTitle("Method not allowed") //
            .withDetail("You can't complete an donation that is in the " + donation.getStatus() + " status"));
    }
}
