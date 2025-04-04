/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

/**
 *
 * @author 10
 */
public abstract class Product {
    private String name;
    private double price;
    private int AvaliableQuantity;
    
    public Product(String name, double price, int AvaliableQuantity ){
        
        this.AvaliableQuantity=AvaliableQuantity;
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvaliableQuantity() {
        return AvaliableQuantity;
    }
    
    public void decreaseQuantity(int quantity){
    
        if (quantity <= AvaliableQuantity)
        {
         AvaliableQuantity -= quantity;    
        }
        else
        {
           throw new IllegalArgumentException("Ther is no avaliable product for now!");
        }
    }
    
    public abstract boolean isExpired();
    public abstract boolean RequireShipping();
    
}
