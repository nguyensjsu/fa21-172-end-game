package com.example.orders;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "donations")
public class Donation 
{
    private @Id @GeneratedValue Long id;

    private String description;
    private Status status;

    Donation() 
    {
        
    }

    Donation(String description, Status status) 
    {
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Donation))
        {
            return false;
        }
        Donation donation = (Donation) o;
        return Objects.equals(this.id, donation.id) && Objects.equals(this.description, donation.description) && this.status == donation.status;
    }
  
    @Override
    public int hashCode() 
    {
        return Objects.hash(this.id, this.description, this.status);
    }
  
    @Override
    public String toString() 
    {
        return "Donation{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }
}
