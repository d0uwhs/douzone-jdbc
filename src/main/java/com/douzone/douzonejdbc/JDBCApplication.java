package com.douzone.douzonejdbc;

import com.douzone.douzonejdbc.views.MainMenu;

import java.util.Scanner;

public class JDBCApplication {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();
    }
}
