/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayOrderedList;

import linkedqueue.QueueADT;
import Excepcions.*;

/**
 *
 * @author Rafael
 * @param <T>
 */
public class ArrayCircularQueue<T> implements QueueADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int front;
    private int rear;
    private T[] queue;
    private int count;

    public ArrayCircularQueue() {
        front = 0;
        rear = 0;
        count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public ArrayCircularQueue(int initialCapacity) {
        front = 0;
        rear = 0;
        count = 0;
        queue = (T[]) (new Object[initialCapacity]);
    }

    @Override
    public void enqueue(T elemente) {
        if (size() == queue.length) {
            expandCapacity();
        }
        queue[rear] = elemente;
        rear = (rear + 1) % queue.length;
        count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        T result = queue[front];
        queue[front] = null;
        front++;
        count--;
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return count<=0; 
    }

    @Override
    public int size() {
        return count;
    }

    private void expandCapacity() {
        T[] Newqueue = (T[]) (new Object[queue.length * 2]);
        System.arraycopy(queue, 0, Newqueue, 0, queue.length);
        queue = Newqueue;
    }
    
    @Override
    public String toString() {
        String string="Queueu:\n";
        for(int i=front;i<rear;i++){
            string+=" "+ queue[i].toString();
        }
        return string;
    }

}
