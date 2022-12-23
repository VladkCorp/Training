package com.Udm1.ExtraChallenges;

import java.util.Scanner;

public class DemoEnum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please, enter message type to check its priority: ");
            String userInput = sc.next();

            if (isValidMessageType(userInput)) {
                MessageType messageType = MessageType.valueOf(userInput);
                System.out.println(messageType.getPriority());
                break;
            } else {
                System.out.println("Please, enter valid "
                        + "message type. Only 'A', 'B', 'C' or 'D' are allowed");
                continue;
            }
        }

    }

    //won't process multiple messages todo
    private static boolean isValidMessageType(String userInput) {
        String[] splitInput = userInput.split("\\s+");
        for(String message : splitInput) {

            if (message.equals("A") || message.equals("B")
                    || message.equals("C") || message.equals("D")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}


