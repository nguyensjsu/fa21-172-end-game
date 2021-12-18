package com.example.foodapi;

//import java.util.Objects;

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
public class FoodOrder {
    private @Id @GeneratedValue Long id;
    
    @Column(nullable=false)     private String order;
    @Column(nullable=false)     private String size;
    @Column(nullable=false)     private double total;
                                private String status;
}
