package com.williampepin;

public class Array {
    private int[] items;
    private int count;

    public Array(int length){
    items = new int[length];
    }

    public void insert(int item){
        if(items.length == count){
            int[] newItems = new int[count*2];
            for (int i = 0; i < count; i++) {
                newItems[i] = items[i];
            }
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

    public int indexOf(int item) {
        for (int i = 0; i < count; i++) {
            if (items[i] == item)
                return i;
        }
        return -1;
    }

    public int lastIndexOf(int item){
        for (int i = count -1; i > -1; i++){
            if(items[i] == item){
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
            if(items[i] == item)
                return true;
        }
        return false;
    }

    public int[] toArray(){
        int[] newItems = new int[count];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[i];
        }
        return newItems;
    }

    public void print(){
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }
    // TODO Rendre le type dynamique
    // TODO max() return the largest number
    // TODO intersect() compares two array and return the number
    // that have the same value in each of them
    // TODO reverse() reverses the array
    // TODO insertAt() insert at specified index
}
