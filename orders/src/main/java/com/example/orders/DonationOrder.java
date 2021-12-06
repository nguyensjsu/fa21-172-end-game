package com.example.orders;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(indexes=@Index(name = "DONATION_ORDER", columnList = "id"))
@RequiredArgsConstructor
public class DonationOrder
{
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @Column(nullable=true)      private String notes;
    @Column(nullable=false)     private String tier;
    @Column(nullable=false)     private double total;
                                private String status;
}                           

