package com.Udm1.ExtraChallenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class FilterStringArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter any words separated by space: ");
        String userInput = sc.nextLine();
        System.out.print("Please, enter minumum word length to filter words: ");
        int minLength = sc.nextInt();

        String[] words = userInput.split("\\s+");
        String[] filteredWords = filterWordsByLength(minLength, words);
        System.out.println(Arrays.toString(filteredWords));
    }



    public static String[] filterWordsByLength(int minLength, String[] words) {
        String[] filteredWordsArr;
        List <String> filteredWordsList = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {

            if (words[i].length() >= minLength) {
                filteredWordsList.add(words[i]);
            }
        }
//        filteredWordsArr = new String[filteredWordsList.size()];
        filteredWordsArr = filteredWordsList.toArray(new String[filteredWordsList.size()]);
        return filteredWordsArr;
    }

}