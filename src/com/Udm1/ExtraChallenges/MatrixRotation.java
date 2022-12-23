package com.Udm1.ExtraChallenges;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixRotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Please, enter matrix size: ");
        int size = in.nextInt();
        double[][] matrix = generateMatrix(size);

        System.out.println("How you want to rotate matrix:" + System.lineSeparator() +
                "\t1 - 90" + System.lineSeparator() +
                "\t2 - 180" + System.lineSeparator() +
                "\t3 - 270");
        int mode = in.nextInt();

        System.out.println(System.lineSeparator() + "Base matrix:" + System.lineSeparator());
        printMatrixToConsole(matrix);
        System.out.println();

        if (rotateMatrix(matrix, mode)) {
            printMatrixToConsole(matrix);
        }
    }

    public static double[][] generateMatrix(int size) {
        double[][] matrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                matrix[i][j] = i+(0.1*j);
            }
        }
        return matrix;
    }

    public static boolean rotateMatrix(double[][] matrix, int mode) {
        boolean isExecuted = false;

        switch(mode) {
            case 1:
                rotate90(matrix);
                isExecuted = true;
                break;
            case 2:
                rotate180(matrix);
                isExecuted = true;
                break;
            case 3:
                rotate270(matrix);
                isExecuted = true;
                break;
        }
        return isExecuted;
    }

    // Used only for printing
    public static void printMatrixToConsole(double[][] matrix) {
        double[] row;

        for (int i = 0; i < matrix.length; i++) {

            row = new double[matrix[i].length];

            for (int j = 0; j < matrix[i].length; j++) {

                BigDecimal bd = BigDecimal.valueOf(matrix[i][j]);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                // for fixing anomalies!

                row[j] = bd.doubleValue();
            }
            System.out.println(Arrays.toString(row));
        }
    }


    private static void transposeMatrix(double[][] matrix) {
        double temp;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < i; j++) {

                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    private static void verticalReflection(double[][] matrix) {
        double temp;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length / 2; j++) {

                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    private static void horizontalReflection(double[][] matrix) {
        double temp;

        for (int i = 0; i < matrix.length / 2; i++) {

            for (int j = 0; j < matrix.length; j++) {

                temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = temp;
            }
        }
    }

    public static void rotate90(double[][] matrix) {
        transposeMatrix(matrix);
        verticalReflection(matrix);
    }

    public static void rotate180(double[][] matrix) {
        verticalReflection(matrix);
        horizontalReflection(matrix);
    }

    public static void rotate270(double[][] matrix) {
        transposeMatrix(matrix);
        horizontalReflection(matrix);
    }

}
