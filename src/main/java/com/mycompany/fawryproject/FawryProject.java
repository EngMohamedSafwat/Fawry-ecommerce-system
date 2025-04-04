/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fawryproject;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author 10
 */
public class FawryProject {

    public static void main(String[] args) {
        
        // Expirable Products
        CanBeExpireProduct cheese = new CanBeExpireProduct("Cheese", 100, 50, LocalDate.now().plusDays(30), true, 0.2);
        CanBeExpireProduct expiredCheese = new CanBeExpireProduct("Expired Cheese", 100, 50, LocalDate.now().minusDays(5), true, 0.2);
        CanBeExpireProduct biscuits = new CanBeExpireProduct("Biscuits", 150, 100, LocalDate.now().plusDays(90), true, 0.7);
        CanBeExpireProduct expiredBiscuits = new CanBeExpireProduct("Expired Biscuits", 150, 100, LocalDate.now().plusDays(90), true, 0.7);
        
        // Non Expirable products
        NonExpirableProduct tv = new NonExpirableProduct("TV", 25000, 32, true, 15.0);
        NonExpirableProduct mobile = new NonExpirableProduct("Mobile", 20000, 32, true, 0.4);
        
        // Digital products
        DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50, 200);
        
        Customer customer = new Customer("Mohamed Safwat", 100000);
        
        System.out.println("Welcome, " + customer.getName());
        System.out.println("Your balance is: " + customer.getBalance() + " LE");
        
        CheckoutService checkoutService = new CheckoutService();
        
        Product[] availableProducts = {
            cheese, expiredCheese, biscuits, tv, mobile, scratchCard
        };
        
        // If you want to test fast uncomment code for fawry example to run and test it easily.
        
        /*
        
        try {
            customer.getShippingCart().clear();
            
            System.out.println("Adding products to cart..");
            customer.addToCart(cheese, 2);
            customer.addToCart(biscuits, 1);
            
            System.out.println("\nProcessing checkout..");
            checkoutService.processCheckout(customer);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        */
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n** FAWRY STORE **");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!!");
                continue;
            }
            
            switch (choice) {
                case 1:
                    AddProductToCart(customer, scanner, availableProducts);
                    break;
                    
                case 2:
                    ViewCart(customer);
                    break;
                    
                case 3:
                    checkout(customer, checkoutService);
                    break;
                    
                case 4:
                    running = false;
                    System.out.println("Thanks for your shopping, have a nice day :) ");
                    break;
                    
                default:
                    System.out.println("Please Enter Valid Option!!");
            }
        }
        
        scanner.close();
        
    }
    
    private static void AddProductToCart(Customer customer, Scanner scanner, Product[] products) {
        
        System.out.println("** Avaliable Products **");
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            System.out.printf("%d. %s - %.0f LE %s\n", 
                    i + 1, 
                    p.getName(), 
                    p.getPrice(),
                    p.isExpired() ? "(Expired!!)" : "");
        }
        
        System.out.println("Enter product number (1-" + products.length + "): ");
        
        try {
            int productNum = Integer.parseInt(scanner.nextLine());
            
            if (productNum < 1 || productNum > products.length) {
                System.out.println("Invalid product number");
                return;
            }
            
            Product selectedProduct = products[productNum - 1];
            
            if (selectedProduct.isExpired()) {
                System.out.println("The Product: " + selectedProduct.getName() + " is expired!");
                System.out.println("Cannot be added to the cart!!");
                return;
            }
            
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            if (quantity <= 0) {
                System.out.println("Quantity cannot be zero");
                return;
            }
            
            try {
                customer.addToCart(selectedProduct, quantity);
                System.out.println(quantity + "x " + selectedProduct.getName() + " added to cart!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static void ViewCart(Customer customer) {
        ShippingCart cart = customer.getShippingCart();
        
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!!");
            return;
        }
        
        System.out.println("** YOUR CART **");
        for (CartItems item : cart.getItems()) {
            String status = item.getProduct().isExpired() ? " (Expired!!)" : "";
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + 
                    status + " - " + String.format("%.0f", item.getTotalPrice()) + " LE");
        }
        
        System.out.println("----------------------");
        System.out.println("Cart Total: " + String.format("%.0f", cart.SubTotal()) + " LE");
        
        System.out.println("Would you like to remove an item? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim().toLowerCase();
        
        if (answer.equals("y")) {
            System.out.print("Enter product name to remove: ");
            String productName = scanner.nextLine().trim();
            cart.removeItem(productName);
            System.out.println("Cart updated.");
            
            ViewCart(customer);
        }
    }
    
    private static void checkout(Customer customer, CheckoutService checkoutService) {
        ShippingCart cart = customer.getShippingCart();
        
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!!, There is nothing to checkout");
            return;
        }
        
        ViewCart(customer);
        
        System.out.print("Proceed with checkout? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("Checkout cancelled.");
            return;
        }
        
        try {
            // Display balance before checkout
            System.out.println("Current balance: " + customer.getBalance() + " LE");
            
            checkoutService.processCheckout(customer);
            
            System.out.println("Checkout successful!");
            System.out.println("Remaining balance: " + customer.getBalance() + " LE");
            
        } catch (Exception e) {
            if (e.getMessage().contains("is out of stock") || 
                    e.getMessage().contains("Empty") ||
                    e.getMessage().contains("Balance")) {
                System.out.println("Checkout failed: " + e.getMessage());
            } else {
                System.out.println("Checkout failed: The Product " + e.getMessage() + " is expired!!!");
                System.out.println("Please remove this product from your cart before proceeding!!");
            }
            System.out.println("Balance unchanged: " + customer.getBalance() + " LE");
        }
    }
}