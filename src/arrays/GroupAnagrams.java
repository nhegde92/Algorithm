package arrays;

/*
Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: strs = ["act","pots","tops","cat","stop","hat"]

Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
Example 2:

Input: strs = ["x"]

Output: [["x"]]
Example 3:

Input: strs = [""]

Output: [[""]]
 */

/*

The core idea is to build a unique encoding for each string based on its character frequency.
This encoding ensures that all anagrams will generate the same key, allowing us to group them together.
We use a fixed-size array of 26 elements (for each lowercase letter) to count the frequency of characters in
the string. Then, we convert this array into a string key by appending each count followed by a
delimiter (e.g., #), to avoid ambiguity.
At the end, we return the cumulative values from the map, where each key corresponds to a
list of anagrams

Space and Time Complexity would be O(m*n) where m is total string and n is the length of longest string


An alternative approach is to sort each string alphabetically and use the sorted string as the map key.
Since anagrams share the same letters in different orders, sorting them ensures identical keys for all anagram groups.
We then group strings in the map based on this sorted key.

The time complexity would be O(n*mlogn)


Note for multilingual code we need to use unicode normalization and sorting approach. Unicode normalization is needed
because the sorting order is not respected well when strings are in multi language.
Anagrams may visually look the same but differ in underlying encoding due to combining characters.

Example:

"é" == "e\u0301" (e + combining acute accent)
In such cases, you'll need to normalize strings before sorting to make sure they match.

  charMap[c-'a']++;  // a = 97 b = 98 and so on b-a = 1 so charMap[1]++ means there is one b

similarly
char c = '7';
int value = c - '0'; = 7  '0' → 48  '7' → 55  55 - 48 = 7


 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List <List< String >> groupAnagrams(String[] strs) {

        List<List<String>> res  = new ArrayList<>();
        HashMap<String, List<String>> hm = new HashMap<>();
        for(int i = 0; i<strs.length; i++){
            int []charMap = new int[26];
            char cArray[] = strs[i].toCharArray();
            StringBuffer sb = new StringBuffer();
            for(char c: cArray){
                charMap[c-'a']++;
            }
            for(int j = 0; j<charMap.length; j++){
                sb.append(charMap[j]); //make a unique encoding.
                sb.append('#'); //delimiter
            }
            String key = sb.toString();
            hm.putIfAbsent(key, new ArrayList<String>());
            hm.get(key).add(strs[i]);
        }

        for(List<String> value: hm.values()){
            res.add(value);
        }
        return res;

    }

    public static void main(String []args){
        GroupAnagrams ag = new GroupAnagrams();
        System.out.println(ag.groupAnagrams( new String[]{"act","pots","tops","cat","stop","hat"}));
    }

}
