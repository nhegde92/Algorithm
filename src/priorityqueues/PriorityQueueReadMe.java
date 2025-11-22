


/*

Time complexity for operations of priority queue

add()/offer() O(log n)
poll()	O(log n)
peek()	O(1)
size()	O(1)
isEmpty()	O(1)
contains() O(n)
toArray() O(n)


A Priority Queue in Java is a data structure that stores elements in a way that the element with
the highest (or lowest) priority is always at the front of the queue.

 Unlike a regular queue, where elements are processed in the order they arrive (FIFO – First In First Out),
  a priority queue ensures that elements with higher priority are dequeued before elements with lower priority,
   regardless of their insertion order.

Key Characteristics:
Element Ordering: The order of elements in the queue is determined by their priority.
 The priority is usually defined by a comparator or natural ordering of the elements.

Heap-based Implementation: Internally, a priority queue is often implemented using a heap,
specifically a binary heap (either max-heap or min-heap).
 This allows for efficient insertion and removal of the highest (or lowest) priority elements.

Dynamic: It adjusts dynamically as elements are added or removed.

Not thread-safe: By default, PriorityQueue is not thread-safe.
 If you need thread safety, you must manually synchronize the queue or use alternatives like
  PriorityBlockingQueue from java.util.concurrent.

Basic Operations:
Insertion (add() or offer()): Insert an element into the queue.

Removal (poll()): Removes and returns the element with the highest priority.

Peek (peek()): Returns the element with the highest priority without removing it.

Size (size()): Returns the number of elements in the queue.

Check if empty (isEmpty()): Checks if the queue is empty.


Examples
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create a priority queue of integers
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements to the priority queue
        pq.add(10);
        pq.add(20);
        pq.add(15);
        pq.add(5);

        // Printing the elements in the priority queue
        System.out.println("Priority Queue: " + pq);

        // Peek at the highest priority element (smallest value for a min-heap)
        System.out.println("Peek: " + pq.peek());

        // Removing the highest priority element (smallest value for a min-heap)
        System.out.println("Poll: " + pq.poll());

        // Printing the updated priority queue
        System.out.println("Priority Queue after poll: " + pq);
    }
}


import java.util.PriorityQueue;
import java.util.Comparator;

public class MaxHeapExample {
    public static void main(String[] args) {
        // Create a max-heap using a custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(15);
        maxHeap.add(5);

        // Print the elements in the priority queue (max-heap order)
        System.out.println("Max Heap: " + maxHeap);

        // Peek at the highest priority element (largest value for a max-heap)
        System.out.println("Peek: " + maxHeap.peek());

        // Removing the highest priority element (largest value for a max-heap)
        System.out.println("Poll: " + maxHeap.poll());

        // Print the updated max-heap
        System.out.println("Max Heap after poll: " + maxHeap);
    }
}

Custom objects
import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Implement the compareTo method to define priority based on age
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);  // Prioritize by age (ascending)
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create a PriorityQueue of Person objects
        PriorityQueue<Person> pq = new PriorityQueue<>();

        // Add custom Person objects to the queue
        pq.add(new Person("John", 25));
        pq.add(new Person("Alice", 30));
        pq.add(new Person("Bob", 20));
        pq.add(new Person("Charlie", 22));

        // Poll elements from the queue (youngest first)
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}



Operation	Time Complexity
add(E e)	O(log n)
offer(E e)	O(log n)
peek()	O(1)
poll()	O(log n)
remove(Object o)	O(n)
contains(Object o)	O(n)
size()	O(1)
isEmpty()	O(1)
Iteration	O(n)
 */



package priorityqueues;

public class PriorityQueueReadMe {


}
