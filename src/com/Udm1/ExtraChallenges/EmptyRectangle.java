package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class EmptyRectangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter height of rectangle: ");
        int height = sc.nextInt();
        System.out.print("Please, enter width of rectangle: ");
        int width = sc.nextInt();

        drawRectangle(height, width);
    }

    public static void drawRectangle(int height, int width) {
        String widthLine = "";
        String heightLine = "";
        String rectangleLineSpace = "";

        for (int i = 1; i <= width; i++) {
            widthLine += "*";
        }
        System.out.println(widthLine);

        for (int i = 1; i <= width - 2; i++) {
            rectangleLineSpace += " ";
        }
        heightLine = "*" + rectangleLineSpace + "*";

        for (int i = 1; i <= height-2; i++) {
            System.out.println(heightLine);
        }

        System.out.println(widthLine);
    }

}
