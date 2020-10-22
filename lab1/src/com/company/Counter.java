package com.company;

import java.math.BigInteger;

public class Counter {
    int counter;
    Counter(){
        this.counter = 0;
    }
    synchronized void inc(){
        this.counter++;
    }
    synchronized void dec(){
        this.counter--;
    }

    synchronized public int getCounter() {
        return counter;
    }
}
