package com.example.orders;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DonationModelAssembler implements RepresentationModelAssembler<Donation, EntityModel<Donation>>
{
    @Override
    public EntityModel<Donation> toModel(Donation donation) 
    {
        // Unconditional links to single-item resource and aggregate root

        EntityModel<Donation> donationModel = EntityModel.of(donation,
            linkTo(methodOn(DonationController.class).one(donation.getId())).withSelfRel(),
            linkTo(methodOn(DonationController.class).all()).withRel("donation"));

        // Conditional links based on state of the donation

        if (donation.getStatus() == Status.IN_PROGRESS) 
        {
            donationModel.add(linkTo(methodOn(DonationController.class).cancel(donation.getId())).withRel("cancel"));
            donationModel.add(linkTo(methodOn(DonationController.class).complete(donation.getId())).withRel("complete"));
        }

        return donationModel;
    }
}
