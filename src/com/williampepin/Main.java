package com.williampepin;


import com.williampepin.hash.HashTable;
import com.williampepin.sorting.Sort;
import com.williampepin.trees.Tree;
import com.williampepin.heaps.Heap;
import com.williampepin.trees.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
// Todo Test unitaire pour chaque structure
    // Todo Stack avec linked list
    // Todo Queue avec linked list et implementation de queue interface
    // Todo Doubly Linked list
    // Todo Toute en generic
    public static void main(String[] args) {

        treeTraversal();
        trie();
    }

    public static void treeTraversal(){
        Tree tree = new Tree();

        tree.insert(7);
        tree.insert(4);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(10 );

        Tree tree2 = new Tree();
        tree2.insert(7);
        tree2.insert(4);
        tree2.insert(9);
        tree2.insert(1);
        tree2.insert(6);
        tree2.insert(8);
        tree2.insert(10 );

        tree.depthPreOrder();
        tree.depthInOrder();
        tree.depthPostOrder();

        System.out.println(tree.height());
        System.out.println(tree.equals(tree2));
        System.out.println(tree.isBinarySearchTree());
        var list = tree.getNodesAtDistance(2);
        for (var item:list
             ) {
            System.out.println(item);
        }
        heap();

        sort();
    }

    public static void heap(){
        int[] numbers = {5,3,8,4,1,2};
        numbers = Heap.sortDescending(numbers);
        for (var number:numbers)
            System.out.println(number);
        numbers = Heap.sortAscending(numbers);
        for (var number:numbers)
            System.out.println(number);
        numbers = new int[]{5, 3, 8, 4, 1, 2};
        Heap.heapify(numbers);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Heap.GetKthLargest(numbers,2));
    }

    public static void trie(){
        var trie = new Trie();
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("careful");
        trie.insert("egg");
        trie.insert("car");

        var words = trie.findWords("ca");

        System.out.println(words);;
    }

    public static void sort(){
        System.out.println("--- Sort ---");
        var numbers = new int[]{5, 3, 8, 4, 1, 2};
        Sort.selectionSort(numbers);
        System.out.println(Arrays.toString(numbers));

        numbers = new int[]{5, 3, 8, 4, 1, 2};
        Sort.bubbleSort(numbers);
        System.out.println(Arrays.toString(numbers));

        numbers = new int[]{5, 3, 8, 4, 1, 2};
        Sort.insertionSort(numbers);
        System.out.println(Arrays.toString(numbers));

        numbers = new int[]{5, 3, 8, 4, 1, 2};
        Sort.MergeSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public static int factorial(int n){
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }
}
