package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // configuration variables
        int maxBufferSize = 16384;
        int consumerNumber = 8;
        int producerNumber = 8;

        // initialization
        ArrayList<Thread> producers = new ArrayList<>(producerNumber);
        ArrayList<Thread> consumers = new ArrayList<>(consumerNumber);
        ThreadSafeStorage threadSafeStorage = new ThreadSafeStorage(maxBufferSize);

        // create Producers
        for (int i = 0; i < producerNumber; i++) {
            Thread thread = new Producer(threadSafeStorage, i, (int) Math.pow(2, i));
            producers.add(thread);
            thread.start();
        }

        // create Consumers
        for (int i = 0; i < consumerNumber; i++) {
            Thread thread = new Consumer(threadSafeStorage, i, (int) Math.pow(2, i));
            consumers.add(thread);
            thread.start();
        }


    }
}
