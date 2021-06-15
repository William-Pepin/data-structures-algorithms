package com.williampepin;

public class Array<E extends Comparable<E>>   {
    private Comparable[] items;
    private int count;

    public Array(){items = new Comparable[count = 10]; };
    public Array(int length){
    items  = new Comparable[length];
    }

    public void insert(E item){
        if(items.length == count){
            Comparable[] newItems = new Comparable[count*2];
            for (int i = 0; i < count; i++)
                newItems[i] = items[i];
            items = newItems;
        }
        items[count++] = item;
    }

    public void remove(int index) throws IllegalArgumentException {
        if(index < 0 || index >= count)
            throw new IllegalArgumentException();

        for (int i = index; i < count; i++) {
            items[i] = items[i+1];
            count--;
        }
    }

    public int indexOf(E item) {
        for (int i = 0; i < count; i++) {
            if (items[i].equals(item))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(E item){
        for (int i = count -1; i > -1; i++){
            if(items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    public int size(){
        return count;
    }

    public boolean contains(int item){
        for (int i = 0; i < count; i++) {
            if(items[i].equals(items))
                return true;
        }
        return false;
    }

    public E[] toArray(){
        Comparable[] newItems = new Comparable[count];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[i];
        }
        return (E[]) newItems;
    }

    public void print(){
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }
    // TODO max() return the largest number
    // TODO intersect() compares two array and return the number that have the same value in each of them
    // TODO reverse() reverses the array
    // TODO insertAt() insert at specified index
}
