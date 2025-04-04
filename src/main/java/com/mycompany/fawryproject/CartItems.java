/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

/**
 *
 * @author 10
 */
public class CartItems {
    private Product product;
    private int quantity;
    
    public CartItems(Product product, int quantity){
       if(quantity > product.getAvaliableQuantity())
       {
           throw new IllegalArgumentException("There is no more avaliable quantity");
       }
       this.product=product;
       this.quantity=quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice()
    {
        return quantity * product.getPrice() ;
    }
}
