package com.williampepin;

public class Stack {
    int items[];
    int count;

    public Stack(int size){
        items = new int[size];
        count = 0;
    }

    public void push(int item) {
        if(count == items.length)
            throw new StackOverflowError();

        items[count] = item;
        count++;
    }

    public int pop() {
        if(empty())
            throw new IllegalStateException();
        return items[--count];
    }

    public int peek() {
        if(empty())
            throw new IllegalStateException();
        return items[count];
    }
    public boolean empty(){
        return count == 0;
    }
}
