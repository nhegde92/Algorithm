package datastructures;

import java.util.HashMap;
import java.util.Map;
/*


| Method               | Signature                                                                                    | Return Type | Returns                                                                                                         |
| -------------------- | -------------------------------------------------------------------------------------------- | ----------- | --------------------------------------------------------------------------------------------------------------- |
| **putIfAbsent**      | `V putIfAbsent(K key, V value)`                                                              | `V`         | - Existing value if key is present<br>- `null` if new key-value pair added                                      |
| **computeIfPresent** | `V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)` | `V`         | - New value if updated<br>- `null` if key is absent or mapping removed                                          |
| **computeIfAbsent**  | `V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)`                 | `V`         | - Existing value if present<br>- New computed value if absent<br>- `null` if result is null                     |
| **compute**          | `V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)`          | `V`         | - New value after compute<br>- `null` if result is null (removes key)                                           |
| **merge**            | `V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)`   | `V`         | - Merged value if key present<br>- Provided value if key was absent<br>- `null` if result is null (removes key) |

 */
public class HashMapAdvanced {

    public static void demonstrateAdvancedOperations() {
        Map<String, Integer> map = new HashMap<>();

        System.out.println("Initial map: " + map);

        // putIfAbsent - inserts only if key is absent
        map.putIfAbsent("apple", 10);
        map.putIfAbsent("banana", 20);
        map.putIfAbsent("apple", 50);  // Will NOT overwrite existing "apple"
        System.out.println("After putIfAbsent: " + map);

        // computeIfPresent - update value if key exists
        map.computeIfPresent("apple", (key, val) -> val + 5);  // apple: 10 + 5 = 15
        map.computeIfPresent("cherry", (key, val) -> 30);     // cherry absent, no change
        System.out.println("After computeIfPresent: " + map);

        // computeIfAbsent - add entry if key is absent
        map.computeIfAbsent("banana", key -> 25);  // banana exists, no change
        map.computeIfAbsent("cherry", key -> 30);  // cherry added with 30
        System.out.println("After computeIfAbsent: " + map);

        // compute - compute new value regardless of presence
        map.compute("apple", (key, val) -> (val == null) ? 100 : val * 2);  // apple: 15 * 2 = 30
        map.compute("date", (key, val) -> 40);                              // date added with 40
        System.out.println("After compute: " + map);

        // merge - insert or combine values
        map.merge("apple", 10, (oldVal, newVal) -> oldVal + newVal);  // apple: 30 + 10 = 40
        map.merge("banana", 5, (oldVal, newVal) -> oldVal - newVal);  // banana: 20 - 5 = 15
        map.merge("elderberry", 50, (oldVal, newVal) -> oldVal + newVal); // elderberry added with 50
        System.out.println("After merge: " + map);
    }

    public static void main(String[] args) {
        demonstrateAdvancedOperations();
    }
}

