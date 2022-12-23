package com.Udm1.Threads.SpamDetector;

public class MainMenu extends Menu {

    private SpamManagementService spamManagementService;

    {
        spamManagementService = DefaultSpamManagementService.getInstance();
    }

    @Override
    void start() {
        Thread thread1;

        spamManagementService.collectInputs();
        thread1 = new Thread(spamManagementService
                .makeSpamObject());
        thread1.start();
//        thread1.interrupt();
    }

}
