/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayOrderedList;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ConcurrentModificationException;
import Excepcions.*;

/**
 *
 * @author Rafael
 * @param <T>
 */
public abstract class ArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    int front;
    int rear;
    T[] list;
    int count;
    int modcount;

    public ArrayList() {
        front = 0;
        rear = 0;
        count = 0;
        modcount = 0;
        list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public ArrayList(int initialCapacity) {
        front = 0;
        rear = 0;
        count = 0;
        list = (T[]) (new Object[initialCapacity]);
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista");
        }
        T result = list[front];
        for(int i=0;i<rear-1;i++){
            list[i]=list[i+1];
        }
        rear--;
        list[rear]=null;
        count--;
        modcount++;
        return result;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista");
        }
        T result = list[rear - 1];
        list[rear - 1] = null;
        rear--;
        count--;
        modcount++;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementeException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }
        if (!contains(element)) {
            throw new NoSuchElementeException("Lista");
        }
        T aux = null;
        int index = 0;
        for (int i = front; i < rear; i++) {
            if (list[i].equals(element)) {
                aux = list[i];
                index = i;
            }
        }
        for (int j = index; j < rear - 1; j++) {
            list[j] = list[j + 1];
        }
        count--;
        modcount++;
        rear--;
        list[rear] = null;
        return (T) aux;
    }

    @Override
    public T first() {
        return list[front];
    }

    @Override
    public T last() {
        return list[rear];
    }

    @Override
    public boolean contains(T target) {
        for (int i = front; i < rear; i++) {
            if (list[i] == target) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    @Override
    public int size() {
        return count;
    }

    public void expandCapacity() {
        T[] Newlist = (T[]) (new Object[list.length * 2]);
        System.arraycopy(list, 0, Newlist, 0, list.length);
        list = Newlist;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>();
    }

    public class BasicIterator<T> implements Iterator<T> {

        private int index;
        private int expectedModCount;
        private boolean okToRemove;

        public BasicIterator() {
            index = front;
            okToRemove = false;
            expectedModCount = modcount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modcount) {
                throw new ConcurrentModificationException("Lista");
            }
            return index < rear;
        }

        @Override
        public T next() {
            if (expectedModCount != modcount) {
                throw new ConcurrentModificationException("Lista");
            }
            okToRemove = true;
            index++;
            return (T) list[index - 1];
        }

        @Override
        public void remove() {
            if (expectedModCount != modcount) {
                throw new ConcurrentModificationException("Lista");
            }
            if (okToRemove) {
                try {
                    ArrayList.this.remove(list[index - 1]);
                    okToRemove = false;
                } catch (NoSuchElementeException | EmptyCollectionException ex) {
                    Logger.getLogger(ArrayList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
