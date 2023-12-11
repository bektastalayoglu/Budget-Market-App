package Algorithms;

// based on a double linked list
public class QueueLinkedList<E>{
    private DoubleLinkedList<E> data;
    public QueueLinkedList() {
        data = new DoubleLinkedList<>();
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
        return "data= " + data;
    }
}



