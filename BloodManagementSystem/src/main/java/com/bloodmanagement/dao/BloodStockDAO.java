package com.bloodmanagement.dao;



import com.bloodmanagement.util.DatabaseConnection;

import com.bloodmanagement.model.BloodStock;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;



public class BloodStockDAO {

    public List<BloodStock> getStockStatus() {

        List<BloodStock> stockList = new ArrayList<>();



        String query = "SELECT GroupName, Quantity, LastUpdate FROM vw_BloodStockStatus";



        try (Connection conn = DatabaseConnection.getConnection();

             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(query)) {



            while (rs.next()) {

                stockList.add(new BloodStock(

                        rs.getString("GroupName"),

                        rs.getDouble("Quantity"), // Ky është COUNT-i nga SQL

                        rs.getTimestamp("LastUpdate") != null ?

                                rs.getTimestamp("LastUpdate").toLocalDateTime() :

                                java.time.LocalDateTime.now() // Default nëse s'ka data

                ));

            }

        } catch (SQLException e) {

            System.out.println("❌ [Database Error]: " + e.getMessage());

        }

        return stockList;

    }

}