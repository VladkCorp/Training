package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class ConvertDecimalToRoman {
    public static String[] romanUnitsArr = {"VIII", "VII", "III", "IV", "VI", "II", "IX", "I", "V"};
    public static String[] romanTensArr = {"LXXX", "LXX", "XXX", "XL", "LX", "XX", "XC", "X", "L"};
    public static int[] arrConvertedToUnits = {8, 7, 3, 4, 6, 2, 9, 1, 5};
    public static int[] arrConvertedToTens = {80, 70, 30, 40, 60, 20, 90, 10, 50};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mainLoop:
        while (true) {
            System.out.print("Please, select mode. If you want to convert Roman "
                    + "numbers to decimal - type 'R2D' and press enter."
                    + System.lineSeparator()
                    + "If you want to convert decimal numbers to Roman - type 'D2R' and press enter: ");

            String mode = sc.next();
            if (mode.equalsIgnoreCase("R2D")) {

                while (true) {

                    System.out.print("Please, enter Roman number you want to convert: ");
                    String romanNumber = sc.next();

                    if (isRomanNumberValid(romanNumber)) {
                        System.out.println(roman2Decimal(romanNumber));
                        break mainLoop;
                    } else {
                        System.out.println("You entered invalid Roman number. "
                                + "Please, try one more time.");
                        continue;
                    }
                }
            } else if (mode.equalsIgnoreCase("D2R")) {

                while (true) {

                    System.out.print("Please, enter decimal number "
                            + "you want to convert: ");
                    int decimalNumber = sc.nextInt();
                    if (isDecimalNumberValid(decimalNumber)) {
                        System.out.println(decimal2Roman(decimalNumber));
                        break mainLoop;
                    } else {
                        System.out.println("Please, enter positive integer from 1 to 100.");
                        continue;
                    }

                }

            }

            System.out.println("Please, enter 'R2D' or 'D2R.");
        }
    }


    /**
     * Converts decimal numbers to Roman.
     * <p>
     * Takes int value as a parameter. Works only with numbers from 1 to 100.
     *
     * @param number to convert to Roman.
     * @return string of Roman number.
     */
    public static String decimal2Roman(int number) {

        String strNum = Integer.toString(
                Math.abs(number));
        String romanOutput = "";
        int digit;

        if (number == 100) {
            return "C";
        }
        //logic here is dependent on 2 digits
        for (int i = 0; i < strNum.length(); i++) {

            digit = Integer.parseInt("" + strNum.charAt(i));
            if (strNum.length() == 1 || i == 1) {

                if (digit <= 3) {

                    for (int j = 0; j < digit; j++) {

                        romanOutput += "I";
                    }
                } else if (digit == 4) {
                    romanOutput += "IV";

                } else if (digit <= 8) {
                    romanOutput += "V";

                    for (int j = 5; j < digit; j++) {

                        romanOutput += "I";
                    }
                } else if (digit == 9) {
                    romanOutput += "IX";
                }
            } else if (i == 0) {

                if (digit <= 3) {

                    for (int j = 0; j < digit; j++) {

                        romanOutput += "X";
                    }
                } else if (digit == 4) {
                    romanOutput += "XL";

                } else if (digit <= 8) {
                    romanOutput += "L";

                    for (int j = 5; j < digit; j++) {

                        romanOutput += "X";
                    }
                } else if (digit == 9) {
                    romanOutput += "XC";
                }
            }
        }
        return romanOutput;
    }

    /**
     * Converts Roman numbers to decimal.
     * <p>
     * Takes string value with Roman number as a parameter.
     * <p>
     * At first method validates if input string could be Roman number. After method
     * uses algorithm to convert Roman numeral to decimal.
     *
     * @param romanNumber
     * @return decimal representation of Roman number
     */

    public static int roman2Decimal(String romanNumber) {
        int decimalOutput = 0;
        int units = 0;
        int tens = 0;

        if (romanNumber.equals("C")) {
            return 100;
        }

        for (int k = 0; k < romanUnitsArr.length; k++) {

            if (romanNumber.contains(romanUnitsArr[k])) {
                units = arrConvertedToUnits[k];
                if (romanNumber.equals(romanUnitsArr[k])) {
                    return units;
                }
                break;
            }
        }
        for (int j = 0; j < romanTensArr.length; j++) {

            if (romanNumber.contains(romanTensArr[j])) {
                tens = arrConvertedToTens[j];
                if (romanNumber.equals(romanTensArr[j])) {
                    return tens;
                }
                break;
            }
        }
        decimalOutput = tens + units;
        return decimalOutput;
    }


    /**
     * Validation for Roman numbers.
     * <p>
     * Use regular expression which is checking if string really could be Roman
     * number.
     *
     * @param romanNumber
     * @return true if String is Roman number
     */
    //Could use a logic upgrade, make less exception statements
    public static boolean isRomanNumberValid(String romanNumber) {
        int unitDigitCounter = 0;
        int tenDigitCounter = 0;
        String strUnits = "";
        String strTens = "";
        boolean isValid = false;

        if (romanNumber.length() > 8) {
            return false;
        }
        if (romanNumber.equals("C")) {
            return true;
        }

        for (int j = 0; j < romanUnitsArr.length; j++) {

            if (unitDigitCounter > 0) {
                break;
            }
            if (romanNumber.length() - romanUnitsArr[j].length() >= 0
                    && romanNumber.substring
                    (romanNumber.length() - romanUnitsArr[j].length()).
                    equals(romanUnitsArr[j])) {

                //cutting roman units from romanNumber
                romanNumber = romanNumber.substring
                        (0,romanNumber.length() - romanUnitsArr[j].length());

                isValid = true;
                unitDigitCounter++;
            }
        }
        for (int i = 0; i < romanTensArr.length; i++) {

            if (tenDigitCounter > 0) {
                break;
            }
            //dangerous
            if (romanNumber.length() == 0 && isValid) {
                return true;
            }
            if (romanNumber.length() - romanTensArr[i].length() >= 0
                    && romanNumber.equals(romanTensArr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDecimalNumberValid(int decimalNumber) {

		if (decimalNumber <= 100 && decimalNumber > 0) {
		    return true;
        } else
            return false;
    }

}