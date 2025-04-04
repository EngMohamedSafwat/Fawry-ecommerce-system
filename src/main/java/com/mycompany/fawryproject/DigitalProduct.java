/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

/**
 *
 * @author 10
 */
public class DigitalProduct extends Product {

    public DigitalProduct(String name, double price, int AvaliableQuantity) {
        super(name, price, AvaliableQuantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean RequireShipping() {
        return false;
    }
    
}
