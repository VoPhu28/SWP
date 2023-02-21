/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import com.DAO.ProductDAO;
import com.DAO.UserDAO;

/**
 *
 * @author
 */
public class GenerateID {

    UserDAO userDAO = new UserDAO();

    public String generate(String recognition) {
        String id = "";
        int min = 1;
        int max = 1000;
        while (true) {
            int random_int = (int) (Math.random() * (max - min + 1) + min);
            if (recognition.equals("customer")) {
                id = "CUS_" + String.valueOf(random_int);
                if (userDAO.getUserByID(id) == null) {
                    break;
                }
            } else if (recognition.equals("staff")) {
                id = "STA_" + String.valueOf(random_int);
                if (userDAO.getUserByID(id) == null) {
                    break;
                }
                break;
            }
        }

        return id;
    }

    public String generateProduct() {
        String id = "";
        int min = 1;
        int max = 1000;
        ProductDAO aO = new ProductDAO();
        while (true) {
            int random_int = (int) (Math.random() * (max - min + 1) + min);
            id = "PRO_" + String.valueOf(random_int);
            if (aO.getAllByID(id) == null) {
                break;
            }
        }

        return id;
    }

}
