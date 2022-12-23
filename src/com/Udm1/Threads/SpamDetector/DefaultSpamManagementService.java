package com.Udm1.Threads.SpamDetector;

import java.util.Arrays;
import java.util.Scanner;

public class DefaultSpamManagementService implements SpamManagementService {

    private static DefaultSpamManagementService instance;

    private static final int DEFAULT_MESSAGES_AND_INTERVALS_CAPACITY = 20;

    private Long[] intervals = new Long[DEFAULT_MESSAGES_AND_INTERVALS_CAPACITY];
    private String[] messages = new String[DEFAULT_MESSAGES_AND_INTERVALS_CAPACITY];

    public static DefaultSpamManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultSpamManagementService();
        }
        return instance;
    }

    @Override
    public Spam makeSpamObject() {
        return new DefaultSpamManagementService.Spam(intervals, messages);
    }

//    TODO fix scanner issues. find super cool way
//    TODO switch from scanning by line to scanning on live input
    @Override
    public void collectInputs() {
        Scanner sc = new Scanner(System.in);
        long intervalStart = System.currentTimeMillis();
        String userInput = null;

        System.out.println("Please input your messages, press Enter to submit message");
        for (int i = 0; i < messages.length; i++) {
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("")) { // TODO fix this!
                break;
            }
            messages[i] = userInput;
            intervals[i] = calculateInterval(intervalStart);
            intervalStart = System.currentTimeMillis();
        }
        sc.close();
    }

    // TODO find good console log class and set timer. usage of method may be modified
    private long calculateInterval(long start) {
        return System.currentTimeMillis() - start;
    }

    public class Spam implements Runnable {
//        Array elements must be checked if they're nulls to make sure filled elements are printed
        Long[] intervals;
        String[] messages;

        Spam (Long[] intervals, String[] messages) {
            this.intervals = intervals;
            this.messages = messages;
        };

//        TODO organize for best practices. Specific or not exceptions?
        private synchronized void imitateUserInput() {
            try {
                for (int i = 0; i < messages.length; i++) {
                    if (messages[i] == null
                            || intervals[i] == null) {
                        break;
                    }
                    this.wait(intervals[i]);
                    System.out.println(messages[i]);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private synchronized void printMessages() {
            System.out.println(Arrays.toString(messages));
        }

        private synchronized void printIntervals() {
            System.out.println(Arrays.toString(intervals));
        }

//        TODO print messages with intervals using thread.sleep for interval values
        @Override
        public void run() {
            printMessages();
            printIntervals();
            imitateUserInput();
        }

    }

}
