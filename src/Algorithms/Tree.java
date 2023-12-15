package Algorithms;
public class Tree {
    // the class for implementing a node in the tree.
    // contains a value, a pointer to the left child and a pointer to the right child
	public class TreeNode implements Comparable {
        private Comparable value;
        private TreeNode leftNode;
        private TreeNode rightNode;

        public TreeNode(Comparable v) {
            this(v, null, null);
        }

        public TreeNode(Comparable v, TreeNode left, TreeNode right) {
            value = v;
            leftNode = left;
            rightNode = right;
        }

        public TreeNode getLeftTree() {
            return leftNode;
        }

        public TreeNode getRightTree() {
            return rightNode;
        }


        public Comparable getValue() {
            return value;
        }

        @Override
        public int compareTo(Object arg0) {
			return value.compareTo(((TreeNode)arg0).value );
        }
    }


    // start of the actual tree class

    // the root of our tree
    protected TreeNode root;

    public Tree() {
        root = null;
    }


    public void traverse(TreeAction action) {
		Queue t = new Queue<>();
		t.push(root);
		while (!t.empty()){
			TreeNode n = (TreeNode) t.pop();
			action.run(n);
			if(n.getLeftTree() != null){
				t.push(n.getLeftTree());
			}
			if(n.getRightTree() != null){
				t.push(n.getRightTree());
			}
 		}
    }

    private void traverseNode(TreeNode n, TreeAction action) {
        if (n != null) {
            if (n.getLeftTree() != null) traverseNode(n.getLeftTree(), action);
            action.run(n);
            if (n.getRightTree() != null) traverseNode(n.getRightTree(), action);
        }
    }

    public void traverseInOrder(TreeAction action) {
        traverseNode(root, action);
    }

    public void insert(Comparable element) {
        insertAtNode(element, root, null);
    }

    // we traverse the tree.
    // Current holds the pointer to the TreeNode we are currently checking
    // Parent holds the pointer to the parent of the current TreeNode
    private void insertAtNode(Comparable element, TreeNode current, TreeNode parent) {
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
            }
            // the current node is empty and it has no parent, we actually have an empty tree
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

	// this toString does Depth First Search with Stack
	// If we use Queue instead of stack it does Breath First Search

	public String depthSearch() {
		String s = "";
		Stack<TreeNode> t = new Stack<>();
		t.push(root);
		while(!t.empty()){
			TreeNode n = t.pop();
			s += n.value.toString();
			if(n.getRightTree() != null){
				t.push(n.getRightTree());
			}
			if(n.getLeftTree() != null){
				t.push(n.getLeftTree());
			}
			s += "\n";
		}
		return s;
	}

	public String breadthSearch() {
		String s = "";
		Queue<TreeNode> t = new Queue<>();
		t.push(root);
		while(!t.empty()){
			TreeNode n = t.pop();
			s += n.value.toString();
			if(n.getRightTree() != null){
				t.push(n.getRightTree());
			}
			if(n.getLeftTree() != null){
				t.push(n.getLeftTree());
			}
			s += "\n";
		}
		return s;
	}


	@Override
	public String toString(){
		String s = "";
		traverse(new TreeAction() {
			@Override
			public void run(TreeNode n) {
				System.out.println(n.getValue());
			}
		});
		return s;
	}

	public int depthAtNode(TreeNode node)
	{
		if (node == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int depthLeft = depthAtNode(node.leftNode) + 1;
			int depthRight = depthAtNode(node.rightNode) + 1;

			/* use the larger one */
			return depthLeft > depthRight ? depthLeft : depthRight;
		}
	}

	public int depth(){
		return depthAtNode(root);
	}

	private Comparable maxNode(TreeNode node){
		if(node == null){
			return null;
		}
		if(node.rightNode == null){
			return node.getValue();
		}else{
			return maxNode(node.rightNode);
		}
	}
	public Comparable findMax(){
		return maxNode(root);
	}





}

