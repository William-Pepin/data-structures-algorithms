package com.williampepin;

public class Stack<E extends Comparable<E>> {
    private Comparable[] items;
    private int count;

    public Stack(int size){
        items = new Comparable[size];
        count = 0;
    }

    public void push(int item) {
        if(count == items.length)
            throw new StackOverflowError();

        items[count] = item;
        count++;
    }

    public E pop() {
        if(empty())
            throw new IllegalStateException();
        return (E) items[--count];
    }

    public E peek() {
        if(empty())
            throw new IllegalStateException();
        return (E) items[count];
    }
    public boolean empty(){
        return count == 0;
    }
}
