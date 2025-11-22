package linkedlist;

public class RotateListByK {

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null)
            return head;
        int size = getSize(head);
        int totalRotation = k % size;
        if (k == 0 || totalRotation == 0 )
            return head;
        ListNode curr = head;
        for (int i = 0; i < size - totalRotation - 1; i++) {
            curr = curr.next;
        }

        ListNode nextNode = curr.next;
        curr.next = null;
        if (nextNode == null) return head;
        curr = nextNode;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;
        return nextNode;

    }


    public static int getSize(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public static void main(String args[]){
        ListNode ln = new ListNode();
        ListNode head = ln.createList(5);
        System.out.println(rotateRight(head,2));
    }

}
