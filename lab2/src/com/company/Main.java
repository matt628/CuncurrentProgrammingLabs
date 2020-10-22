package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    LockObject lockObject = new LockObject(3);

    Thread1 t1 = new Thread1(1, lockObject);
    Thread1 t2 = new Thread1(2, lockObject);
    Thread1 t3 = new Thread1(3, lockObject);

    t1.start();
    t2.start();
    t3.start();


    }
}
