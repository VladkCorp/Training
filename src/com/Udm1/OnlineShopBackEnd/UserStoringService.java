package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public interface UserStoringService {

    public void saveUser(User user);

    void serializeUsers();

    public List<User> loadUsers();

    void deserializeUsers();

}
