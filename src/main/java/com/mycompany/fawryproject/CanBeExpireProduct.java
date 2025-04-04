/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

import java.time.LocalDate;

/**
 *
 * @author 10
 */
public class CanBeExpireProduct extends Product implements ExpireableProducts, ShippableProducts{
    
    private LocalDate ExpireDate;
    private boolean RequireShipping;
    private double weight;

    public CanBeExpireProduct(String name, double price, int AvaliableQuantity, LocalDate ExpireDate, boolean RequireShipping, double weight) {
        super(name, price, AvaliableQuantity);
        this.ExpireDate=ExpireDate;
        this.RequireShipping=RequireShipping;
        this.weight=weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(ExpireDate);
    }

    @Override
    public boolean RequireShipping() {
        return RequireShipping; 
    }

    @Override
    public LocalDate getExpiryDate() {
        return ExpireDate;
    }

    @Override
    public double getWeight() {
        if (!RequireShipping) {
            return 0;
        }
        return weight;
    }
  
}
