package Algorithms;

public class Dictionary {
    private class DictionaryPair implements Comparable {
        private Object key;
        private Object value;

        public DictionaryPair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
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

    public void add(Object key, Object value) {
        int position = findPosition(key);
        if (position != -1) {
            DictionaryPair pair = (DictionaryPair) data.get(position);
            pair.setValue(value);
        } else {
            DictionaryPair newDict = new DictionaryPair(key, value);
            data.addLast(newDict);
        }

    }


    public int findPosition(Object key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public Object find(Object key) {
        for (int i = 0; i < data.size(); i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null; // Key not found
    }

    public void removeKey(Object key) {
        int index = findPosition(key);
        if (index != -1) {
            data.removeAt(index);
        }
    }

    public int size() {
        return data.size();
    }

}
