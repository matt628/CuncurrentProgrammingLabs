package com.company;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeStorage {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final int maxArraySize;
    ArrayList<Object> items;
//    final Object[] items = new Object[16384]; //todo set by constructor
//    int putptr, takeptr, count;


    public ThreadSafeStorage(int maxArraySize) {
        this.maxArraySize = maxArraySize;
        this.items = new ArrayList<>(maxArraySize);
    }

    public void put(ArrayList<Object> toInsert) throws InterruptedException {
        lock.lock();
        try {
            while (items.size() + toInsert.size() > maxArraySize)
                notFull.await();
            items.addAll(toInsert);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public ArrayList<Object> take(int quantityToTake) throws InterruptedException {
        lock.lock();
        try {
            while (items.size() < quantityToTake)
                notEmpty.await();
            ArrayList<Object> result = new ArrayList<>(quantityToTake);
            for(int i = 0; i < quantityToTake; i++){ //todo refactor
                result.add(items.remove(0));
            }
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
