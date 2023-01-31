/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedbinarytree;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
/**
 * BinaryTreeNode represents a node in a binary tree with a left and right
 * child.
 * @param <T>
 */
public class BinaryTreeNode<T> {

    private T element;
    private BinaryTreeNode<T> left, right;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param element the element that will become a part of the new tree node
     */
    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     * Returns the number of non-null children of this node. This method may be
     * able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;

        if (this.left != null) {
            children = 1 + this.left.numChildren();
        }

        if (this.right != null) {
            children = children + 1 + this.right.numChildren();
        }

        return children;
    }
    
    public T getElement() {
        return this.element;
    }
    
    public BinaryTreeNode getLeft() {
        return this.left;
    }
    
    public BinaryTreeNode getRight() {
        return this.right;
    }

    public void setElement(T element) {
        this.element = element;
    }
    
    public void setLeft(BinaryTreeNode node) {
        this.left = node;
    }
    
    public void setRight(BinaryTreeNode node) {
        this.right = node;
    }
}
