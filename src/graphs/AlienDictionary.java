package graphs;

import java.util.*;


/*
There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.

If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".

 */

/*
Tinme O(N * L)

Get a list of all the characters that are used. Unique only
Create a adj list for every letter. its okay to consider the difference in only one word.
Do DFS on it.
 */

class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words.length == 0)
            return "";

        HashMap<Character, Set<Character>> adj = new HashMap<>();
        Set<Character> letters = new HashSet<>();
        int vis[] = new int[26];
        StringBuilder result = new StringBuilder("");
        constructGraph(words, adj, letters);
        System.out.println(adj);
        System.out.println(letters);
        for (char c : letters) {
            if (vis[c - 'a'] == 0 && hasCycle(c, result, adj, vis))
                return "";
        }

        return result.reverse().toString();
    }

    public boolean hasCycle(char c, StringBuilder result, HashMap<Character, Set<Character>> adj, int vis[]) {

        // There is a cycle.
        if (vis[c - 'a'] == 1)  // ASCII of 'a' is 97 and '0' is 48. so e-'a' =  4 and 5-'0' = 5
            return true;

        // already processed
        if (vis[c - 'a'] == 2)
            return false;

        vis[c - 'a'] = 1; // processing

        // DFS
        Set<Character> neighbors = adj.get(c);
        for (char curr : neighbors) {
            if (hasCycle(curr, result, adj, vis))
                return true;
        }
        result.append(c);
        vis[c - 'a'] = 2;
        return false;
    }

    public void constructGraph(String[] words, HashMap<Character, Set<Character>> adj, Set<Character> letters) {

        // Find all the unique letters
        // Create a adj key for all unique index;
        for (String word : words) {
            char wordArray[] = word.toCharArray();
            for (char c : wordArray) {
                letters.add(c);
                adj.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            char[] currWord = words[i].toCharArray();
            char[] nextWord = words[i + 1].toCharArray();

            int minLen = Math.min(currWord.length, nextWord.length);
            boolean diffFound = false;
            for (int j = 0; j < minLen; j++) {
                if(currWord[j] == nextWord[j])
                    continue;

                // add to adj list if the letter not present. ( Set takes care of this. with list use contains. )
                adj.get(currWord[j]).add(nextWord[j]);
                diffFound = true;
                break;
            }

            // two words same, not a valid dictionary. //["wrt","wrtkj"] is valid. Thus cuuWord <= next word
            if (!diffFound && currWord.length > nextWord.length) {

                letters.clear();
                return;
            }

        }
    }

    // Main method for testing
    public static void main(String[] args) {
        AlienDictionary sol = new AlienDictionary ();

        // Example test case
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Alien Order: " + sol.alienOrder(words1));
        // Expected output: "wertf" or a valid ordering that satisfies constraints

        // Another test case with invalid dictionary (prefix problem)
        String[] words2 = {"abc", "ab"};
        System.out.println("Alien Order: " + sol.alienOrder(words2));
        // Expected output: "" (empty string)

        // Single word case
        String[] words3 = {"z"};
        System.out.println("Alien Order: " + sol.alienOrder(words3));
        // Expected output: "z"

        // Empty input case
        String[] words4 = {};
        System.out.println("Alien Order: " + sol.alienOrder(words4));
        // Expected output: ""
    }
}
