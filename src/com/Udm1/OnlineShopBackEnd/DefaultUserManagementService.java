package com.Udm1.OnlineShopBackEnd;

import java.util.ArrayList;
import java.util.List;

// Manages user registration
public class DefaultUserManagementService implements UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE =
            "This email is already used by another user. Please, use another email\n";

    private static final String EMPTY_EMAIL_ERROR_MESSAGE =
            "You have to input email to register. \n";

    private static final String IMPOSSIBLE_EMAIL_ERROR_MESSAGE =
            "Email you entered isn't correct. \n";

    private static final String EMPTY_FIRSTNAME_ERROR_MESSAGE =
            "You have to input first name to register. \n";

    private static final String EMPTY_LASTNAME_ERROR_MESSAGE =
            "You have to input last name to register. \n";

    private static final String ILLEGAL_PASSWORD_ERROR_MESSAGE =
            "Password must be at least 7 characters long and contain at least 1 number\n";

    private static final String CANNOT_STORE_USER_ERROR_MESSAGE =
            "Registration error. Unfortunately we reached limit for storing" +
                    " registered users. Try again soon\n";
    private static final String EMPTY_USER_ERROR_MESSAGE = "Insufficient information for registration. \n";

    private static final String REGISTRATION_SUCCESS_MESSAGE = "New user successfully created\n";

    private static DefaultUserManagementService instance;

    private DefaultUser defaultUserInstance;

    private List<User> users;   // all orders including loaded orders
    private List<User> recentlyRegisteredUsers; // orders to be saved, made at current runtime

    {
        defaultUserInstance = DefaultUser.getInstance();
        users = new ArrayList<User>();
        recentlyRegisteredUsers = new ArrayList<User>();
    }

    private DefaultUserManagementService() {
    }

    // Checks password validity and whether inputs are null
    // and registers new user if email is unique
    // returns error or successful registration message
    //TODO Fix registering with wrong info. Maybe shorten
    @Override
    public String registerUser(User user) {
        String registrationResult = checkFullUserCredentials(user);

        if (registrationResult.length() > 1) { // check for wrong credentials message
            return registrationResult;
        }
        defaultUserInstance.incrementIdIndex();
        recentlyRegisteredUsers.add(user);
        users.add(user);
        registrationResult = REGISTRATION_SUCCESS_MESSAGE;
        return registrationResult;
    }

    @Override //was named checkUserInput
    public String checkFullUserCredentials(User user) {
        String checkResult = "";

        if (user == null) {
            return EMPTY_USER_ERROR_MESSAGE;
        }
        if (user.getFirstName() == null) {
            checkResult += EMPTY_FIRSTNAME_ERROR_MESSAGE;
        }
        if (user.getLastName() == null) {
            checkResult += EMPTY_LASTNAME_ERROR_MESSAGE;
        }
        if (user.getPassword() == null ||
                user.getPassword().length() < 7
                || ( !user.getPassword().contains("1")
                && !user.getPassword().contains("2")
                && !user.getPassword().contains("3")
                && !user.getPassword().contains("4")
                && !user.getPassword().contains("5")
                && !user.getPassword().contains("6")
                && !user.getPassword().contains("7")
                && !user.getPassword().contains("8")
                && !user.getPassword().contains("9")) ) {
            checkResult += ILLEGAL_PASSWORD_ERROR_MESSAGE;
        }
        if (user.getEmail()
                .length() < 1) {
            checkResult += EMPTY_EMAIL_ERROR_MESSAGE;

        } else if (getUserByEmail(
                user.getEmail()) != null) {
            checkResult += NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
        } else if (!user.getEmail().contains("@") ) {
            checkResult += IMPOSSIBLE_EMAIL_ERROR_MESSAGE;
        }
        if (checkResult.length() > 1) {
            checkResult += "Please, try one more time\n";
        }
        return checkResult;
    }

    @Override
    public String checkSignInPassword(String enteredEmail, String enteredPassword) {
        User user = getUserByEmail(enteredEmail);

        if (user != null
                && user.getPassword()
                .equals(enteredPassword)) {
            return "Welcome, " + user.getFirstName() + "!\n";
        }
        return "Unfortunately, such login and password doesn't exist\n";
    }

    @Override
    public void addUsers(List<User> loadedUsers) {
        users.addAll(loadedUsers);
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        List<User> copyOfRecentlyRegisteredUsers = users;
        return copyOfRecentlyRegisteredUsers;
    }

    @Override
    public List<User> getRecentUsers() {
        List<User> copyOfRecentUsers = recentlyRegisteredUsers;
        return copyOfRecentUsers;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : users) {
            if (user != null
                && user.getEmail()
                    .equals(userEmail)) {
                return user;
            }
        }
        return null;
    }

    public void clearServiceState() {
        recentlyRegisteredUsers.clear();
        users.clear();
    }

}
