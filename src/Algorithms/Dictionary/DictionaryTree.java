package Algorithms.Dictionary;

import Algorithms.Tree.Tree;
import Algorithms.Vector.Vector;

/**
 * Author: Bektas Talayoglu
 * Description: Dictionary class represents a simple dictionary with key-value pairs.
 * It uses a Tree-based data structure to store the pairs.
 *
 * @param <K> The type of keys in the dictionary.
 * @param <V> The type of values associated with the keys.
 **/
public class DictionaryTree<K extends Comparable<K>, V> {
    private class DictionaryPair implements Comparable<DictionaryPair> {
        private K key;
        private V value;

        public DictionaryPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(DictionaryPair o) {
            return key.compareTo(o.key);
        }

        /**
         * Convert the dictionary to a string representation as key-value pair
         *
         * @return String representation of the dictionary pair
         **/
        @Override
        public String toString() {
            return "DictionaryPair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * The underlying data structure to store key-value pairs
     */
    private Tree<DictionaryPair> data;

    /**
     * Default constructor for the Dictionary class.
     * Initializes the underlying tree.
     */
    public DictionaryTree() {
        data = new Tree<>();
    }

    /**
     * Adds a key-value pair to the dictionary tree. If the key already exists, updates the associated value.
     *
     * @param key   The key to add or update.
     * @param value The value associated with the key.
     */
    public void add(K key, V value) {
        DictionaryPair newDict = new DictionaryPair(key, value);
        if (data.find(newDict) != null) {
            DictionaryPair pair = data.find(newDict);
            pair.setValue(value);
        } else {
            data.insert(newDict);
        }
    }

    /**
     * Finds and returns the value associated with a given key.
     *
     * @param key The key we are looking for
     * @return The value associated with the key, or null if the key is not found.
     */
    public V find(K key) {
        DictionaryPair resultPair = data.find(new DictionaryPair(key, null));

        if (resultPair != null && resultPair.getKey().equals(key)) {
            return resultPair.getValue();
        }

        return null;
    }

    /**
     * Removes a key and its associated value from the dictionary tree.
     *
     * @param key The key to remove.
     */
    public void removeKey(K key) {
        DictionaryPair resultPair = data.find(new DictionaryPair(key, null));
        if (resultPair != null) {
            data.remove(resultPair);
        }else{
            System.out.println("Key can not find");
        }
    }

    /**
     * Stores and returns the values in the dictionary tree.
     *
     * @return Vector : values in the dictionary tree
     */
    public Vector<V> values() {
        Vector<V> values = new Vector<>();
        for (int i = 0; i < data.nodeValues().size(); i++) {
            DictionaryPair pair = data.nodeValues().get(i);
            values.addLast(pair.getValue());
        }
        return values;
    }

    /**
     * Stores and returns the keys in the dictionary tree
     *
     * @return Vector : keys in the dictionary tree
     */
    public Vector<K> keys() {
        Vector<K> keys = new Vector<>();
        for (int i = 0; i < data.nodeValues().size(); i++) {
            DictionaryPair pair = data.nodeValues().get(i);
            keys.addLast(pair.getKey());
        }
        return keys;
    }

    /**
     * Gets the size of key-value pairs in the dictionary tree.
     *
     * @return The depth of the dictionary tree.
     */
    public int size() {
        return data.depth();
    }

    /**
     * Convert the dictionary to a string representation
     *
     * @return String representation of the dictionary
     **/
    @Override
    public String toString() {
        return "data={" + data.breadthSearch() + '}';
    }

}
