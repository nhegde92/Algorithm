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

import java.util.*;

/*
     0 is not visited. 1 is visiting in the current cycle 2 visiting completed
    0->1-3. 0-2-3. first 0 goes vis[0] = 1, then comes 1 vis[1] = 1, then goes 3. Visited of
    vis[3] will be 2. Then vis[1] will be 2, then vis[2] = 1, then vis [2] = 2 then
    vis[1] = 2

 */
public class CourseScheduleBFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> order = new LinkedList<>();
        if (numCourses < 2)
            return true;
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preReq = prerequisites[i][1];
            adj.get(course).add(preReq);
            indegree[preReq]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);

        }

        //No indegree with 0,  so its a loop
        if (queue.size() == 0)
            return false;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            order.addFirst(course);
            List<Integer> sorroundingCourse = adj.get(course);
            for (int i = 0; i < sorroundingCourse.size(); i++) {
                indegree[sorroundingCourse.get(i)]--;
                if (indegree[sorroundingCourse.get(i)] == 0)
                    queue.add(sorroundingCourse.get(i));
            }
        }

        if (order.size() != numCourses)
            return false;
        System.out.println(order);
        return true;
    }

    public static void main(String args[]) {
        CourseScheduleBFS obj = new CourseScheduleBFS();
        System.out.println(obj.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(obj.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(obj.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));

    }

}
