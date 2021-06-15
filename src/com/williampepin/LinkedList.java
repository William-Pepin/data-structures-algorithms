package com.williampepin;

import java.util.NoSuchElementException;

public class LinkedList<E extends Comparable<E>> {
    private Node first;
    private Node last;
    private int size = 0;

    public int size(){
        return size;
    }

    public void addFirst(E item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else{
            node.next = first;
            first = node;
        }
        size++;
    }

    public void addLast(E item){
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else{
            last.next = node;
            last = node;
        }
        size++;
    }

    public E removeFirst(){
        if (isEmpty())
            throw new NoSuchElementException();

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

    public E removeLast(){
        if(isEmpty())
            throw new NoSuchElementException();

        var removed  = last;
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

    public int IndexOf(E item){
        int index = 0;
        var current = first;
        while(current != null) {
            if (current.value.equals(item)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(E item){
        return IndexOf(item) != -1;
    }

    public E[] toArray(){
        Comparable<E>[] array = new Comparable[size];
        var current = first;
        var index = 0;
        while (current != null){
            array[index++] = current.value;
            current = current.next;
        }
        return (E[]) array;
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

    public E getKthFromEnd(int k){
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
        private E value;
        private Node next;

        public Node(E value){
            this.value = value;
        }
    }
}

