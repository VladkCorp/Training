package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public interface UserManagementService {

    String registerUser(User user);

    void addUsers(List<User> loadedUsers);

    List<User> getUsers();

    List<User> getRecentUsers();

    User getUserByEmail(String userEmail);

    String checkFullUserCredentials(User user); //was named checkUserInput

    String checkSignInPassword (String userEmail, String password);

    void clearServiceState();
}

