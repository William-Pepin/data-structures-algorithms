package com.williampepin;

import java.util.Queue;

public class Reversed {
  public static void reverse(Queue<Integer> queue){
    if(queue.isEmpty()) return;
    java.util.Stack<Integer> stack = new java.util.Stack<>();

    while (!queue.isEmpty())
      stack.push(queue.remove());

    while (!stack.empty())
      queue.add(stack.pop());
  }
}
