package Algorithms;

/**
 * StackLinkedList class represents a stack using a linked list.
 *
 * @param <E> The type of elements in the stack.
 */
public class StackLinkedList<E extends Comparable> {
    private LinkedList data;

    public StackLinkedList() {
        data = new LinkedList();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param o The element to be pushed onto the stack.
     */
    public void push(E o) {
        data.addLast(o);
    }

    /**
     * Pops the element from the top of the stack and returns it.
     *
     * @return The element from the top of the stack.
     */
    public E pop() {
        int len = this.size();
        E obj = this.peek();
        data.removeLast();
        return obj;
    }

    /**
     * Returns the element from the top of the stack without removing it.
     *
     * @return The element from the top of the stack.
     */
    public E top() {
        return (E) data.getLast();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The size of the stack.
     */
    public int size() {
        return data.size();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean empty() {
        return data.isEmpty();
    }

    public E peek() {
        return (E) data.getLast();
    }

}


