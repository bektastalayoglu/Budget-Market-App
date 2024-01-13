/**
 * Author : Bektas Talayoglu
 * Description : LinkedList class represents a generic singly linked list.
 *
 * @param <E> The generic types of elements in the linked list.
 **/

package Algorithms.List;

public class LinkedList<E extends Comparable<E>> {
    private class ListElement {
        private E value;
        private ListElement next;

        public ListElement(E el, ListElement nextElement) {
            value = el;
            next = nextElement;
        }

        public ListElement(E el) {
            this(el, null);
        }

        public E getValue() {
            return value;
        }

        public ListElement getNext() {
            return next;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNext(ListElement value) {
            next = value;
        }

    }

    private ListElement head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    /**
     * Adds an element to the front of the linked list.
     *
     * @param o The element to be added.
     */
    public void addFirst(E o) {
        head = new ListElement(o, head);
        count++;
    }

    /**
     * Returns the element from the front of the linked list.
     *
     * @return The element from the front of the linked list.
     */
    public E getFirst() {
        return head.getValue();
    }

    /**
     * Returns the element at the specified index in the linked list.
     *
     * @param n The index of the element to retrieve.
     * @return The element at the specified index.
     */
    public E get(int n) {
        ListElement d = head;
        for (int i = 0; i < n; i++) {
            d = d.next;
        }
        return d.getValue();
    }

    public String toString() {
        String s = "(";
        ListElement d = head;
        while (d != null) {
            s += d.getValue().toString();
            s += " ";
            d = d.next;
        }
        s += ")";
        return s;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return The size of the linked list.
     */
    public int size() {
        return count;
    }

    /**
     * Sets the element at the specified index in the linked list to the specified value.
     *
     * @param n The index of the element to set.
     * @param o The value to set the element to.
     */
    public void set(int n, E o) {
        ListElement d = head;
        for (int i = 0; i < n; i++) {
            d = d.next;
        }
        d.setValue(o);
    }

    /**
     * Returns the last element in the linked list.
     *
     * @return The last element in the linked list.
     */
    public E getLast() {
        if (head == null) {
            return null;
        }

        ListElement d = head;
        while (d.next != null) {
            d = d.next;
        }
        return d.value;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param o The element to be added.
     */
    public void addLast(E o) {
        ListElement newElement = new ListElement(o);

        if (head == null) {
            // If the list is empty, set the new element as the head
            head = newElement;
        } else {
            ListElement current = head;

            // Traverse the list until the last element is reached
            while (current.next != null) {
                current = current.next;
            }
            // Set the next element of the current last element to the new element
            current.setNext(newElement);
        }
    }

    /**
     * Searches for the specified element in the linked list.
     *
     * @param o The element to search for.
     * @return True if the element is found, false otherwise.
     */
    public boolean search(Comparable o) {
        ListElement current = head;
        while (current.next != null) {
            if (current.value == o) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void fropple() {
        if (head == null || head.next == null) {
            // If the list is empty or has only one element, no fropple needed
            return;
        }

        ListElement current = head;
        ListElement next = current.next;
        ListElement previous = null;

        // Update the head to the second element
        head = next;

        while (current != null && next != null) {
            // Fropple the current and next elements
            current.setNext(next.next);
            next.setNext(current);

            // Connect the previous element to the froppled pair
            if (previous != null) {
                previous.setNext(next);
            }

            // Move to the next pair
            previous = current;
            current = current.next;

            if (current != null) {
                next = current.next;
            }
        }
    }

    /**
     * Appends the elements of another linked list to the end of this linked list.
     *
     * @param list2 The linked list to append.
     */
    public void append(LinkedList list2) {
        if (list2.head == null) {
            // If list2 is empty, nothing to append
            return;
        }
        // Append elements of list2 to the end of list1
        ListElement list2Current = list2.head;
        while (list2Current != null) {
            addLast(list2Current.value);
            list2Current = list2Current.next;
        }
    }

    /**
     * Removes the last element from the linked list.
     */
    public void removeLast() {
        if (head == null) {
            System.out.println("List is empty...");
        }

        ListElement current = head;
        ListElement previous = null;

        // Traverse the list until the last element is reached
        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        // If the list has only one element, set head to null
        if (previous == null) {
            head = null;
        } else {
            // Otherwise, remove the last element by setting the next of the previous to null
            previous.setNext(null);
        }
    }

    /**
     * Removes the first element from the linked list.
     */
    public void removeFirst() {
        if (head == null) {
            throw new NullPointerException();
        }

        ListElement second = head.next;
        head.next = null;
        head = second;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return True if the linked list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an element to the linked list in sorted order.
     *
     * @param o The element to be added.
     */
    public void addSorted(E o) {
        // an empty list, add element in front
        if (head == null)
            head = new ListElement(o, null);
        else if (head.value.compareTo(o) > 0) {
            // we have to add the element in front
            head = new ListElement(o, head);
        } else {
            // we have to find the first element which is bigger
            ListElement d = head;
            while ((d.next != null) && (d.next.value.compareTo(o) < 0)) {
                d = d.next;
            }
            ListElement next = d.next;
            d.setNext(new ListElement(o, next));
        }
        count++;
    }

    /**
     * This method delete index nth element from the linked list.
     * @param n : index
     */
    public void delete(int n) {

        if (n == 0) {
            removeFirst();
        } else {
            ListElement previous = head;
            int i = 1;

            while (i < n) {
                previous = previous.next;
                i++;
            }

            ListElement current = previous.next;
            previous.setNext(current.next);

            current.setNext(null);

            count--;
        }

        if (n < 0 || n >= count) {
            throw new IndexOutOfBoundsException();
        }

    }


}

