/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

/**
 *
 * @author 10
 */
public class NonExpirableProduct extends Product implements ShippableProducts{
    
    private boolean RequireShipping;
    private double weight;

    public NonExpirableProduct(String name, double price, int AvaliableQuantity, boolean RequireShipping, double weight) {
        super(name, price, AvaliableQuantity);
        this.RequireShipping=RequireShipping;
        this.weight=weight;
    }
    
    
    @Override
    public boolean isExpired() {
        return false;

    }

    @Override
    public boolean RequireShipping() {
        
        return RequireShipping;
    }
    
    @Override
    public double getWeight() {
        if (!RequireShipping) {
            return 0;
        }
        return weight;
    }
    
    
    
}
