package Algorithms;

/**
 * CircularVector class represents a circular vector using a vector
 *
 * @param <E> The type of elements in the circular vector.
 */
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

    /**
     * Returns the number of elements in the circular vector.
     *
     * @return The size of the circular vector.
     */
    public int size() {
        return count;
    }

    /**
     * Checks if the circular vector is empty.
     *
     * @return True if the circular vector is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Adds an element to the front of the circular vector.
     *
     * @param element The element to be added.
     * @throws ArrayIndexOutOfBoundsException if the circular vector is full.
     */
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

    /**
     * Adds an element to the end of the circular vector.
     *
     * @param element The element to be added.
     * @throws ArrayIndexOutOfBoundsException if the circular vector is full.
     */
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

    /**
     * Returns the element from the front of the circular vector.
     *
     * @return The element from the front of the circular vector.
     */
    public E getFirst() {
        if (count > 0) {
            return data.get(first);
        }
        return null; // or throw an exception, depending on your requirements
    }

    /**
     * Returns the element from the end of the circular vector.
     *
     * @return The element from the end of the circular vector.
     */
    public E getLast() {
        if (count > 0) {
            int lastIndex = (first + count - 1) % capacity;
            return data.get(lastIndex);
        }
        return null; // or throw an exception, depending on your requirements
    }

    /**
     * Removes the element from the front of the circular vector.
     */
    public void removeFirst() {
        if (count > 0) {
            first = (first + 1) % capacity;
            count--;
        }
    }

    /**
     * Removes the element from the end of the circular vector.
     */
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

