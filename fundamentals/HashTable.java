/**
 * Hash table = hash map = dictionary = associative array
 * 
 * variations:
 * 
 * fixed size: predetermined number of buckets
 * dynamic: number of buckets may increase based on load factor
 *   * all-at-once: reallocate new array of buckets and rehash everything at once (expensive)
 *   * incremental: keep old and new buckets; for each insertion, move k elements
 *     to new table and insert new element only in new table. for each lookup/delete,
 *     check first new table then old table. Deallocate old table once all are moved
 * open addressing/closed hashing: if hash collision, probe linearly
 *   to next available bucket (no linked list for each bucket => no chaining)
 *   * limits # of entries to # of buckets
 *   * hash function must avoid clustering (as well as being uniform)
 *   * advantage is avoiding indirection of having pointer to linked list
 *       ==> only good if stored values are small (char/int); else 
 *       you lose the cache advantage 
 */
import java.util.ArrayList;

public class HashTable<K, V> {
    public static void main(String args[]) {

        // map names to addresses
        HashTable<String, String> addresses = new HashTable<>();
        addresses.put("Axel", "Some street");
        addresses.delete("Axelf");
        System.out.println(addresses.get("Axel"));
    }

    ArrayList<LinkedList<K, V>> buckets;
    final int nBuckets = 1000;
    public HashTable() {
        buckets = new ArrayList<>(nBuckets);
        for (int i = 0; i < nBuckets; i++) {
            buckets.add(null);
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % nBuckets; // remove sign bit
    }

    public void put(K key, V value) {
        int index = hash(key);

        LinkedList<K, V> chain = buckets.get(index);
        if (chain == null) {
            chain = new LinkedList<>();
        }
        chain.add(key, value);
        buckets.set(index, chain);
    }

    public V get(K key) {
        int index = hash(key);

        LinkedList<K, V> chain = buckets.get(index);
        if (chain == null) {
            return null;
        }
        return chain.get(key);
    }

    public void delete(K key) {
        int index = hash(key);

        LinkedList<K, V> chain = buckets.get(index);
        if (chain == null) {
            return;
        }
        chain.remove(key);
    }
}