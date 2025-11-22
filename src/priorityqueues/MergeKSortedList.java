
/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 */


package priorityqueues;




 /*

 put all the heads in the min heap. Now point current to dummy.
 poll the least. add to current.next = pq.poll(). move current to the
 least node. Add the next node in that linkedList to pq

 while(pqisNotEMpty){
    ListNode node = pq.poll();
    current.next = node; //add a node to next
    current = current.next. //move the current to head;
    if(node.next != null) //add the next node to PQ if present.a
        pq.add(node.next);
 }
Let’s say the lists look like this:

List 1: 1 -> 4

List 2: 2 -> 5

List 3: 3 -> 6

Heap at start: [1, 2, 3]

Step-by-step:
pq.poll() gets 1

current.next = 1 adds 1 to the result

current = current.next moves to node 1

pq.offer(4) adds 4 to the heap

Result so far: 1

Heap: [2, 3, 4]


 */


import java.util.PriorityQueue;

import linkedlist.ListNode;

public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        // Min-heap based on ListNode's value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-null list to the heap
        for (ListNode list : lists) {
            if (list != null)
                pq.offer(list);
        }

        // Dummy head to build the result list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge nodes from the heap
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;

            if (node.next != null)
                pq.offer(node.next);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode().createList(3); // creates 1 -> 2 -> 3
        ListNode l2 = new ListNode(1, new ListNode(4, new ListNode(5, null)));
        ListNode[] lists = new ListNode[]{l1, l2};

        MergeKSortedList merger = new MergeKSortedList();
        ListNode result = merger.mergeKLists(lists);

        System.out.println(result);
    }
}

