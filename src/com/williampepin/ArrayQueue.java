package com.williampepin;

import java.util.Arrays;

// ArrayQueue using circular array
public class ArrayQueue<E extends Comparable<E>> {
    private Comparable[] items;
    private int front;
    private int back;
    private int count;

    public ArrayQueue(int capacity){
        items = new Comparable[capacity];
        front = back = 0;
    }

    public void enqueue(E item){
        if (count == items.length)
            throw new IllegalStateException();
        items[back] = item;
        back = (back + 1) % items.length;
        count++;
    }

    public E dequeue(){
        if (count == 0)
            throw new IllegalStateException();

        var item = items[front];
        items[front] = 0;
        front = (front + 1) % items.length;
        count--;
        return (E) item;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(items);
    }}
