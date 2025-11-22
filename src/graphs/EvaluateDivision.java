/*You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

 Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
        return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:

 1 <= equations.length <= 20
equations[i].length == 2
        1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
        1 <= queries.length <= 20
queries[i].length == 2
        1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.*/

/*

Learn

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Node(b, val));
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Node(a, 1.0 / val));

 */
package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EvaluateDivision {
    public double[] evaluateDivision(String[][] equations, String[][] queries, double[] values) {
        double res[] = new double[queries.length];
        if (queries.length == 0 || equations.length == 0)
            return res;

        HashMap<String, List<Node>> adj = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] currEquation = equations[i];
            String src = currEquation[0];
            String dest = currEquation[1];
            double value = values[i];
            Node currNode = new Node(dest, value);
            Node inverseNode = new Node(src, 1 / value);
            if (adj.containsKey(src)) {
                List<Node> list = adj.get(src);
                list.add(currNode);
            } else {
                List<Node> list = new ArrayList<>();
                list.add(currNode);
                adj.put(src, list);
            }

            if (adj.containsKey(dest)) {
                List<Node> list = adj.get(dest);
                list.add(inverseNode);
            } else {
                List<Node> list = new ArrayList<>();
                list.add(inverseNode);
                adj.put(dest, list);
            }

        }

        for(int i =0 ; i<queries.length; i++){
            String src = queries[i][0];
            String dest = queries[i][1];
            if(!adj.containsKey(src) || !adj.containsKey(dest))
                res[i] = -1.0;
            else if(src.equals(dest))
                res[i] = 1.0;
            else {
                HashSet<String> vis =new HashSet<String>();
                res[i] = dfs(src, dest, adj, 1.0, vis);
            }

        }

        return res;
    }

    public double dfs(String src, String dest, HashMap<String, List<Node>> adj, double res,  HashSet<String> vis){
        if(src.equals(dest))
            return res;
        if(vis.contains(src) || !adj.containsKey(src))
            return -1.0;
        vis.add(src);
        for(int i = 0; i<adj.get(src).size(); i++){
            double result =  dfs(adj.get(src).get(i).dest, dest, adj, res*adj.get(src).get(i).value, vis);
            if (result != -1.0) { //Don't break early if -1.  explore all the other paths. if not -1 then we have the result
                return result;
            }

        }
        return -1.0;
    }

    public static void main (String args[]){
        EvaluateDivision ed = new EvaluateDivision();
        String  [][]equations = {{"a","b"},{"b","c"},{"bc","cd"}};
        double values[] = {1.5,2.5,5.0};
        String  [][] queries = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
        double res[] = ed.evaluateDivision(equations,queries,values);
        for(int i = 0; i<res.length; i++){
            System.out.println(res[i]);
        }

    }
}
