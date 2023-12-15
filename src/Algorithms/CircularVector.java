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

    // This empty constructor use default vector capacity which is 10.
    public CircularVector(){
        count = 0;
        first = 0;
        data = new Vector<E>();
        this.capacity = data.getCapacity();
    }

    public int size() {
        return count;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void addFirst(E element) {
        if (capacity <= count) {
            throw new ArrayIndexOutOfBoundsException();
        }else{
            // Calculate the index where the element should be added
            first = (first - 1 + capacity) % capacity;
            data.set(first, element);
            count++;
        }
    }

    public void addLast(E element) {
        if (capacity <= count) {
            throw new ArrayIndexOutOfBoundsException();
        }else{
            // Calculate the index where the element should be added
            int index = (first + count) % capacity;
            data.set(index, element);
            count++;
        }
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
        String str = "[";
        for (int i = 0; i < count; i++) {
            int index = (first + i) % capacity;
            str += data.get(index).toString() + " ";
        }
        str += "]";
        return str;
    }
}

