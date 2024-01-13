package Algorithms.PriorityQ;

import Algorithms.Tree.Tree;

/**
 * PriorityQueueTree class represents a priority queue using tree.
 *
 * @param <E> The type of elements in the priority queue tree.
 */
public class PriorityQueueTree<E, P> {

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

    // implementing a priority queue using a binary search tree.
    private Tree priorityTree;

    public PriorityQueueTree(){
        priorityTree = new Tree();
    }

    /**
     * Adds an element with the specified priority to the priority queue tree.
     *
     * @param o        The element to be added.
     * @param priority The priority associated with the element.
     */
    public void push(E o, P priority){
        PriorityPair x = new PriorityPair(o, priority);
        priorityTree.insert(x);
    }

    /**
     * Removes and returns the element with the highest priority from the priority queue tree.
     * To do this first we find max node value which is node has the highest priority in tree.
     *
     * @return The element with the highest priority.
     */
    public E pop(){
        PriorityPair x = (PriorityPair) priorityTree.findMaxNode().getValue();
        priorityTree.remove(x);
        return x.element;
    }

    /**
     * Returns the element with the highest priority without removing it from the priority queue tree.
     *
     * @return The element with the highest priority.
     */
    public E top(){
        return ((PriorityPair)priorityTree.findMaxNode().getValue()).element;
    }

    /**
     * Size of the priority queue tree is actually depth of the tree
     * @return depth of the tree
     */
    public int size() {
        return priorityTree.depth();
    }

    @Override
    public String toString() {
        return "data=" + priorityTree + '}';
    }


}
