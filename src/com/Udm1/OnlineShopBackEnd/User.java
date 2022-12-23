package com.Udm1.OnlineShopBackEnd;

public interface User {

    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();
    int getId();

    void setPassword(String newPassword);
    void setEmail(String newEmail);
    void setFirstName(String firstName);
    void setLastName(String lastName);

    void clearState();
}
