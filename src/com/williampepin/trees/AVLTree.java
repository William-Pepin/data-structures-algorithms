package com.williampepin.trees;

public class AVLTree {

  private Node root;

  public void insert(int value){
    root = insert(root, value);
  }

  private Node insert(Node node, int value){
    if(node == null)
      return new Node(value);

    if (value < root.value)
      node.leftChild = insert(root.leftChild, value);
    else
      node.rightChild = insert(root.rightChild, value);

    setHeight(node);

    return balance(node);
  }

  private Node balance(Node node){
    if(isLeftHeavy(node)) {
      if(balanceFactor(node.leftChild) < 0)
        root.leftChild = rotateLeft(node.leftChild);
      return rotateRight(node);
    }
    else if (isRightHeavy(node)){
      if(balanceFactor(node.rightChild) > 0)
        node.rightChild = rotateRight(node.rightChild);
      return rotateLeft(node);
    }
    return root;
  }

  private Node rotateLeft(Node node){
    var newRoot = node.rightChild;

    node.rightChild = newRoot.leftChild;
    newRoot.leftChild = node;

    setHeight(node);
    setHeight(newRoot);

    return newRoot;
  }

  private Node rotateRight(Node node){
    var newRoot = node.leftChild;

    node.leftChild = newRoot.rightChild;
    newRoot.rightChild = node;

    setHeight(node);
    setHeight(newRoot);

    return newRoot;
  }

  private void setHeight(Node node){
    node.height = Math.max(
        height(node.leftChild),
        height(node.rightChild)) + 1;
  }

  private boolean isLeftHeavy(Node node){
    return balanceFactor(node) > 1;
  }

  private boolean isRightHeavy(Node node){
    return balanceFactor(node) < -1;
  }

  private int balanceFactor(Node node){
    return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
  }


  private int height(Node node){
    return node == null ? -1 : node.height;
  }

  private class Node {
    private int value;
    public int height;

    private Node leftChild;
    private Node rightChild;

    public Node(int value)
    {
      this.value = value;
    }

    @Override
    public String toString(){
      return "Value=" + this.value;
    }

  }
}
