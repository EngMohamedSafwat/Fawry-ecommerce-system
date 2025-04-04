/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fawryproject;

import java.time.LocalDate;

/**
 *
 * @author 10
 */
public class FawryProject {

    public static void main(String[] args) {
        
        CanBeExpireProduct cheese = new CanBeExpireProduct("Cheese", 100, 50, LocalDate.now().plusDays(30), true, 0.2);
        
        CanBeExpireProduct biscuits = new CanBeExpireProduct("Biscuits", 150, 100, LocalDate.now().plusDays(90), true, 0.7);
        
        NonExpirableProduct tv = new NonExpirableProduct("TV", 5000, 32, true, 15.0);
        
        DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50, 200);
        
        Customer customer = new Customer("Mohamed Safwat", 100000);
        
        CheckoutService checkOut = new CheckoutService();
        
        try {
            customer.getShippingCart().clear();
            

            System.out.println("Adding products to cart..");
            customer.addToCart(cheese, 2);
            customer.addToCart(biscuits, 1);
            customer.addToCart(scratchCard, 2); 
            
            System.out.println("\nProcessing checkout..");
            checkOut.processCheckout(customer);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void DisplayProductInfo(Product product) {
        System.out.println("Product: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Requires Shipping: " + product.RequireShipping());
        System.out.println("Is Expired: " + product.isExpired());
        System.out.println();
    }
}
