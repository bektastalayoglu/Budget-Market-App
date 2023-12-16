package Algorithms;

/**
 * TreeAction is an abstract class that defines an action to be performed on tree nodes.
 */
public abstract class TreeAction {
    /**
     * Abstract method to be implemented by subclasses.
     * This method specifies the action to be performed on a tree node.
     *
     * @param n The tree node on which the action will be performed.
     */
    public abstract void run(Tree.TreeNode n);
}


