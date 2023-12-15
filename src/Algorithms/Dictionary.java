package Algorithms;

public class Dictionary <K, V> {
    private class DictionaryPair implements Comparable {
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
        public int compareTo(Object o) {
            return 0;
        }
    }

    private Vector data;

    public Dictionary() {
        data = new Vector<>();
    }

    public void add(K key, V value) {
        int position = findPosition(key);
        if (position != -1) {
            DictionaryPair pair = (DictionaryPair) data.get(position);
            pair.setValue(value);
        } else {
            DictionaryPair newDict = new DictionaryPair(key, value);
            data.addLast(newDict);
        }

    }


    public int findPosition(K key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public V find(K key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null; // Key not found
    }

    public void removeKey(K key) {
        int index = findPosition(key);
        if (index != -1) {
            data.removeAt(index);
        }
    }

    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        String s ="";
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            s += "key:" + pair.key;
            s += " ";
            s += "value:" + pair.value;
            s += "\n";
        }
        return s;
    }

}
