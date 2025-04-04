/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 10
 */
public class CheckoutService {
    private static final double Shipping_Rate_Per_KG = 27.0;
    
    public void processCheckout(Customer customer){
        ShippingCart shippingcart= customer.getShippingCart();
        
        if (shippingcart.isEmpty())
        {
            throw new IllegalStateException("The Cart is Empty!!");
        }
        
        for(CartItems item: shippingcart.getItems())
        {
            Product product = item.getProduct();
            
            if(product.isExpired())
            {
                throw new IllegalStateException(product.getName());
            }
            if(item.getQuantity() > product.getAvaliableQuantity())
            {
                throw new IllegalStateException("The Product " + product.getName() + " is out of stock!!");
            }
        }
        
        double subtotal= shippingcart.SubTotal();
        double ShippingFees= calculateShippingFees(shippingcart);
        double TotalAmount= subtotal + ShippingFees; 
        
        if(customer.getBalance() < TotalAmount)
        {
            throw new IllegalStateException("Insufficient balance! Required balance is: "+ TotalAmount + " LE, Avalibale balance is: "+ customer.getBalance()+ " LE");
        }
        
        customer.deductBalance(TotalAmount);
        
        for(CartItems item: shippingcart.getItems())
        {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        
        sendShippableItems(shippingcart);
        PrintCheckOutDetails(customer, subtotal, ShippingFees, TotalAmount);
        
        shippingcart.clear();
    }

    private double calculateShippingFees(ShippingCart shippingcart) {
        double TotalWeight = 0;
        
        for(CartItems item: shippingcart.getItems())
        {
            Product product = item.getProduct();
            
            if (product.RequireShipping() && product instanceof ShippableProducts) {
            ShippableProducts shippableProduct = (ShippableProducts) product;
            TotalWeight += shippableProduct.getWeight() * item.getQuantity();
        }
        }
        return TotalWeight * Shipping_Rate_Per_KG;
    }

    private void PrintCheckOutDetails(Customer customer, double subtotal, double ShippingFees, double TotalAmount) {
        System.out.println("** Checkout receipt **");
        
        for (CartItems item : customer.getShippingCart().getItems()) {
            Product product = item.getProduct();
            System.out.println(item.getQuantity() + "x " + product.getName() + " " + String.format("%.0f", item.getTotalPrice())+ " LE");
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + String.format("%.0f", subtotal) + " LE");
        System.out.println("Shipping " + String.format("%.0f", ShippingFees) + " LE");
        System.out.println("Amount " + String.format("%.0f", TotalAmount) + " LE");
    }
    
     private void sendShippableItems(ShippingCart cart) {
        List<ShippingItem> shippableItems = new ArrayList<>();
        double totalWeight = 0;
        
        boolean hasShippableItems = false;
        for (CartItems item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.RequireShipping() && product instanceof ShippableProducts) {
                hasShippableItems = true;
                break;
            }
        }
        
        if (hasShippableItems) {
            System.out.println("** Shipment notice **");
            
            for (CartItems item : cart.getItems()) {
                Product product = item.getProduct();
                
                if (product.RequireShipping() && product instanceof ShippableProducts) {
                    ShippableProducts shippableProduct = (ShippableProducts) product;
                    final int quantity = item.getQuantity();
                    final double weight = shippableProduct.getWeight();

                    System.out.println(quantity + "x " + product.getName() + " " + String.format("%.0f", weight * 1000 * quantity) + "g");
                    
                    totalWeight += weight * quantity;

                    shippableItems.add(new ShippingItem() {
                        
                        @Override
                        public String getName() {
                            return product.getName();
                        }
                        
                        @Override
                        public double getWeight() {
                            return weight;
                        }
                    });
                }
            }
            
            if (!shippableItems.isEmpty()) {
                System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
                System.out.println();
            }
        }
    }
}