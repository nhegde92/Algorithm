package linkedlist;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int value) {
        this.val = value;

    }

    public ListNode(){

    }
    public ListNode(int value, ListNode next) {
        this.val = value;
        this.next = next;
    }

    public ListNode createList(int size){
        if(size == 0 )
            return null;
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for(int i = 2; i<=size; i++){
            ListNode next = new ListNode(i);
            curr.next = next;
            curr = curr.next;
        }
        return head;
    }

    @Override
    public String toString() {
        return val + " -> " + (next != null ? next.toString() : "null");
    }

}
