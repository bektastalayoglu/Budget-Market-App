package Algorithms.Tree;

import Algorithms.Queue.Queue;
import Algorithms.Stack.Stack;
import Algorithms.Vector.Vector;

public class Tree<E extends Comparable<E>> {
    // the class for implementing a node in the tree.
    // contains a value, a pointer to the left child and a pointer to the right child
    public class TreeNode implements Comparable {
        private E value;
        private TreeNode leftNode;
        private TreeNode rightNode;
        private TreeNode parentNode;

        public TreeNode(E v) {
            this(v, null, null, null);
        }

        public TreeNode(E v, TreeNode left, TreeNode right, TreeNode parent) {
            value = v;
            leftNode = left;
            rightNode = right;
            parentNode = parent;
        }

        public TreeNode getLeftTree() {
            return leftNode;
        }

        public TreeNode getRightTree() {
            return rightNode;
        }

        public E getValue() {
            return value;
        }

        @Override
        public int compareTo(Object arg0) {
            return value.compareTo(((TreeNode) arg0).value);
        }
    }

    // the root of our tree
    protected TreeNode root;

    public Tree() {
        root = null;
    }


    /**
     * Traverses the tree in a specified order and performs an action on each node.
     *
     * @param action The action to be performed on each node.
     */
    public void traverse(TreeAction action) {
        Queue t = new Queue<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = (TreeNode) t.pop();
            action.run(n);
            if (n.getLeftTree() != null) {
                t.push(n.getLeftTree());
            }
            if (n.getRightTree() != null) {
                t.push(n.getRightTree());
            }
        }
    }

    /**
     * Helper method for recursive traversal of the tree in-order.
     *
     * @param n      The current node.
     * @param action The action to be performed on each node.
     */
    private void traverseNode(TreeNode n, TreeAction action) {
        if (n != null) {
            if (n.getLeftTree() != null) traverseNode(n.getLeftTree(), action);
            action.run(n);
            if (n.getRightTree() != null) traverseNode(n.getRightTree(), action);
        }
    }

    /**
     * Traverses the tree in-order and performs an action on each node.
     *
     * @param action The action to be performed on each node.
     */
    public void traverseInOrder(TreeAction action) {
        traverseNode(root, action);
    }

    /**
     * Inserts an element into the binary tree.
     *
     * @param element The element to be inserted.
     */
    public void insert(E element) {
        insertAtNode(element, root, null);
    }

    // we traverse the tree.
    // Current holds the pointer to the TreeNode we are currently checking
    // Parent holds the pointer to the parent of the current TreeNode
    private void insertAtNode(E element, TreeNode current, TreeNode parent) {
        // if the node we check is empty
        if (current == null) {
            TreeNode newNode = new TreeNode(element);
            // the current node is empty, but we have a parent
            if (parent != null) {
                // do we add it to the left?
                if (element.compareTo(parent.value) < 0) {
                    parent.leftNode = newNode;
                }
                // or do we add it to the right?
                else {
                    parent.rightNode = newNode;
                }
                newNode.parentNode = parent;
            }
            // the current node is empty, and it has no parent, we actually have an empty tree
            else root = newNode;

        } else if (element.compareTo(current.value) == 0) {
            // if the element is already in the tree, what to do?
            throw new UnsupportedOperationException();
        }
        // if the element is smaller than current, go left
        else if (element.compareTo(current.value) < 0) {
            insertAtNode(element, current.getLeftTree(), current);
        }
        // if the element is bigger than current, go right
        else insertAtNode(element, current.getRightTree(), current);
    }

    private void transplant(TreeNode oldNode, TreeNode newNode) {
        if (oldNode.parentNode == null) {
            root = newNode;
        } else if (oldNode.parentNode.leftNode == oldNode) {
            oldNode.parentNode.leftNode = newNode;
        } else {
            oldNode.parentNode.rightNode = newNode;
        }
        if (newNode != null) {
            newNode.parentNode = oldNode.parentNode;
        }
    }

    public void remove(E element) {
        removeNode(element, root);
    }

    private void removeNode(E element, TreeNode current) {
        if (current == null) {
            return;
        } else if (element.compareTo(current.value) == 0) {
            if(current.leftNode == null) transplant(current, current.rightNode);
            else if (current.rightNode == null) transplant(current, current.leftNode);
            else {
                // To find the smallest node which is bigger
                TreeNode y = minNode(current.rightNode);
                if(y.parentNode != current){
                    transplant(y, y.rightNode);
                    y.rightNode = current.rightNode;
                    y.rightNode.parentNode = y;
                }
                transplant(current, y);
                y.leftNode = current.leftNode;
                y.leftNode.parentNode = y;
            }
        } else if (element.compareTo(current.value) < 0) {
            removeNode(element, current.leftNode);
        }else removeNode(element, current.rightNode);
    }

    public TreeNode minNode(TreeNode current) {
        if (current == null) {
            return null;
        } else if (current.leftNode == null) {
            return current;
        } else {
            return minNode(current.leftNode);
        }
    }

    /**
     * Performs Depth First Search as we use Stack
     * If we use Queue instead of stack it does Breath First Search
     * Time complexity is O(n) as we traverse each element once.
     *
     * @return String representation of the tree.
     */

    public String depthSearch() {
        String s = "";
        Stack<TreeNode> t = new Stack<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = t.pop();
            s += n.value.toString();
            if (n.getRightTree() != null) {
                t.push(n.getRightTree());
            }
            if (n.getLeftTree() != null) {
                t.push(n.getLeftTree());
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Performs a Breadth-First search
     * Time complexity is O(n) as we traverse each element once.
     * @return String representation of the tree.
     */
    public String breadthSearch() {
        String s = "";
        Queue<TreeNode> t = new Queue<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = t.pop();
            s += n.value.toString();
            if (n.getRightTree() != null) {
                t.push(n.getRightTree());
            }
            if (n.getLeftTree() != null) {
                t.push(n.getLeftTree());
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Calculates the depth of the tree.
     *
     * @return The depth of the tree.
     */
    public int depth() {
        return depthAtNode(root);
    }

    // Helper method for recursive calculation of the depth of a node.
    private int depthAtNode(TreeNode node) {
        if (node == null)
            return 0;
        else {
            // calculate the depth of each subtree
            int depthLeft = depthAtNode(node.leftNode) + 1;
            int depthRight = depthAtNode(node.rightNode) + 1;

            // use the bigger one
            return depthLeft > depthRight ? depthLeft : depthRight;
        }
    }

    /**
     * Finds and returns the maximum node in the tree.
     *
     * @return The maximum node in the tree.
     */
    public TreeNode findMaxNode() {
        return maxNode(root);
    }

    // Helper method for finding the maximum node in the tree.
    private TreeNode maxNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.rightNode == null) {
            return node;
        } else {
            return maxNode(node.rightNode);
        }
    }

    private E findNode(E element, TreeNode current){
        if(current == null){
            return null;
        } else if (element.compareTo(current.value) == 0) {
            return current.value;
        } else if (element.compareTo(current.value) < 0) {
            return findNode(element, current.getLeftTree());
        }else {
            return findNode(element, current.getRightTree());
        }
    }

    public E find(E element){
        return findNode(element, root);
    }

    @Override
    public String toString() {
        String s = "";
        traverse(new TreeAction<E>() {
            @Override
            public void run(TreeNode n) {
                System.out.println(n.getValue());
            }
        });
        return s;
    }

    private void printNode(TreeNode n){
        if (n != null){
            System.out.println(n.value);
            printNode(n.leftNode);
            printNode(n.rightNode);
        }
    }

    public void print2(){
        printNode(root);
    }


    public Vector<E> nodeValues() {
        Vector<E> s = new Vector<>();
        Queue<TreeNode> t = new Queue<>();
        t.push(root);
        while (!t.empty()) {
            TreeNode n = t.pop();
            s.addLast(n.value);
            if (n.getRightTree() != null) {
                t.push(n.getRightTree());
            }
            if (n.getLeftTree() != null) {
                t.push(n.getLeftTree());
            }
        }
        return s;
    }


}

