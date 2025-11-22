package linkedlist;

/*

Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.


Example 1:

Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]


 */

public class ReverseLinkedList2 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (right <= left || head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        //Move prev to one step before curr. prev will be in 1.
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        //Cant traverse forward if prev is null
        if (prev == null || prev.next == null)
            return head;

        //Reverse starting the next, 2 in our example. The prev should attach to reveresed list.
        prev.next = reverselinkedlist(prev.next, right - left);
        return dummy.next;
    }

    //Reverse the linked list. Keep the prev to null. 2->3->4
    //becomes 4->3->2.Also save the curr in a separate variable.
    //That way we can append it to 2. So we will be returning 4-3-2-5

    public ListNode reverselinkedlist(ListNode head, int count) {

        ListNode prev = null;
        ListNode curr = head;
        while (count >= 0 && curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count--;
        }
        head.next = curr;
        return prev;
    }

    public static void main(String args[]) {
        ReverseLinkedList2 rl = new ReverseLinkedList2();
        ListNode ll = new ListNode();
        ListNode head = ll.createList(5);
        System.out.println(rl.reverseBetween(head, 2, 4));
    }
}
