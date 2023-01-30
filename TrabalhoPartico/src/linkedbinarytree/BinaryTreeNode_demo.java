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
public class BinaryTreeNode_demo {

    public static void main(String[] args) {

        BinaryTreeNode<String> btna = new BinaryTreeNode("A");
        BinaryTreeNode<String> btnb = new BinaryTreeNode("B");
        BinaryTreeNode<String> btnc = new BinaryTreeNode("C");
        BinaryTreeNode<String> btnd = new BinaryTreeNode("D");

        btna.setLeft(btnb);
        
        btna.setRight(btnc);
        
        btna.numChildren();
        
    }

}
