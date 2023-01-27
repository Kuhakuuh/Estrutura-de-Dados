/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedqueue;

/**
 *
 * @author Tiago
 */
public class LinkedQueue<T> implements QueueADT<T> {

    /**
     * int that represents the number of elements present in the queue
     */
    private int count;

    /**
     * node with generic element at the front of the queue
     */
    private LinearNode front;

    /**
     * node with generic element at the rear of the queue
     */
    private LinearNode rear;

    public LinkedQueue() {
        this.count = 0;
        this.front = null;
        this.rear = null;
    }

    @Override
    public void enqueue(T element) {
        //adding an element to the queue
        LinearNode<T> oldRear = this.rear;
        LinearNode<T> newNode = new LinearNode(element);
        //newNode.setNext(null);
        this.rear = newNode;
        this.count++;

        //assigning the front the new element as the queue's front if there is
        //only 1 element inside the queue
        if (this.size() == 1) {
            this.front = this.rear;
        } else {
            oldRear.setNext(this.rear);
        }

    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty queue");
        }
        T element = (T) this.front.getElement();
        this.front = this.front.getNext();
        this.count--;

        if (this.isEmpty()) {
            this.rear = null;
        }

        return element;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empry queue");
        }

        return (T) this.front.getElement();
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
    public String toString() {
        String output = "";

        //The string representation of this queue should only reveal the item
        //count as well as the rear and front of the queue due to not having
        //an iterator implemented
        output += "Count = " + this.count;

        if (!this.isEmpty()) {
            try {
                output += "\nFront Item: " + this.first().toString()
                        + "\nRear Item: " + this.rear.getElement().toString();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
        } else {
            output += "\nNo front or rear items.";
        }

        return output;
    }

}
