package Algorithms.Vector;

/**
 * Author : Bektas Talayoglu
 * Description : Implementation of Vector Data Structure
 *
 * @param <E> The generic types of elements in the vector.
 **/

public class Vector<E> {
    private Object[] data;
    private int count;
    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;

    /**
     * Default Constructor
     * Default size 10
     */
    public Vector() {
        data = new Object[DEFAULT_CAPACITY];
        count = 0;
        this.capacity = DEFAULT_CAPACITY;
    }


    /**
     * Constructor with initial capacity
     *
     * @param capacity - Initially given capacity
     **/
    public Vector(int capacity) {
        data = new Object[capacity];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * Size of vector
     *
     * @return count
     **/
    public int size() {
        return count;
    }


    /**
     * Check the vector is empty or not
     * returns true or false
     **/
    public boolean isEmpty() {
        return size() == 0;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Get element at a specified index
     *
     * @param index : index of the element to get
     * @return data : element at the specified index
     **/
    public E get(int index) {
        return (E) data[index];
    }

    /**
     * Set element at a specified index
     *
     * @param index - index of the element to set
     * @param obj   - Element to be set
     **/
    public void set(int index, E obj) {
        data[index] = obj;
    }

    /**
     * Check if the vector contains a specific element
     *
     * @param obj - Element to check for existence
     * @return true if the element is found, false otherwise
     **/
    public boolean contains(E obj) {
        for (int i = 0; i < count; i++) {
            if (data[i] == obj)
                return true;
        }
        return false;
    }

    /**
     * Extend the capacity of the vector by doubling its size
     **/
    public void extendCapacity() {
        // Define new capacity if capacity is zero use default capacity
        int newCapacity;
        if (data.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = data.length * 2;
        }
        // create a new array (twice the count)
        Object[] newArr = new Object[newCapacity];
        // copy all the existing items
        for (int i = 0; i < count; i++) {
            newArr[i] = data[i];
        }
        // set 'array' to this new array
        data = newArr;
    }

    /**
     * Add an element to the beginning of the vector
     *
     * @param item - Element to be added
     **/
    public void addFirst(E item) {
        if (data.length == count)
            extendCapacity();
        // we start left shifting from the last item
        for (int i = count; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = item;
        count++;
    }

    /**
     * Add an element to the end of the vector
     *
     * @param o - Element to be added
     **/
    public void addLast(E o) {
        // If the array is full, extend the capacity
        if (data.length == count)
            extendCapacity();

        data[count] = o;
        count++;
    }

    /**
     * Get the first element of the vector
     *
     * @return The first element
     **/
    public E getFirst() {
        return (E) data[0];
    }

    /**
     * Get the last element of the vector
     *
     * @return The last element
     **/
    public E getLast() {
        return (E) data[count - 1];
    }

    /**
     * Remove the last element from the vector
     **/
    public void removeLast() {
        count--;
    }

    /**
     * Remove the first element from the vector
     * It shifts elements to the left and decreases the count
     **/
    public void removeFirst() {
        for (int i = 0; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
    }

    /**
     * Convert the vector to a string representation
     *
     * @return String representation of the vector
     **/
    public String toString() {
        String str = "[ ";
        for (int i = 0; i < count; i++) {
            str += data[i].toString() + " ";
        }
        str += "]";
        return str;
    }


    /**
     * Reverse the order of elements in the vector
     **/
    public void reverse() {
        for (int i = 0; i < count / 2; i++) {
            swap(i, count - i - 1);
        }
    }

    /**
     * Swap is helper method for reverse method.
     **/
    private void swap(int in1, int in2) {
        E temp = (E) data[in1];
        data[in1] = data[in2];
        data[in2] = temp;
    }

    /**
     * Repeat the elements in the vector
     *
     * @return Object
     **/
    public Vector<E> repeat() {
        Vector<E> repeatVect = new Vector<>(count * 2);
        for (int i = 0; i < count; i++) {
            repeatVect.addLast((E) data[i]);
            repeatVect.addLast((E) data[i]);
        }
        return repeatVect;
    }

    /**
     * Remove the element from the vector according to its index
     *
     * @param index index of the element
     **/
    public void removeAt(int index) {
        // Check the index
        if (index < 0 || index >= count)
            throw new ArrayIndexOutOfBoundsException();
        // Shift the items to the left
        for (int i = index; i < count; i++) {
            data[i] = data[i + 1];
        }
        count--;
    }

    /**
     * Find the element from the vector and return it
     *
     * @param obj element you're looking for
     * @return E element you find, if it does not exist null
     **/
    public E find(E obj) {
        for (int i = 0; i < count; i++) {
            if (((Comparable) data[i]).compareTo(obj) == 0) {
                return (E) data[i];
            }
        }
        return null;
    }
}