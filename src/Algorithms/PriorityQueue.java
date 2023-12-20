package Algorithms;

/**
 * PriorityQueue class represents a priority queue.
 *
 * @param <E> The type of elements in the priority queue.
 */
public class PriorityQueue<E, P> {

    private class PriorityPair implements Comparable{
        private E element;
        private P priority;

        public PriorityPair(E element, P priority){
            this.element = element;
            this.priority = priority;
        }

        public int compareTo(Object o){
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

    public PriorityQueue(){
        data = new LinkedList();
    }

    /**
     * Adds an element with the specified priority to the priority queue.
     *
     * @param o        The element to be added.
     * @param priority The priority associated with the element.
     */
    public void push(E o, P priority){
        PriorityPair x = new PriorityPair(o, priority);
        data.addSorted(x);
    }

    /**
     * Removes and returns the element with the highest priority from the priority queue.
     *
     * @return The element with the highest priority.
     */
    public Object pop(){
        PriorityPair x = (PriorityPair) data.getFirst();
        data.removeFirst();
        return x;
    }

    /**
     * Returns the element with the highest priority without removing it from the priority queue.
     *
     * @return The element with the highest priority.
     */
    public Object top(){
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return "data=" + data + '}';
    }

    public E get(int index){
        PriorityPair pair = (PriorityPair) data.get(index);
        return pair.element;
    }
}
