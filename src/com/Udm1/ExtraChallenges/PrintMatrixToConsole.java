package com.Udm1.ExtraChallenges;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PrintMatrixToConsole {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5,},
                {6, 7},
                {8, 9, 10}
        };

        String matrixStr = "";

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                matrixStr += (matrix[i][j] + " ");
                if (j == matrix[i].length-1) {
                    matrixStr += "\n";
                }
            }
        }
        System.out.println(matrixStr);
    }
}