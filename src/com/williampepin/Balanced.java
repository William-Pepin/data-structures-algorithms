package com.williampepin;

import java.util.Arrays;
import java.util.List;

public class Balanced {

  private static final List<Character> leftBrackets = Arrays.asList('(','<','[','{');
  private static final List<Character> rightBrackets = Arrays.asList(')','>',']','}');

  public static boolean isBalanced(String input) {
    java.util.Stack<Character> stack = new java.util.Stack<>();

    for (char ch : input.toCharArray()) {
      if (isLeftBracket(ch))
        stack.push(ch);

      if (isRightBracket(ch)) {
        if (stack.empty()) return false;

        if (!bracketsMatch(stack.pop(), ch)) return false;
      }
    }
    return stack.empty();
  }

  private static boolean isLeftBracket(char ch){
    return leftBrackets.contains(ch);
  }

  private static boolean isRightBracket(char ch){
    return rightBrackets.contains(ch);
  }

  private static boolean bracketsMatch(char left, char right){
    return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
  }
}
