package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class AmountOfWords {

    public static void main(String[] args) {
        String input;
        int amountOfWords;
        Scanner sc = new Scanner(System.in);

        System.out.print("Please, enter any text: ");

	    input = sc.nextLine();
	    amountOfWords = getWordsAmount(input);

        System.out.println("Amount of words in your text: " + amountOfWords);
    }

    public static int getWordsAmount(String text) {
		String[] wordsArray = text.split("\\s");

        return wordsArray.length;
    }
}
