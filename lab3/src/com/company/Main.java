package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // configuration variables
        final int maxBufferSize = 16384;
        final int consumersNumber = 8;
        final int producersNumber = 8;

        final int workingTime = 10;


        // initialization
        ArrayList<Producer> producers = new ArrayList<>(producersNumber);
        ArrayList<Consumer> consumers = new ArrayList<>(consumersNumber);
        ThreadSafeStorage threadSafeStorage = new ThreadSafeStorage(maxBufferSize);

        // create Producers
        for (int i = 0; i < producersNumber; i++) {
            Producer producer = new Producer(threadSafeStorage, i, (int) Math.pow(2, i));
            producers.add(producer);
            producer.start();
        }

        // create Consumers
        for (int i = 0; i < consumersNumber; i++) {
            Consumer consumer = new Consumer(threadSafeStorage, i, (int) Math.pow(2, i));
            consumers.add(consumer);
            consumer.start();
        }

        //let other work
        for (int i = workingTime; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        producers.forEach(producer -> producer.setReport(false));
        consumers.forEach(consumer -> consumer.setReport(false));
        //producers histogram
        System.out.println("\nProducers: ");
        for(Producer producer : producers){
            producer.tellMeAboutYoursefl();
        }



    }
}
