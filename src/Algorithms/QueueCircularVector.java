package Algorithms;


public class QueueCircularVector<E>{
    private CircularVector<E> data;

    public QueueCircularVector() {
        data = new CircularVector<>();
    }

    public QueueCircularVector(int capacity) {
        data = new CircularVector<>(capacity);
    }

    public void push(E o) {
        data.addLast(o);
    }
    public E pop() {
        E obj = (E) data.getFirst();
        data.removeFirst();
        return obj;
    }
    public E top() {
        return (E) data.getFirst();
    }

    public int size() {
        return data.size();
    }
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        return "Queue{" +
                "data=" + data +
                '}';
    }
}




