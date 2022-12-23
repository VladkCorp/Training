package com.Udm1.ExtraChallenges;

public class AdminGuestMode {

    public static void main(String[] args) {
        boolean isAdmin = false;
        boolean isGuest = false;

        for(String s : args) {
            if (s.contains("--admin")) {
                isAdmin = true;
            }
            if (s.contains("--guest")) {
                isGuest = true;
            }
        }

        if (isAdmin && isGuest) {
            System.out.println("Please, select either 'ADMIN' or 'Guest' mode for this program");

        } else if (isAdmin) {
            System.out.println("Hello, Admin!");
        } else if (isGuest) {
            System.out.println("Hello, Guest!");
        }
    }
}