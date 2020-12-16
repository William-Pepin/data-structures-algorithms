package com.williampepin;

public class Array {
    private int[] items;
    private int count;

    public Array(int length){
    items = new int[length];
    }

    public void insert(int item){
        items[count++] = item;
    }

    public void print(){
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }
}
