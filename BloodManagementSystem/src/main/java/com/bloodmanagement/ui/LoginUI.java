package com.bloodmanagement.ui;

import com.bloodmanagement.service.AuthService;
import java.util.Scanner;

public class LoginUI {
    private AuthService authService = new AuthService();
    private Scanner scanner = new Scanner(System.in);

    public void showLogin() {
        System.out.println("--- 💉 SISTEMI I MENAXHIMIT TË GJAKUT ---");
        System.out.println("Ju lutem identifikohuni.");

        System.out.print("Username: ");
        String user = scanner.nextLine();

        System.out.print("Password: ");
        String pass = scanner.nextLine();

        if (authService.authenticate(user, pass)) {
            System.out.println("✅ Login i suksesshëm! Mirësevini, " + user);
            MainMenu mainMenu = new MainMenu();
            mainMenu.showMenu();
        } else {
            System.out.println("❌ Username ose Password i gabuar!");
            showLogin();
        }
    }
}