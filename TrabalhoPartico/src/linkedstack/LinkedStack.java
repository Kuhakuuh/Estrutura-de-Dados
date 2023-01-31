/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedstack;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class LinkedStack<T> implements StackADT<T> {

    /**
     * int that represents the number of elements present in the stack
     */
    private int count;
    /**
     * node with generic element at the top of the stack
     */
    LinearNode top;
    
    public LinkedStack() {
        this.count = 0;
        this.top = null;
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param initialCapacity represents the specified capacity
     */
    public LinkedStack(T element) {
        this.count = 1;
        this.top = new LinearNode<>(element);
    }

    /**
     * Adds the specified element to the top of this stack. Creates a new
     * linear node that will hold the new element. This new node's next node
     * will be the "old" top and the top will be this new node. Fix the count
     *
     * @param element generic element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(this.top);
        this.top = newNode;
        this.count++;
    }

    /**
     * Removes the element at the top of this stack and returns a reference to
     * it. Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element removed from top of stack
     * @throws EmptyCollectionException if a pop is attempted on empty stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty stack");
        }
        T element = (T) this.top.getElement();
        this.top = this.top.getNext();
        this.count--;
        return element;
    }

    /**
     * Returns a reference to the element at the top of this stack. The element
     * is not removed from the stack. Throws an EmptyCollectionException if the
     * stack is empty.
     *
     * @return T element on top of stack
     * @throws EmptyCollectionException if a peek is attempted on empty stack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty stack");
        }
        
        return (T) this.top.getElement();

    }

    /**
     * Returns true if this stack contains no elements and false if it doesn't.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return this.count;
    }
    
    @Override
    public String toString() {
        /**
        String output = "";
        
        output += "Number of elements: " + this.size();
        if (!this.isEmpty()) {
            try {
                output += " Top element: " + this.peek().toString();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
        }
        
        return output;
        */
        String string="";
        LinearNode<T> current=top;
        while(current!=null){
            string+=" "+current.getElement();
            current=current.getNext();
        }
        return string;
    }
    
}
