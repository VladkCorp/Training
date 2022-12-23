package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class GreatestCommonDivisor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter two numbers separated by space: ");
        String userInput = sc.nextLine();

        String[] inputArgumentsArray = userInput.split("\\s+");
        int number1 = Integer.parseInt(inputArgumentsArray[0]);
        int number2 = Integer.parseInt(inputArgumentsArray[1]);

        System.out.println(gcdRecursive(number1, number2));
    }

    public static int gcdRecursive(int firstNumber, int secondNumber) {
        int greatestCommonDivisor = 1;

        int minNumber = Math.min( Math.abs(firstNumber), Math.abs(secondNumber));

        for (int i = 1; i <= minNumber; i++) {

            if ( i >= greatestCommonDivisor
                            && firstNumber % i == 0
                            && secondNumber % i == 0) {
                greatestCommonDivisor = i;
            }
        }
        return greatestCommonDivisor;
    }

}