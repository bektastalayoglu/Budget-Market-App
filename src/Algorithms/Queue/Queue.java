package Algorithms.Queue;

import Algorithms.Vector.Vector;

/**
 * Author : Bektas Talayoglu
 * Description : Queue class represents a queue using a Vector.
 * @param <E> The type of elements in the queue.
 **/
public class Queue<E> {
    private Vector<E> data;

    public Queue() {
        data = new Vector<>();
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param o The element to be added.
     */
    public void push(E o) {
        data.addLast(o);
    }

    /**
     * Removes and returns the element from the front of the queue.
     *
     * @return The element from the front of the queue.
     */
    public E pop() {
        E obj = (E) data.getFirst();
        data.removeAt(0);
        return obj;
    }

    /**
     * Returns the element from the front of the queue without removing it.
     *
     * @return The element from the front of the queue.
     */
    public E top() {
        return (E) data.getFirst();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return data.size();
    }


    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean empty() {
        return data.isEmpty();
    }

}



