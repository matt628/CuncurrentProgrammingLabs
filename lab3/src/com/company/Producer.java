package com.company;

import java.util.ArrayList;

public class Producer extends Thread {
    ThreadSafeStorage threadSafeStorage;
    int id;
    int portionSize;
    boolean report = false;
    int successInsertionCounter = 0;

    Producer(ThreadSafeStorage threadSafeStorage, int id, int portionSize) {
        this.threadSafeStorage = threadSafeStorage;
        this.id = id;
        this.portionSize = portionSize;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<Object> list = produce();
            try {
                successInsertionCounter++;
                threadSafeStorage.put(list);
            } catch (InterruptedException e) {
                successInsertionCounter--;
                if (report) {
                    System.out.println("Producer " + id + " failed inserting " + portionSize + " items");
                }
//                System.out.println(System.out.format("Producer %d failed inserting %d items\n", id, portionSize));
//                e.printStackTrace();
            }
        }
    }

    private ArrayList<Object> produce() {
        //TODO jakie są konswencje Signal vs SignalAll
        ArrayList<Object> result = new ArrayList<>(portionSize);
        for (int i = 0; i < portionSize; i++) {
            result.add("xxx");  // TODO generate random strings
        }
//        System.out.println(System.out.format("Producer %d produced %d items\n", id, portionSize));
        if(report) {
            System.out.println("Producer " + id + " produced " + portionSize + " items");
        }
        return result;
    }

    public void tellMeAboutYoursefl(){
        System.out.println("Producer " + id + "was inserting" + portionSize + " items. I manged to do " + successInsertionCounter + " insertions");
    }

    public void setReport(boolean report) {
        this.report = report;
    }
}
