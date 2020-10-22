package com.company;

public class Thread1 extends Thread {
    int id;
    LockObject lockObject;

    Thread1(int id, LockObject monitor) {
        this.id = id;
        this.lockObject = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lockObject) {
                    if (lockObject.getCurrentThread() != id) {
                        lockObject.wait();
                    } else {
                        System.out.println(id);
                        lockObject.changeThread();
                        lockObject.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
