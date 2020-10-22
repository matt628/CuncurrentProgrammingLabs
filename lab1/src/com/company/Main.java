package com.company;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
        ThreadFun tInc = new ThreadFun(true, counter);
        ThreadFun tDec = new ThreadFun(false, counter);
        tInc.start();
        tDec.start();
        try {
            tInc.join();
            tDec.join();
        } catch (Exception ex) {
            System.out.println("The exeption was thrown: " + ex);
        }
        System.out.println(counter.getCounter());


    }
}
