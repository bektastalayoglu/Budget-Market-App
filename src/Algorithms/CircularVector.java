package Algorithms;

public class CircularVector<E> {

    private Vector<E> data;
    private int first;
    private int count;
    private int capacity;

    public CircularVector(int capacity) {
        count = 0;
        first = 0;
        data = new Vector<E>(capacity);
        this.capacity = capacity;
    }

    public int size() {
        return count;
    }

    public void addFirst(E element) {
        if (count == capacity) {
            data.extendCapacity();
        }

        // Calculate the index where the element should be added
        first = (first - 1 + capacity) % capacity;
        data.set(first, element);
        count++;
    }

    public void addLast(E element) {
        if (count == capacity) {
            data.extendCapacity();
        }

        // Calculate the index where the element should be added
        int index = (first + count) % capacity;
        data.set(index, element);
        count++;
    }

    public E getFirst() {
        if (count > 0) {
            return data.get(first);
        }
        return null; // or throw an exception, depending on your requirements
    }

    public E getLast() {
        if (count > 0) {
            int lastIndex = (first + count - 1) % capacity;
            return data.get(lastIndex);
        }
        return null; // or throw an exception, depending on your requirements
    }

    public void removeFirst() {
        if (count > 0) {
            first = (first + 1) % capacity;
            count--;
        }
    }

    public void removeLast() {
        if (count > 0) {
            count--;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            int index = (first + i) % capacity;
            s.append(data.get(index)).append(" ");
        }
        s.append("] ");
        return s.toString();
    }
}

