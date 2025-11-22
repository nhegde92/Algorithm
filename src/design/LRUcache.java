package design;

import java.util.HashMap;

public class LRUcache {

    class Node {
        Node next;
        Node prev;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    HashMap<Integer, Node> nodeMap;
    Node head;
    Node tail;

    LRUcache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    int getKey(int key) {
        if (!nodeMap.containsKey(key)){
            System.out.println("No value found for key: "+ key);
            return -1;
        }
        Node node = nodeMap.get(key);
        removeNode(node);
        insertToTop(node);
        System.out.println("Value for key" + key +" is: "+ node.value);
        return node.value;
    }

    void putKey(int key, int value) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            removeNode(node);
            insertToTop(node);
            return;
        }

        if (nodeMap.size() >= capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            nodeMap.remove(lru.key);
        }

        Node newNode = new Node(key, value);
        insertToTop(newNode);
        nodeMap.put(key, newNode);
    }

    private void insertToTop(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String args[]){
        LRUcache obj = new LRUcache(3);
        obj.putKey(1,100);
        obj.putKey(2,200);
        obj.getKey(1); // 100
        obj.getKey(10); // -1
        obj.putKey(3,300);
        obj.putKey(4,400);
        obj.getKey(2); // -1
        obj.getKey(1);; // 100
        obj.getKey(3); // -1
        obj.getKey(4); // 400
    }
}
