package Algorithms.Dictionary;

import Algorithms.Vector.Vector;

/**
 * Author : Bektas Talayoglu
 * Description : Dictionary class represents a simple dictionary with key-value pairs.
 * It uses a Vector-based data structure to store the pairs.
 *
 * @param <K> The type of keys in the dictionary.
 * @param <V> The type of values associated with the keys.
 **/

public class Dictionary<K extends Comparable<K>, V> {
    private class DictionaryPair implements Comparable<DictionaryPair> {
        private K key;
        private V value;

        public DictionaryPair(K key, V value) {
            this.key = key;
            this.value = value;
        }


        /**
         * Gets the key of the pair.
         *
         * @return The key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Sets the key of the pair.
         *
         * @param key The new key.
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Gets the value associated with the key.
         *
         * @return The value.
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value associated with the key.
         *
         * @param value The new value.
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Compares this pair with another pair based on their keys.
         */

        @Override
        public int compareTo(DictionaryPair o) {
            return this.key.compareTo(o.key);
        }
    }

    /**
     * The underlying data structure to store key-value pairs
     */
    private Vector<DictionaryPair> data;

    /**
     * Default constructor for the Dictionary class.
     * Initializes the underlying vector.
     */
    public Dictionary() {
        data = new Vector<>();
    }

    /**
     * Adds a key-value pair to the dictionary. If the key already exists, updates the associated value.
     *
     * @param key   The key to add or update.
     * @param value The value associated with the key.
     */
    public void add(K key, V value) {
        int position = findPosition(key);
        if (position != -1) {
            DictionaryPair pair = data.get(position);
            pair.setValue(value);
        } else {
            DictionaryPair newDict = new DictionaryPair(key, value);
            data.addLast(newDict);
        }

    }

    /**
     * Finds the position of a key in the dictionary.
     *
     * @param key The key we are looking for
     * @return The index of the key if found, otherwise -1.
     */
    public int findPosition(K key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = data.get(i);
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds and returns the value associated with a given key.
     *
     * @param key The key we are looking for
     * @return The value associated with the key, or null if the key is not found.
     */
    public V find(K key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = data.get(i);
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * Removes a key and its associated value from the dictionary.
     *
     * @param key The key to remove.
     */
    public void removeKey(K key) {
        int index = findPosition(key);
        if (index != -1) {
            data.removeAt(index);
        } else {
            System.out.println("Key can not find");
        }
    }

    /**
     * Gets the size of key-value pairs in the dictionary.
     *
     * @return The size of the dictionary.
     */
    public int size() {
        return data.size();
    }

    /**
     * Convert the dictionary to a string representation as key-value pair
     *
     * @return String representation of the dictionary
     **/
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = data.get(i);
            s += "key:" + pair.key + " " + "value:" + pair.value + "\n";
        }
        return s;
    }

    /**
     * Stores and returns the values in the dictionary
     *
     * @return Vector : values in the dictionary
     */
    public Vector<V> values() {
        Vector<V> values = new Vector<>();
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = data.get(i);
            values.addLast(pair.getValue());
        }
        return values;
    }

    /**
     * Stores and returns the keys in the dictionary
     *
     * @return Vector : keys in the dictionary
     */
    public Vector<K> keys() {
        Vector<K> keys = new Vector<>();
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = data.get(i);
            keys.addLast(pair.getKey());
        }
        return keys;
    }

}
