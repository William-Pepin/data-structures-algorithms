package com.williampepin.trees;

import com.williampepin.Array;

import java.util.ArrayList;
import java.util.Objects;

public class Tree {

  private Node root;

  public void insert (int value){
    var node = new Node(value);

    if(root == null){
      root = node;
      return;
    }

    var current = root;
    while(true){
      if(value < current.value) {
        if (current.leftChild == null){
          current.leftChild = node;
          break;
        }
        current = current.leftChild;
      }
      else{
        if(current.rightChild == null){
          current.rightChild = node;
          break;
        }
        current = current.rightChild;
      }
    }
  }

  public boolean find(int value){
    var current = root;
    while(current != null){
      if(value < current.value)
        current = current.leftChild;
      else if (value > current.value)
        current = current.rightChild;
      else
        return true;
    }
    return false;
  }

  public void depthPreOrder(){
    depthPreOrder(root);
  }

  public void depthInOrder(){
    depthInOrder(root);
  }

  public void depthPostOrder(){
    depthPostOrder(root);
  }

  public int height(){
    if(root == null)
      return -1;
    return height(root);
  }

  private int height(Node node){
    if(isLeaf(node))
      return 0;
    return 1 + Math.max(height(node.leftChild), height(node.rightChild));
  }

  public int min(){
    return min(root);
  }

  public int minBst(){
    if(root == null)
        throw new IllegalStateException();

    var current = root;
    var last = current;
    while(current != null){
      last = current;
      current = current.leftChild;
    }
    return last.value;
  }

  public ArrayList<Integer> getNodesAtDistance(int distance){
    var list = new ArrayList<Integer>();
    getNodesAtDistance(root,distance, list);
    return list;
  }

  public void traverseLevelOrder(){
    for(var i = 0 ; i <= height(); i++){ ;
      for (var value:getNodesAtDistance(i)) {
        System.out.println(value);
      }
    }
  }

  private void getNodesAtDistance(Node node, int distance, ArrayList<Integer> list){
    if (node == null)
      return;

    if (distance == 0) {
      list.add(node.value);
      return;
    }

    getNodesAtDistance(node.leftChild,distance -1, list);
    getNodesAtDistance(node.rightChild, distance -1, list);
  }

  public boolean isBinarySearchTree(){
    return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  private boolean isBinarySearchTree(Node node, int min, int max){
    if(node == null)
      return true;
    if(node.value < min || root.value > max)
      return false;
    return isBinarySearchTree(node.leftChild, min, root.value) && isBinarySearchTree(node.rightChild, node.value, max );
  }

  public boolean equals(Tree tree){
   if(tree == null)
     throw new IllegalArgumentException();
   if(root == null)
     throw new IllegalStateException();
   return equals(root, tree.root);
  }

  private boolean equals(Node first, Node second){
    if(first == null && second == null)
      return true;
    if(first != null && second != null)
    return first.equals(second)
        && equals(first.leftChild,second.leftChild)
        && equals(first.rightChild,second.rightChild);
    return false;
  }

  private int min(Node node){
    //Binary Tree
    if(isLeaf(node))
      return node.value;

    var left = min(node.leftChild);
    var right = min(node.rightChild);

    return Math.min(Math.min(left,right),node.value);
    }

  private void depthPreOrder(Node node){
    if(node == null)
      return;
    System.out.println(node.value);
    depthPreOrder(node.leftChild);
    depthPreOrder(node.rightChild);
  }

  private void depthInOrder(Node node){
    if(node == null)
      return;
    depthInOrder(node.leftChild);
    System.out.println(node.value);
  }

  private void depthPostOrder(Node node){
    if(node == null)
      return;
    depthPostOrder(node.leftChild);
    depthPostOrder(node.rightChild);
    System.out.println(node.value);
  }

  private boolean isLeaf(Node node){
    return node.rightChild == null && node.leftChild == null;
  }

  private class Node{
    private int value;
    private Node leftChild;
    private Node rightChild;

    public Node(int value){
      this.value = value;
    }

    @Override
    public String toString(){
      return "Node=" + value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return value == node.value && Objects.equals(leftChild, node.leftChild) && Objects.equals(rightChild, node.rightChild);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, leftChild, rightChild);
    }
  }
}
