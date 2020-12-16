package com.williampepin;

import java.util.NoSuchElementException;

public class LinkedList {
    private Node first;
    private Node last;
    private int size = 0;

    public int size(){
        return size;
    }

    public void addFirst(int item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else{
            node.next = first;
            first = node;
        }
        size++;
    }

    public void addLast(int item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else{
            last.next = node;
            last = node;
        }
        size++;
    }

    public int removeFirst(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        var removed = first;
        if(hasOneItem()) {
            first = last = null;
        }
        else{
            first = removed.next;
            removed.next = null;
        }
        size--;
        return removed.value;
    }

    public int removeLast(){
        if(isEmpty())
            throw new NoSuchElementException();

        var removed = last;
        if(hasOneItem()) {
            first = last = null;
        }else{
            var previous = getPrevious(last);
            last = previous;
            previous.next = null;
        }
        size--;
        return removed.value;
    }

    public int IndexOf(int item){
        int index = 0;
        var current = first;
        while(current != null) {
            if (current.value == item) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int item){
        return IndexOf(item) != -1;
    }

    public int[] toArray(){
        int[] array = new int[size];
        var current = first;
        var index = 0;
        while (current != null){
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    public void reverse(){
        if(isEmpty()) return;

        var previous = first;
        var current = first.next;
        while (current != null){
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromEnd(int k){
        if(isEmpty())
            throw new IllegalStateException();
        if(k > size)
            throw new IllegalArgumentException();

        var a = first;
        var b = first;
        for (int i = 0; i < k - 1; i++) {
            b = b.next;
            // if the size is unknown
            // if (b == null)
            //    throw new IllegalArgumentException();

        }
        while (b != last){
            a = a.next;
            b = b.next;
        }
        return a.value;
    }


    private boolean isEmpty(){
        return first == null;
    }

    private boolean hasOneItem(){
        return first == last;
    }

    private Node getPrevious(Node node){
        var current = first;
        while(current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    private class Node {
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }
    }
}

