package graphs;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */

/*
     0 is not visited. 1 is visiting in the current cycle 2 visiting completed
    0->1-3. 0-2-3. first 0 goes vis[0] = 1, then comes 1 vis[1] = 1, then goes 3. Visited of
    vis[3] will be 2. Then vis[1] will be 2, then vis[2] = 1, then vis [2] = 2 then
    vis[1] = 2

 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseScheduleDFS {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> order = new ArrayList<>();
        if (numCourses < 2)
            return true;
        // init adj list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int[] vis = new int[numCourses];//0 = not visited, 1 = visiting 2 = processed
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preReq = prerequisites[i][1];
            adj.get(course).add(preReq);
        }

        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0 && hasCycle(i, vis, adj, order))
                return false;

        }

        System.out.println("order is " + order);
        return true;
    }

    public boolean hasCycle(int index, int[] vis, HashMap<Integer, List<Integer>> adj, List<Integer> order) {
        if (vis[index] == 1)
            return true;
        vis[index] = 1;
        for (int i = 0; i < adj.get(index).size(); i++) {
            if (vis[adj.get(index).get(i)] == 2)
                continue;
            if (hasCycle(adj.get(index).get(i), vis, adj, order))
                return true;
        }
        vis[index] = 2;
        order.add(index);
        return false;
    }

    public static void main(String args[]) {
        CourseScheduleDFS obj = new CourseScheduleDFS();
        System.out.println(obj.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(obj.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(obj.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));

    }

}
