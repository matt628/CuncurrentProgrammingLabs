package com.company;

public class LockObject {
    int currentThread = 0;
    int number_of_threads;
    LockObject(int number_of_threads){
        this.number_of_threads = number_of_threads;
    }

    public void changeThread(){
        currentThread = ((++currentThread) % this.number_of_threads);
    }

    public int getCurrentThread(){
        return currentThread+1;
    }

}
