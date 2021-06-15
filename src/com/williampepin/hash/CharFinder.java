package com.williampepin.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharFinder {
    // using hashmap to store number of repeated char
    // returns the first char with a 1 repeated in the hash
    public char findFirstNonRepeatingChar(String str){
        Map<Character, Integer> map = new HashMap<>();
        var chars = str.toCharArray();

        for(var ch : chars){
            var count = map.containsKey(ch) ? map.get(ch) : 0;
            map.put(ch,count + 1);
        }

        for (var ch : chars){
            if(map.get(ch) == 1)
                return ch;
        }
        return Character.MIN_VALUE;
    }

    // use set to contain char, if a second char appears of the same key
    // return the char
    // set is hashmap with only key no value
    public char findFirstRepeatedChar(String str){
        Set<Character> set = new HashSet<>();

        for(char ch : str.toCharArray()){
            if(set.contains(ch))
                return ch;
            set.add(ch);
        }
        return Character.MIN_VALUE;
    }
}
