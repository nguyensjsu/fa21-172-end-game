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
@Table(indexes=@Index(name = "altIndex", columnList = "cardNumber", unique = true))
@Data
@RequiredArgsConstructor
public class StarbucksCard {
   private @Id @GeneratedValue Long id;
   
   @Column(nullable = false) private String cardNumber;
   @Column(nullable = false) private String cardCode;
   @Column(nullable = false) private double balance;
   @Column(nullable = false) private boolean activated;
                             private String status;
   
}
