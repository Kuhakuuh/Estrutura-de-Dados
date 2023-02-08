/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedbinarytree;

import arrayunorderedlist.ArrayUnorderedList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import Excepcions.EmptyCollectionException;
import linkedqueue.LinkedQueue;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 *
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     * tree
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    /**
     * For testing purposes
     *
     * @param root to test a binary tree
     */
    public LinkedBinaryTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public T getRoot() {
        return this.root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) {
        BinaryTreeNode<T> current = findAgain(targetElement, this.root);

        return current != null;
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree. Throws a NoSuchElementException if the specified target
     * element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, this.root);

        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }

        return (current.getElement());
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root.
     *
     * @return an in order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        this.inorder(this.root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        this.preorder(this.root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        this.postorder(this.root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedQueue<BinaryTreeNode> tempQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        this.levelorder(this.root, tempQueue, tempList);

        return tempList.iterator();
    }

    @Override
    public String toString() {
        String output = "";
        Iterator iter = this.iteratorLevelOrder();
        while (iter.hasNext()) {
            output += iter.next();
        }
        return output;
    }

    /**
     * Returns a reference to the specified target element if it is found in
     * this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement,
            BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }

        return temp;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node,
            ArrayUnorderedList<T> tempList) {
        if (node != null) {
            this.inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            this.inorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preorder(BinaryTreeNode<T> node,
            ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            this.preorder(node.getLeft(), tempList);
            this.preorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postorder(BinaryTreeNode<T> node,
            ArrayUnorderedList<T> tempList) {
        if (node != null) {
            this.postorder(node.getLeft(), tempList);
            this.postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    protected void levelorder(BinaryTreeNode<T> node,
            LinkedQueue<BinaryTreeNode> tempQueue,
            ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempQueue.enqueue(node);
            while (!tempQueue.isEmpty()) {
                try {
                    BinaryTreeNode<T> headNode = tempQueue.dequeue();
                    tempList.addToRear(headNode.getElement());
                    if (headNode.getLeft() != null) {
                        tempQueue.enqueue(headNode.getLeft());
                    }
                    if (headNode.getRight() != null) {
                        tempQueue.enqueue(headNode.getRight());
                    }
                } catch (EmptyCollectionException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    /**
     * Removes all elements form this binary tree
     */
    public void removeAllElements() {
        count = 0;
        root = null;
    }

}
