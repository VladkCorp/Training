package com.Udm1.OnlineShopBackEnd;

public class DefaultUser implements User {

    private static DefaultUser instance;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int id = -1;

    public static int idIndex = 1;

    public static DefaultUser getInstance() {
        if (instance == null) {
            instance = new DefaultUser();
        }
        return instance;
    }

    // when using this constructor IdIndex MUST be incremented after successful user credentials verification
    public DefaultUser() {
        this.id = idIndex;
    }

    // Only for User registration
    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.id = idIndex;

        incrementIdIndex();
    }

    public DefaultUser(String firstName, String lastName, String password, String email,
                       String customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.id = Integer.parseInt(customerId);
    }

    //TODO rearrange setGet by variable context he-he :D
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DefaultUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
     public void clearState() {
        this.firstName = null;
        this.lastName = null;
        this.password = null;
        this.email = null;
        this.id = -1;
    }

    public void incrementIdIndex() {
        idIndex++;
    }

}
