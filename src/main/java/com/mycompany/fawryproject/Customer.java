/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

/**
 *
 * @author 10
 */
public class Customer {
    private String name;
    private double balance;
    private ShippingCart ShippingCart;
    
    public Customer(String name, double balance){
        this.ShippingCart= new ShippingCart();
        this.balance=balance;
        this.name=name;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public ShippingCart getShippingCart() {
        return ShippingCart;
    }
    
    public void addToCart(Product product, int quantity)
    {
        ShippingCart.addItem(product,quantity);
    }
    
    public void deductBalance(double amount)
    {
        if(amount > balance)
        {
            throw new IllegalArgumentException("Insuffecient Balance!!");
        }
        balance -= amount;
    }
}
