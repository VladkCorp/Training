package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class SumDigitsInNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter integer: ");
        int number = sc.nextInt();

        int sumOfDigits = sumDigitsInNumber(number);
        System.out.println(sumOfDigits);
    }

    public static int sumDigitsInNumber(int number) {
        int digit = 0;
        int sum = 0;
        String strNumber = Integer.toString(
                           Math.abs(number));

        for (int i = 0; i < strNumber.length(); i++) {

            digit = Integer.parseInt("" + strNumber.charAt(i));
            sum += digit;
        }
        return sum;
    }
}
