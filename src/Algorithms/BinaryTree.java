package Algorithms;

public class BinaryTree<E> {
    private TreeNode root;

    private class TreeNode implements Comparable {
        private Comparable data;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(Comparable data, TreeNode left, TreeNode right, TreeNode parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public TreeNode(Comparable data) {
            this(data, null, null, null);
        }

        @Override
        public int compareTo(Object arg0) {
            TreeNode node2 = (TreeNode) arg0;
            return data.compareTo(node2.data);
        }
    }

    public TreeNode getRoot() {
        return root;
    }


    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void insert(int data) {
        insertAtNode(data, root, null);
    }

    private void insertAtNode(Comparable data, TreeNode current, TreeNode parent) {
        if (current == null) {
            TreeNode newNode = new TreeNode(data);
            if (parent != null) {
                if (data.compareTo(parent.data) < 0) {
                    parent.left = newNode;
                } else {
                    parent.right = newNode;
                }
            } else {
                root = newNode;
            }
        } else if (data.compareTo(current.data) < 0) {
            insertAtNode(data, current.left, current);
        } else if (data.compareTo(current.data) == 0) {
            throw new UnsupportedOperationException();
        } else {
            insertAtNode(data, current.right, current);
        }

    }


}
