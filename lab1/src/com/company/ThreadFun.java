package com.company;

public class ThreadFun extends Thread {
    boolean inc;
    Counter counter;
    ThreadFun(boolean inc, Counter counter){
        this.inc = inc;
        this.counter = counter;
    }
    @Override
    public void run() {
        System.out.println("x");
        for(int i = 0; i < 1e8; i++){
            if(inc){
                counter.inc();
            }
            else {
                counter.dec();
            }
        }
    }
}
