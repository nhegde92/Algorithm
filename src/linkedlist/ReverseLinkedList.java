package linkedlist;

public class ReverseLinkedList {

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }


    public static void main(String args[]) {
        ListNode ln = new ListNode();
        ListNode head = ln.createList(5);
        ReverseLinkedList rl = new ReverseLinkedList();
        System.out.println(rl.reverse(head));
    }
}


//Iterative solution

    /* We have 1->2->3->4->5. In order to revers it we need to get
       5->4->3->2->1. basically we need to set all the pointers in the
       reverse direction. We need to keep track of 3 variables,
       prev initialized to null, curr initialized to head, and next
       which will be head.next. For every iteration change the pointer
       and increment current.
    */