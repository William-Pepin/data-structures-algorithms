package com.williampepin.graphs;

import com.williampepin.ArrayQueue;

import java.util.*;

public class Graph {

  private Map<String, Node> nodes = new HashMap<>();
  private Map<Node, List<Node>> adjacencyList = new HashMap<>();

  public void addNode(String label){
    var node = new Node(label);
    nodes.putIfAbsent(label, node);
    adjacencyList.putIfAbsent(node, new ArrayList<>());
  }

  public void addEdge(String from, String to) {
    var fromNode = nodes.get(from);
    if (fromNode == null)
      throw new IllegalArgumentException();

    var toNode = nodes.get(to);
    if (toNode == null)
      throw new IllegalArgumentException();

    adjacencyList.get(fromNode).add(toNode);
  }

  public void removeNode(String label){
    var node = nodes.get(label);
    if(node == null)
      return;

    for(var n : adjacencyList.keySet())
      adjacencyList.get(n).remove(node);

    adjacencyList.remove(node);
    nodes.remove(node);
  }

  public void removeEdge(String from, String to){
    var fromNode = nodes.get(from);
    var toNode = nodes.get(to);

    if(fromNode == null || toNode == null)
      return;

    adjacencyList.get(fromNode).remove(toNode);
  }

  public void print () {
    for (var source : adjacencyList.keySet()) {
      var targets = adjacencyList.get(source);
      if (!targets.isEmpty())
        System.out.println(source + " is connected to " + targets);
    }
  }

  public void traverseDepthFirstIterative(String label){
   var node = nodes.get(label);
   if(node == null)
     return;

   var visited = new HashSet<Node>();
   var stack = new Stack<Node>();
   stack.push(node);

   while(!stack.isEmpty()){
     var current = stack.pop();

     if(visited.contains(current))
       continue;

     System.out.println(current);;
     visited.add(current);

     for (var neighbor : adjacencyList.get(current))
       if(!visited.contains(neighbor))
         stack.push(neighbor);
   }
  }

  public void traverseBreadthFirstIterative(String label){
    var node = nodes.get(label);
    if(node == null)
      return;

    var visited = new HashSet<Node>();
    var queue = new ArrayDeque<Node>();
    queue.add(node);

    while(!queue.isEmpty()){
      var current = queue.remove();

      if(visited.contains(current))
        continue;

      System.out.println(current);;
      visited.add(current);

      for (var neighbor : adjacencyList.get(current))
        if(!visited.contains(neighbor))
          queue.add(neighbor);
    }
  }

  public List<String> topologicalSort(){
    var visited = new HashSet<Node>();
    var stack = new Stack<Node>();

    for(var node : nodes.values())
      topologicalSort(node, visited, stack);

    var sorted = new ArrayList<String>();
    while(!stack.empty())
      sorted.add(stack.pop().label);

    return sorted;
  }

  private void topologicalSort(Node node, Set<Node> visited, Stack<Node> stack){
    if(visited.contains(node))
      return;

    visited.add(node);

    for(var neighbor: adjacencyList.get(node))
      topologicalSort(neighbor,visited,stack);

    stack.push(node);
  }

  public boolean hasCycle(){
    var all = new HashSet<Node>();
    all.addAll(nodes.values());

    var visiting = new HashSet<Node>();
    var visited = new HashSet<Node>();

    while(!all.isEmpty()){
      var current = all.iterator().next();
      if(hasCycle(current, all, visiting, visited))
        return true;
    }
    return false;
  }

  private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited){
    all.remove(node);
    visiting.add(node);

    for(var neighbor : adjacencyList.get(node)){
      if(visited.contains(neighbor))
        continue;

      if(visiting.contains(neighbor))
        return true;

      if(hasCycle(neighbor, all,visiting,visited))
        return true;
    }

    visiting.remove(node);
    visited.add(node);

    return false;
  }

  public void traverseDepthFirst(String label){
    var node = nodes.get(label);
    if(node == null)
      return;
    traverseDepthFirst(node, new HashSet<>());
  }

  private void traverseDepthFirst(Node node, Set<Node> visited){
    System.out.println(node);
    visited.add(node);

    for(var neighbor : adjacencyList.get(node)){
      if(!visited.contains(neighbor))
        traverseDepthFirst(neighbor, visited);
    }
  }

  private class Node {
      private String label;

      public Node(String label) {
        this.label = label;
      }

      @Override
      public String toString() {
        return label;
      }
    }
  }
