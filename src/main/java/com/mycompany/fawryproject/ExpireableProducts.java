/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fawryproject;

import java.time.LocalDate;

/**
 *
 * @author 10
 */
public interface ExpireableProducts {
    LocalDate getExpiryDate();
    boolean isExpired();
}
