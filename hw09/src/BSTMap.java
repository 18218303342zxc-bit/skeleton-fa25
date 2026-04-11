import java.util.Iterator;
import java.util.Set;



public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V>{

    private int size;

    private class BSTNode{
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K Key,V Value){
            key = Key;
            value = Value;
            left = null;
            right = null;
        }
    }

    private BSTNode root;


    public BSTMap(){
        root = null;
        size = 0;

    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = helper(root,key,value);
        size ++;
    }



    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return find(root,key);
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (find(root,key) == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        delete(root);
        size = 0;

    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private BSTNode helper (BSTNode ref,K key,V value) {
        if (ref == null || ref.key.compareTo(key) == 0) {
            if (ref != null) {
                ref = null;
            }
            return new BSTNode(key,value);
        } else if (ref.key.compareTo(key) > 0 ) {
            ref.left = helper(ref.left,key,value);
        } else if (ref.key.compareTo(key) < 0) {
            ref.right = helper(ref.right,key,value);
        }
        return ref;

    }

    private V find(BSTNode ref,K key) {
        if (ref == null) {
            return null;
        }
        if (ref.key.compareTo(key) == 0) {
            return ref.value;
        } else if (ref.key.compareTo(key) > 0) {
            return find(ref.left,key);
        } else {
            return find(ref.right,key);
        }
    }

    private void delete(BSTNode ref) {
        if (ref == null){
            return;
        }
        if (ref.left == null && ref.right == null) {
            ref = null;
            return;
        }
        delete(ref.right);
        delete(ref.left);
        delete(ref);
    }
}
