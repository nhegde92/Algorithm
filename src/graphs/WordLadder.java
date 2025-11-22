package graphs;

import java.util.*;

/*

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */

/*
Logic: add the first word in the queue. For every iteration for the size of the queue add qualifying words.
Time: O(N * L²), where N = number of words, L = word length.
Space: O(N + L²)
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 0;
        if (!wordList.contains(endWord) || beginWord.equals(endWord))
            return res;
        HashSet<String> vis = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        vis.add(beginWord);
        res++;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int j = 0; j<size; j++) {
                String currWord = queue.poll();
                if (currWord.equals(endWord))
                    return res;
                for (int i = 0; i < wordList.size(); i++) {
                    if (!vis.contains(wordList.get(i)) && checkWordQualifies(currWord, wordList.get(i))) {
                        queue.add(wordList.get(i));
                        vis.add(wordList.get(i));
                    }
                }
            }
            res++;
        }

        return res;
    }

    private boolean checkWordQualifies(String currWord, String tempWord) {
        int diff = 0;
        if(Math.abs(currWord.length() - tempWord.length()) > 1)
            return false;
        char[]src = currWord.toCharArray();
        char[]dest = tempWord.toCharArray();
        for(int i = 0; i<src.length; i++){
            if(src[i] != dest[i])
                diff++;
            if(diff > 1)
                return false;
        }
        return diff == 1;
    }

    public static void main(String args[]){
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(wl.ladderLength("hit", "aaa", Arrays.asList("hot","dot","dog","lot","log","cog")));

    }

}
