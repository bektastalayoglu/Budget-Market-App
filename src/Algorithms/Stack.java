package Algorithms;

public class Stack<E> {
    private Vector<E> data;

    public Stack() {
        data = new Vector<>();
    }

    public void push(E o) {
        data.addLast(o);
    }

    public E pop() {
        int len = this.size();
        E obj = this.top();
        data.removeAt(len - 1);
        return obj;
    }

    public E top() {
        return (E) data.getLast();
    }

    public int size() {
        return data.size();
    }

    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

