package Algorithms;

public class StackLinkedList<E extends Comparable>
{
    private LinkedList data;
    public StackLinkedList ()
    {
        data = new LinkedList();
    }

    public void push ( E o )
    {
        data.addLast(o);
    }
    public E pop ()
    {
        int len = this.size();
        E obj = this.peek();
        data.removeLast();
        return obj;
    }
    public E top ()
    {
        return (E) data.getLast();
    }
    public int size ()
    {
        return data.size();
    }
    public boolean empty ()
    {
        return data.isEmpty();
    }

    public E peek() {
        return (E) data.getLast();
    }

}


