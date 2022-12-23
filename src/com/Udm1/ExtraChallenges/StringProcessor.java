package com.Udm1.ExtraChallenges;


// Will work ONLY on input formatted as in INPUT_DATA
//Separators and ; must be used in the same places for the same input types as in INPUT_DATA

public class StringProcessor {

    private static final String INPUT_DATA = "Login;Name;Email" + System.lineSeparator()
            + "peton;Chris Peterso;person@outlook.com" + System.lineSeparator()
            + "jas;Derek Jame;jes@gmail.com" + System.lineSeparator()
            + "jack;Walter Jackso;jkson@gmail.com" + System.lineSeparator()
            + "greg;Mike Gregor;ggory@yahoo.com";


    public static void main(String[] args) {
        System.out.println("===== Convert 1 demo =====");
        System.out.println(convert1(INPUT_DATA));

        System.out.println("===== Convert 2 demo =====");
        System.out.println(convert2(INPUT_DATA));

    }

    public static String convert1(String input) {
        String login = "";
        String email = "";
        String result = "";
        int firstSemicolonIndex = 0;

        String[] lines = input.split(System.getProperty("line.separator"));

        for(String line : lines) {

            if (line.equals("Login;Name;Email")) {
                continue;
            }

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == ';') {

                    login = line.substring(0, i);
                    firstSemicolonIndex = i;
                    break;
                }
            }
            for (int j = firstSemicolonIndex + 1; j < line.length(); j++) {

                if (j + 1 < line.length()
                        && line.charAt(j) == ';') {

                    email = line.substring(j + 1, line.length());
                    break;
                }
            }
            result += login + " ==> " + email + System.lineSeparator();
        }
        return result;
    }


    public static String convert2(String input) {
        String fullName = "";
        String email = "";
        String result = "";
        int firstSemicolonIndex = 0;

        String[] lines = input.split(System.getProperty("line.separator"));

        for(String line : lines) {
            //todo fix empty input

            if (line.equals("Login;Name;Email")) {
                continue;
            }

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == ';') {
                    firstSemicolonIndex = i;
                    break;
                }
            }
            for (int j = firstSemicolonIndex + 1; j < line.length(); j++) {

                if (j + 1 < line.length()
                        && line.charAt(j) == ';') {

                    fullName = line.substring(firstSemicolonIndex + 1, j);
                    email = line.substring(j + 1, line.length());
                }
            }
            result += fullName + " (email: " + email + ")" + System.lineSeparator();
        }
        return result;
    }

}