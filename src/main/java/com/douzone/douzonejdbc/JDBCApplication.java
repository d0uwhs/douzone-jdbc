package com.douzone.douzonejdbc;

import com.douzone.douzonejdbc.views.ProductMenu;

import java.util.Scanner;

public class JDBCApplication {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ProductMenu productMenu = new ProductMenu();
        productMenu.mainMenu();
    }
}
