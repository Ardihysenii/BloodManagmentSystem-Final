package com.bloodmanagement.dao;

import com.bloodmanagement.util.DatabaseConnection;
import java.sql.*;

public class DonationDAO {

    public void addDonation(int donorId) {
        String sql = "{call sp_AddDonation(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            Date today = new Date(System.currentTimeMillis());
            Date expiry = new Date(System.currentTimeMillis() + (42L * 24 * 60 * 60 * 1000));

            stmt.setInt(1, donorId);
            stmt.setDate(2, today);
            stmt.setDate(3, expiry);

            stmt.execute();
            System.out.println("✅ Donacioni u regjistrua me sukses në sistem dhe Stok!");
        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë ekzekutimit në Database: " + e.getMessage());
        }
    }

    public void showDonorHistory(int donorId) {
        String sql = "{call sp_GetDonorHistory(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, donorId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--- Historiku Juaj i Donacioneve ---");
            while (rs.next()) {
                System.out.println("Data: " + rs.getDate("DonationDate") +
                        " | Spitali: " + rs.getString("HospitalName") +
                        " | Sasia: " + rs.getInt("VolumeML") + "ml");
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë marrjes së historikut: " + e.getMessage());
        }
    }
}