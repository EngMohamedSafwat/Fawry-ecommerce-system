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
public class ShippingCart {
     private List<CartItems> items;
     
     public ShippingCart() {
        this.items = new ArrayList<>();
    }
     
    public void addItem(Product product, int quantity){
        if(quantity<=0)
        {
            throw new IllegalArgumentException("There are no products in the cart");
        }
        if (quantity > product.getAvaliableQuantity())
        {
            throw new IllegalArgumentException("There are no enough quantity avaliable");
        }
        for(CartItems item : items)
        {
            if(item.getProduct().getName().equals(product.getName()))
            {
                throw new IllegalArgumentException("The item is already in the cart exist!");
            }
        }
        items.add(new CartItems(product, quantity));
    }
    
    public void removeItem(String ProductName){
        items.removeIf(item-> item.getProduct().getName().equals(ProductName));
    }
    
    public List<CartItems> getItems()
    {
        return new ArrayList<>(items);
    }
    
    public boolean isEmpty()
    {
        return items.isEmpty();
    }
    
    public double SubTotal()
    {
        double subtotal=0;
        for(CartItems item: items)
        {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }
    
    public void clear()
    {
        items.clear();
    }
    
}
