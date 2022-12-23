package com.Udm1.ExtraChallenges;

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxInt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxInt = 0;
        int[] intArray;

        System.out.print("Please, enter integer numbers separated by space: ");
        String strInput = sc.nextLine();

        intArray = convertStringArrayToIntArray(strInput.split("\\s+"));

        maxInt = findMaxIntInArray(intArray);
        System.out.println("*** Initial Array ***");
        System.out.println(Arrays.toString(intArray));
        System.out.println("*** Max number in array ***");
        System.out.println(maxInt);
    }

    public static int[] convertStringArrayToIntArray(String[] spacedNumbers) {
        int[] intArray = new int[spacedNumbers.length];

        for (int i = 0; i < spacedNumbers.length; i++) {
            intArray[i] = Integer.parseInt(spacedNumbers[i]);
        }
        return intArray;
    }

    public static int findMaxIntInArray(int[] intArray) {
        int maxArrElement = 0;

        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > maxArrElement) {
                maxArrElement = intArray[i];
            }
        }
        return maxArrElement;
    }

}