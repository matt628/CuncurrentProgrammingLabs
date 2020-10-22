package com.company;

public class Consumer extends Thread{
    ThreadSafeStorage threadSafeStorage;
    int id;
    int portionSize;
    boolean report = false;
    Consumer(ThreadSafeStorage threadSafeStorage, int id, int portionSize){
        this.threadSafeStorage = threadSafeStorage;
        this.id = id;
        this.portionSize = portionSize;
    }
    @Override
    public void run() {
        while(true){
            try {
                consume();
            } catch (InterruptedException e) {
                if (report) {
                    System.out.println("Consumer " + id + "failed consuming " + portionSize + " items");
                }
//                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        if(report) {
            System.out.println("Consumer " + id + " is consuming " + portionSize + " items");
        }
        threadSafeStorage.take(portionSize);
    }

    public void setReport(boolean report) {
        this.report = report;
    }
}
