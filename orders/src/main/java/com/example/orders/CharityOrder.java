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

@Entity
@Table(name = "CHARITY_ORDER")
class CharityOrder
{
    private @Id @GeneratedValue Long id;

    private String status;
    private String tier;
    private String notes;
    private double total;

    CharityOrder() 
    {

    }

    public String getStatus()
    {
        return this.status;
    }
    public String getTier()
    {
        return this.tier;
    }
    public String getNotes()
    {
        return this.notes;
    }
    public Double getTotal()
    {
        return this.total;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }
    public void setTotal(Double total) 
    {
        this.total = total;
    }
}
