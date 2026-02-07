package com.bloodmanagement.ui;

import com.bloodmanagement.service.*;
import com.bloodmanagement.model.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private DonorService donorService = new DonorService();
    private AuthService authService = new AuthService();
    private BloodStockService stockService = new BloodStockService();
    private DonationService donationService = new DonationService();
    private BloodRequestService requestService = new BloodRequestService();

    public void showMenu() {
        int choice = -1;
        int userRole = AuthService.getCurrentUser().getRoleID();
        String username = AuthService.getCurrentUser().getUsername();

        while (choice != 0) {
            System.out.println("\n--------------------------------------------");
            System.out.println("   SISTEMI PER MENAXHIMIN E GJAKUT   ");
            System.out.println("   Perdoruesi: " + username.toUpperCase());
            System.out.println("--------------------------------------------");

            System.out.println("1. Shiko Stokun e Gjakut");
            System.out.println("2. Lista e Dhuruesve");

            if (userRole == 1) {
                System.out.println("\n--- MENAXHIMI I DHURIMEVE ---");
                System.out.println("3. Regjistro Dhurues te ri");
                System.out.println("4. Regjistro Donacion (Shto ne Stok)");
                System.out.println("7. Perditeso/Fshij Dhurues");

                System.out.println("\n--- SPITALET DHE KERKESAT ---");
                System.out.println("8. Regjistro Spital te ri");
                System.out.println("10. Lista e Spitaleve");
                System.out.println("9. KRIJO KERKESE PER GJAK");
                System.out.println("11. MONITORO STATUSIN E KERKESAVE");

                System.out.println("\n--- ADMINISTRIMI I SISTEMIT ---");
                System.out.println("5. Krijo Llogari te re (Staff/User)");
                System.out.println("6. Menaxho Perdoruesit");
            } else {
                System.out.println("\n--- MODULI I DHURUESIT ---");
                System.out.println("3. SHIKO KERKESAT PER GRUPIN TEND");
            }

            System.out.println("\n0. Dil / Logout");
            System.out.print("\nZgjedhja juaj: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                handleSelection(choice, userRole);
            } catch (Exception e) {
                System.out.println("Lajmerim: Ju lutem jepni nje numer valid!");
            }
        }
    }

    private void handleSelection(int choice, int userRole) {
        if (userRole == 1) {
            switch (choice) {
                case 1: displayCurrentStock(); break;
                case 2: displayAllDonors(); break;
                case 3: createNewDonor(); break;
                case 4: processNewDonation(); break;
                case 5: registerNewSystemUser(); break;
                case 6: listAndManageUsers(); break;
                case 7: updateOrDeleteDonor(); break;
                case 8: registerNewHospital(); break;
                case 9: createHospitalBloodRequest(); break;
                case 10: requestService.showHospitals(); break;
                case 11: requestService.showAllRequestsForAdmin(); break;
                case 0: AuthService.logout(); break;
                default: System.out.println("Opsion i panjohur!");
            }
        } else {
            switch (choice) {
                case 1: displayCurrentStock(); break;
                case 2: displayAllDonors(); break;
                case 3: handleDonorAcceptProcess(); break;
                case 0: AuthService.logout(); break;
                default: System.out.println("Opsion i panjohur!");
            }
        }
    }

    private void createHospitalBloodRequest() {
        requestService.showHospitals();
        try {
            System.out.print("ID e Spitalit: ");
            int hId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID e Grupit (1-8): ");
            int gId = Integer.parseInt(scanner.nextLine());
            System.out.print("Sasia (qese): ");
            int qty = Integer.parseInt(scanner.nextLine());
            requestService.submitRequest(hId, gId, qty);
        } catch (Exception e) { System.out.println("Gabim ne input!"); }
    }

    private void handleDonorAcceptProcess() {
        // Merr grupin e gjakut nga perdoruesi i loguar
        Integer userGroup = AuthService.getCurrentUser().getBloodGroupID();

        if (userGroup == null) {
            System.out.println("❌ Ky perdorues nuk ka nje grup gjaku te caktuar!");
            return;
        }

        requestService.showPendingForGroup(userGroup);
        System.out.print("\nID e kerkeses qe pranoni (0 per anullim): ");
        try {
            int reqId = Integer.parseInt(scanner.nextLine());
            if (reqId != 0) {
                requestService.handleDonorAccept(reqId);
                displayCurrentStock();
            }
        } catch (Exception e) { System.out.println("ID Invalide!"); }
    }

    private void displayCurrentStock() {
        System.out.println("\n--- STOKU AKTUAL ---");
        List<BloodStock> stock = stockService.getFullStock();
        for (BloodStock s : stock) System.out.println(s.getGroupName() + ": " + s.getQuantity() + " qese");
    }

    private void displayAllDonors() {
        List<Donor> list = donorService.getAllDonors();
        System.out.println("\n--- LISTA E DHURUESVE ---");
        for (Donor d : list) System.out.println("ID: " + d.getDonorID() + " | " + d.getFirstName() + " " + d.getLastName() + " (Gr: " + d.getBloodGroupID() + ")");
    }

    private void processNewDonation() {
        displayAllDonors();
        System.out.print("ID e Dhuruesit: ");
        try { donationService.registerNewDonation(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e) { System.out.println("Gabim!"); }
    }

    private void registerNewHospital() {
        System.out.println("\n--- SHTO SPITAL TE RI ---");
        System.out.print("Emri: "); String name = scanner.nextLine();
        System.out.print("Kontakt: "); String contact = scanner.nextLine();
        System.out.print("Lokacioni: "); String loc = scanner.nextLine();
        requestService.addHospital(name, contact, loc);
    }

    private void createNewDonor() {
        try {
            System.out.println("\n--- REGJISTRIMI I DHURUESIT ---");
            System.out.print("Emri: "); String n = scanner.nextLine();
            System.out.print("Mbiemri: "); String s = scanner.nextLine();
            System.out.print("Gjinia (M/F): "); String gjen = scanner.nextLine();
            System.out.print("Data (YYYY-MM-DD): "); LocalDate d = LocalDate.parse(scanner.nextLine());
            System.out.print("Grupi (1-8): "); int g = Integer.parseInt(scanner.nextLine());
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Telefon: "); String tel = scanner.nextLine();
            System.out.print("Adresa: "); String adr = scanner.nextLine();

            donorService.registerDonor(new Donor(n, s, gjen, d, g, email, tel, adr));
        } catch (Exception e) {
            System.out.println("❌ Gabim: Kontrolloni formatin e dates (YYYY-MM-DD) ose email-in!");
        }
    }

    private void updateOrDeleteDonor() {
        displayAllDonors();
        System.out.print("ID e Dhuruesit: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Emri i ri: "); String n = scanner.nextLine();
            System.out.print("Mbiemri i ri: "); String s = scanner.nextLine();
            System.out.print("Tel: "); String t = scanner.nextLine();
            donorService.updateExistingDonor(id, n, s, t);
        } catch (Exception e) { System.out.println("Gabim!"); }
    }

    private void registerNewSystemUser() {
        try {
            System.out.println("\n--- KRIJO LLOGARI TE RE ---");
            System.out.print("Username: "); String u = scanner.nextLine();
            System.out.print("Password: "); String p = scanner.nextLine();
            System.out.print("Roli (1=Admin, 2=User): ");
            int roli = Integer.parseInt(scanner.nextLine());

            Integer bloodGroupId = null;
            if (roli == 2) {
                System.out.print("Grupi i Gjakut (1-8): ");
                bloodGroupId = Integer.parseInt(scanner.nextLine());
            }

            authService.createNewUser(u, p, roli, bloodGroupId);
        } catch (Exception e) {
            System.out.println("❌ Gabim: Jepni te dhena valide!");
        }
    }

    private void listAndManageUsers() {
        List<User> users = authService.listUsers();
        System.out.println("\n--- PERDORUESIT E SISTEMIT ---");
        for (User u : users) System.out.println(u.getUserID() + ". " + u.getUsername() + " (Gr: " + u.getBloodGroupID() + ")");
        System.out.print("ID per fshirje (0=Mbrapa): ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if(id != 0) authService.removeUser(id);
        } catch (Exception e) { System.out.println("Gabim!"); }
    }
}