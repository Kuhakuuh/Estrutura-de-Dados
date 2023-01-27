/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedbinarytree;

import java.util.Iterator;

/**
 *
 * @author Carlos
 */
public class LinkedBinaryTree_demo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BinaryTreeNode<String> a = new BinaryTreeNode<>("A");
        BinaryTreeNode<String> b = new BinaryTreeNode<>("B");
        BinaryTreeNode<String> c = new BinaryTreeNode<>("C");
        BinaryTreeNode<String> d = new BinaryTreeNode<>("D");
        BinaryTreeNode<String> e = new BinaryTreeNode<>("E");
        
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        
        LinkedBinaryTree<String> lbt = new LinkedBinaryTree<>(a);
        
        Iterator levelOrderIter = lbt.iteratorLevelOrder();
        
        while (levelOrderIter.hasNext()) {
            System.out.print(levelOrderIter.next());
        }
        
        System.out.println("");
        
        Iterator preOrderIter = lbt.iteratorPreOrder();
        
        while (preOrderIter.hasNext()) {
            System.out.print(preOrderIter.next());
        }
        
        System.out.println("");
        
        Iterator postOrderIter = lbt.iteratorPostOrder();
        
        while (postOrderIter.hasNext()) {
            System.out.print(postOrderIter.next());
        }
        
        System.out.println("");
        
        Iterator inOrderIter = lbt.iteratorInOrder();
        
        while (inOrderIter.hasNext()) {
            System.out.print(inOrderIter.next());
        }
        
        System.out.println("");
                
        System.out.println("toString: " + lbt.toString());
        
    }
}
