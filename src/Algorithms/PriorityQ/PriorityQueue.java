package Algorithms.PriorityQ;

import Algorithms.List.LinkedList;
import Algorithms.Vector.Vector;

/**
 * Author : Bektas Talayoglu
 * Description : PriorityQueue class represents a priority queue.
 *
 * @param <E> The generic types of elements in the queue.
 **/
public class PriorityQueue<E, P> {

    private class PriorityPair implements Comparable {
        private E element;
        private P priority;

        public PriorityPair(E element, P priority) {
            this.element = element;
            this.priority = priority;
        }

        public int compareTo(Object o) {
            PriorityPair p2 = (PriorityPair) o;
            return ((Comparable) priority).compareTo(p2.priority);
        }

        @Override
        public String toString() {
            return "{" +
                    element +
                    ", priority=" + priority +
                    '}';
        }

    }

    private LinkedList data;

    public PriorityQueue() {
        data = new LinkedList();
    }

    /**
     * Adds an element with the specified priority to the priority queue.
     *
     * @param o        The element to be added.
     * @param priority The priority associated with the element.
     */
    public void push(E o, P priority) {
        PriorityPair x = new PriorityPair(o, priority);
        data.addSorted(x);
    }

    /**
     * Removes and returns the element with the highest priority from the priority queue.
     *
     * @return The element with the highest priority.
     */
    public E pop() {
        PriorityPair x = (PriorityPair) data.getFirst();
        data.removeFirst();
        return x.element;
    }

    /**
     * Returns the element with the highest priority without removing it from the priority queue.
     *
     * @return The element with the highest priority.
     */
    public E top() {
        return ((PriorityPair) data.getFirst()).element;
    }

    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return "data=" + data + '}';
    }

    public E get(int index) {
        PriorityPair pair = (PriorityPair) data.get(index);
        return pair.element;
    }

    public void remove(int index){
        data.delete(index);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    public PriorityQueue<E, P> copy(){
        // Create a new priority queue to store the copy
        PriorityQueue<E, P> copy = new PriorityQueue<>();

        // Loop over the elements of the original priority queue
        for(int i = 0; i < size(); i++){
            // Get the element and the priority at the current index
            E element = get(i);
            P priority = ((PriorityPair) data.get(i)).priority;

            // Push the element and the priority to the copy priority queue
            copy.push(element, priority);
        }

        // Return the copy priority queue
        return copy;
    }

}
