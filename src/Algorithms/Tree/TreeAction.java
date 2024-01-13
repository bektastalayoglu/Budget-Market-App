/**
 * Author : Bektas Talayoglu
 * Description : TreeAction is an abstract class that defines an action to be performed on tree nodes.
 **/

package Algorithms.Tree;
public abstract class TreeAction<E extends Comparable<E>>{
    /**
     * Abstract method to be implemented by subclasses.
     * This method specifies the action to be performed on a tree node.
     *
     * @param n The tree node on which the action will be performed.
     */
    public abstract void run(Tree<E>.TreeNode n);
}


