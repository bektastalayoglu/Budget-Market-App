package Algorithms;

public class Queue<E>{
        private Vector<E> data;
        public Queue() {
            data = new Vector<>();
        }
        public void push(E o) {
            data.addLast(o);
        }
        public E pop() {
            E obj = (E) data.getFirst();
            data.removeAt(0);
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

}



