package com.williampepin.trees;

import com.williampepin.hash.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
  public static int ALPHABET_SIZE = 26;

  private Node root = new Node(' ');

  public void insert(String word){
    var current = root;
    for(var ch: word.toCharArray()) {
      if(!current.hasChild(ch))
        current.addChild(ch);
      current = current.getChild(ch);
    }
    current.isEndOfWord = true;
  }

  public boolean contains(String word){
    if (word == null || word.isEmpty())
      return false;

    var current = root;
    for(var ch: word.toCharArray()){
      if(!current.hasChild(ch))
        return false;
      current = current.getChild(ch);
    }
    return current.isEndOfWord;
  }

  public void traverse(boolean postOrder){
    if(postOrder)
      traversePostOrder(root);
    else
      traversePreOrder(root);
  }

  public void remove(String word) {
    if(word == null)
      return;
    remove(root,word, 0);
  }

  public List<String> findWords(String prefix){
    List<String>  words = new ArrayList<>();
    var lastNode = findLastNodeOf(prefix);
    findWords(lastNode,prefix,words);
    return words;
  }

  private void findWords(Node node, String prefix, List<String> words){
    if(node == null)
      return;

    if(node.isEndOfWord)
      words.add(prefix);

    for (var child: node.getChildren())
      findWords(child,prefix + child.value, words);
  }

  private Node findLastNodeOf(String prefix){
    if(prefix == null)
      return null;
    var current = root;
    for (var ch : prefix.toCharArray()){
      var child = current.getChild(ch);
      if(child == null)
        return null;
      current = child;
    }
    return current;
  }

  private void remove(Node node, String word, int index){
    if(index == word.length())
      node.isEndOfWord = false;

    var ch = word.charAt(index);
    var child = node.getChild(ch);
    if (child == null)
      return;

    remove(child, word, index + 1);

    if(!child.hasChildren() && !child.isEndOfWord)
      root.removeChild(ch);
  }

  private void traversePreOrder(Node root){
    System.out.println(root.value);
    for (var child : root.getChildren())
      traversePreOrder(child );
  }

  private void traversePostOrder(Node root){
    for (var child : root.getChildren())
      traversePostOrder(child );
    System.out.println(root.value);
  }

  private class Node{
  private char value;
  private boolean isEndOfWord;
  private HashMap<Character, Node> children = new HashMap<>() ;

    public Node(char value) {
      this.value = value;
    }

    public boolean hasChild(char ch) {
      return children.containsKey(ch);
    }

    public boolean hasChildren(){
      return !children.isEmpty();
    }

    public void addChild(char ch){
      children.put(ch, new Node(ch));
    }

    public void removeChild(char ch){
      children.remove(ch);
    }

    public Node getChild(char ch){
      return children.get(ch);
    }

    public Node[] getChildren(){
      return children.values().toArray(new Node[0]);
    }

    @Override
    public String toString() {
      return "Node{" +
          "value=" + value +
          '}';
    }
  }
}
