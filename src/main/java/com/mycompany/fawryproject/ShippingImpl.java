/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

import java.util.List;

/**
 *
 * @author 10
 */
public class ShippingImpl implements ShippingService {
    
        @Override
    public void processShipment(List<ShippingItem> items) {
        System.out.println("Processing shipment with " + items.size() + " items");
        
        System.out.println("Shipment processed successfully!");
    }
}
