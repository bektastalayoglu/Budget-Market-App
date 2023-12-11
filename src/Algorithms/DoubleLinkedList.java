package Algorithms;

public class DoubleLinkedList<E>  {

    private class DoubleLinkedListElement{
        private E data;
        private DoubleLinkedListElement previousElement;
        private DoubleLinkedListElement nextElement;

        public DoubleLinkedListElement(E v, DoubleLinkedListElement next, DoubleLinkedListElement previous) {
            data = v;
            nextElement = next;
            previousElement = previous;

            if(nextElement != null){
                nextElement.previousElement = this;
            }
            if(previousElement != null){
                previousElement.nextElement = this;
            }
        }

        public DoubleLinkedListElement(E v){
            this(v, null, null);
        }

        public DoubleLinkedListElement previous(){
            return previousElement;
        }
        public E value(){
            return data;
        }
        public DoubleLinkedListElement next(){
            return nextElement;
        }

        public void setNext(DoubleLinkedListElement value){
            nextElement = value;
        }

        public void setPrevious(DoubleLinkedListElement value){
            previousElement = value;
        }
    }

    private int count;
    private DoubleLinkedListElement head;
    private DoubleLinkedListElement tail;


    // First when we initialized double linked list is empty.
    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public E getFirst(){
        return head.value();
    }

    public E getLast(){
        return tail.value();
    }
    public boolean isEmpty(){
        return count == 0;  // head == null
    }

    public int size(){
        return count;
    }

    public void addFirst(E data){
        head = new DoubleLinkedListElement(data, head, null);
        if(tail == null) {
            tail =head;
        }
        count++;
    }

    public void addLast(E data){
        tail = new DoubleLinkedListElement(data, null, tail);
        if(head == null) {
            head = tail;
        }
        count++;
    }

    public void removeFirst(){
        if(isEmpty()){
            System.out.println("List is empty...");
        }
        DoubleLinkedListElement temp = head;
        if(head == tail){
            tail = null;
        }else{
            head.nextElement.previousElement = null;
        }
        head = head.nextElement;
        temp.setNext(null);
        head.setPrevious(null);
        count--;
    }

    public void removeLast(){
        if(isEmpty()){
            System.out.println("List is empty...");
        }
        DoubleLinkedListElement temp = tail;
        if(tail == head){
            head = null;
        }else{
            tail.previousElement.setNext(null);
        }
        tail = tail.previousElement;
        temp.setPrevious(null);
        count--;
    }

    public void printReverse(){
        if(tail == null){
            return;
        }
        DoubleLinkedListElement temp = tail;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.previousElement;
        }
    }

    public String toString() {
        String s = "(";
        DoubleLinkedListElement temp = head;
        while (temp != null){
            s += temp.value().toString();
            s += " ";
            temp = temp.next();
        }
        s += ")";
        return s;
    }

    // Return values from specific index
    public E get(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Invalid Index");;
        }

        DoubleLinkedListElement temp = null;
        if (index < size() / 2) {
            temp = head;
            for (int i = index; i > 0; i--) {
                temp =temp.next();
            }
        } else {
            temp = tail;
            for (int i = size() - index - 1; i > 0; i--) {
                temp = temp.previous();
            }
        }
        return temp.value();
    }

    public Object search(Object k){
        DoubleLinkedListElement temp = head;
        while(temp != null && temp.value() != k){
            temp = temp.next();
        }
        return temp;
    }

}



