package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class PyramidInConsole {

    public static void main(String[] args) {

        System.out.print("Please, enter height of the pyramid: ");
        Scanner sc = new Scanner(System.in);
        int pyramidHeight = sc.nextInt();
        String asterisks = "";

        for(int i = 0; i < pyramidHeight; i++) {

            while (asterisks.length() <= i) {
                asterisks += "*";
            }
            System.out.println(asterisks);
        }

        for (int j = asterisks.length()-1; j >= 0; j--) {

            System.out.println(asterisks.substring(0,j));
        }
    }

}