package com.Udm1.Threads;

public class ThreadTest extends Thread { // wrong design lol

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Thread1());
        ThreadTest thread2 = new ThreadTest();
        Thread thread3 = new Thread(() -> System.out.println("I'm Thread-3"));
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm Thread-4");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.run();
        thread2.run();
        thread4.run();
        thread4.run();

        System.out.println("YES?");
    }

    @Override
    public void run() {
        System.out.println("I'm Thread-2.");
    }

}
