package com.example.donationsapi;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(indexes=@Index(name = "altIndex", columnList = "id"))
@Data
@RequiredArgsConstructor
public class DonationsOrder {
    private @Id @GeneratedValue Long id;
    
    // Replaced drink with tiers, removed milk
    @Column(nullable=false)     private String tier;
    @Column(nullable=false)     private String charity;
    @Column(nullable=false)     private double total;
                                private String status;

    // Use Lombok to generate getters and setters
}