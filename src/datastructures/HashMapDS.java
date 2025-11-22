/*
✅ Basic Concepts
Hashing: The key’s hashCode() is used to compute the index (bucket) in the underlying array where the entry will be stored.

Collision Handling: Handled using a linked list or balanced tree (since Java 8) when many keys hash to the same index.

1. put(K key, V value)
Description: Inserts or updates a key-value pair in the map.

Average case complexity: O(1)

Worst case complexity: O(n) (if many keys hash to the same bucket)

Details: On average, the operation is fast due to good hashCode distribution and regular resizing (rehashing). Java 8+ uses a balanced tree in a bucket if collisions exceed a threshold, improving worst-case to O(log n).
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2. get(Object key)
Description: Retrieves the value associated with a specified key.

Average case complexity: O(1)

Worst case complexity: O(n) (high collision scenario), O(log n) with tree bucket (Java 8+)

Details: Efficient on average; degrades to O(n) if all elements land in a single bucket. Since Java 8, this is improved to O(log n) per bucket if collisions are excessive.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3. remove(Object key)
Description: Removes the mapping for a key if present.

Average case complexity: O(1)

Worst case complexity: O(n)

Details: Works similarly to get(), since it first locates the bucket, then removes the node (or tree entry) containing the key.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

4. containsKey(Object key)
Description: Checks if a key exists.

Complexity: Same as get(): O(1) average, O(n) worst case, or O(log n) for tree buckets.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

5. containsValue(Object value)
Description: Checks if at least one key maps to the specified value.

Complexity: O(n)

Details: Unlike containsKey(), it must iterate over all entries to find a value.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

6. size()
Description: Returns the number of key-value pairs.

Complexity: O(1)
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

7. clear()
Description: Removes all mappings.

Complexity: O(1) (effectively resets the table — actual memory cleanup may take longer)
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

8. keySet(), values(), entrySet()
Description: Returns a set view of the keys, values, or key-value pairs.

Complexity: O(1) for initial call, O(n) to iterate the entire set/view.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


| Operation                     | Description                                                         | Average Time | Worst Time |
| ----------------------------- | ------------------------------------------------------------------- | ------------ | ---------- |
| `put(K key, V value)`         | Inserts a key-value pair into the map. Updates value if key exists. | O(1)         | O(n)       |
| `get(Object key)`             | Retrieves the value associated with the key.                        | O(1)         | O(n)       |
| `remove(Object key)`          | Removes the key-value pair for the specified key.                   | O(1)         | O(n)       |
| `containsKey(Object key)`     | Checks if the map contains the specified key.                       | O(1)         | O(n)       |
| `containsValue(Object value)` | Checks if the map contains the specified value.                     | O(n)         | O(n)       |
| `size()`                      | Returns the number of key-value pairs.                              | O(1)         | O(1)       |
| `isEmpty()`                   | Checks if the map is empty.                                         | O(1)         | O(1)       |
| `clear()`                     | Removes all mappings from the map.                                  | O(n)         | O(n)       |
| `keySet()`                    | Returns a Set view of keys.                                         | O(n)         | O(n)       |
| `values()`                    | Returns a Collection view of values.                                | O(n)         | O(n)       |
| `entrySet()`                  | Returns a Set view of key-value mappings.                           | O(n)         | O(n)       |

*/



package datastructures;

import java.util.Map;
import java.util.HashMap;

public class HashMapDS {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // 1. put(K key, V value)
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("apple", 15);  // Updates existing key
        System.out.println("After put: " + map);  // {apple=15, banana=20}

        // 2. get(Object key)
        System.out.println("Get 'apple': " + map.get("apple"));    // 15
        System.out.println("Get 'orange': " + map.get("orange"));  // null

        // 3. remove(Object key)
        map.remove("banana");
        System.out.println("After remove: " + map);  // {apple=15}

        // 4. containsKey(Object key)
        System.out.println("Contains key 'apple': " + map.containsKey("apple"));  // true
        System.out.println("Contains key 'banana': " + map.containsKey("banana")); // false

        // 5. containsValue(Object value)
        System.out.println("Contains value 15: " + map.containsValue(15));  // true
        System.out.println("Contains value 20: " + map.containsValue(20));  // false

        // 6. size()
        System.out.println("Size: " + map.size());  // 1

        // 7. isEmpty()
        System.out.println("Is empty: " + map.isEmpty());  // false

        // 8. clear()
        map.clear();
        System.out.println("After clear: " + map);  // {}

        // 9. keySet(), values(), entrySet()
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);

        System.out.println("Key Set: " + map.keySet());       // [apple, banana, cherry]
        System.out.println("Values: " + map.values());        // [10, 20, 30]
        System.out.println("Entry Set:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        for(String key: map.keySet()){
            int value = map.get(key);
            System.out.println(key + " - " + value);
        }
        for(int value: map.values()){
            System.out.println(value);
        }
        map.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }


}
