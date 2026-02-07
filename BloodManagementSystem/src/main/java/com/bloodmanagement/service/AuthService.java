package com.bloodmanagement.service;

import com.bloodmanagement.dao.UserDAO;
import com.bloodmanagement.model.User;
import java.util.List;

public class AuthService {
    private UserDAO userDAO = new UserDAO();
    private static User currentUser;

    public boolean authenticate(String username, String password) {
        User user = userDAO.login(username, password);
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }


    public void createNewUser(String username, String password, int roleId, Integer bloodGroupId) {
        if (isAdmin()) {
            userDAO.registerUser(username, password, roleId, bloodGroupId);
        } else {
            System.out.println("❌ Vetem admini mund te krijoje perdorues te rinj!");
        }
    }

    public List<User> listUsers() {
        if (isAdmin()) {
            return userDAO.getAllUsers();
        }
        return null;
    }

    public void removeUser(int userId) {
        if (isAdmin()) {

            if (currentUser.getUserID() == userId) {
                System.out.println("❌ Nuk mund të fshini veten tuaj!");
                return;
            }
            userDAO.deleteUser(userId);
        }
    }

    private boolean isAdmin() {
        return currentUser != null && currentUser.getRoleID() == 1;
    }

    public static User getCurrentUser() { return currentUser; }
    public static void logout() { currentUser = null; }
}