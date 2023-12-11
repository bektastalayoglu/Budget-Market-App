package Algorithms;

public class PriorityQueue<E> {

    private class PriorityPair implements Comparable{
        private E element;
        private Object priority;

        public PriorityPair(E element, Object priority){
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

    public void push(E o, int priority){
        PriorityPair x = new PriorityPair(o, priority);
        data.addSorted(x);
    }

    public Object pop(){
        PriorityPair x = (PriorityPair) data.getFirst();
        data.removeFirst();
        return x;
    }

    public Object top(){
        return data.getFirst();
    }

    @Override
    public String toString() {
        return "data=" + data + '}';
    }
}
