/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayunorderedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Tiago
 */
public abstract class ArrayList<T> implements ListADT<T> {

    protected int count;
    protected T[] list;
    /**
     * constant to represent the default capacity of the array
     */
    protected final int DEFAULT_CAPACITY = 100;
    protected int modCount;

    public ArrayList() {
        this.count = 0;
        this.modCount = 0;
        this.list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        T target = this.list[0];
        this.shiftRemove(0);
        this.count--;

        this.modCount++;

        return target;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("List");
        }
        
        this.count--; //get into the rear of the list + remove 1 from the count
        
        T element = this.list[this.count];
        this.list[this.count] = null;

        this.modCount++;

        return element;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }
        T target = null;
        int index = this.getElementIndex(element);

        if (index > -1) {
            target = this.list[index];
            this.shiftRemove(index);
            this.count--;

            this.modCount++;

        }
        return target;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }

        return this.list[0];
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }

        return this.list[this.count - 1];
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Empty List");
        }

        return this.getElementIndex(target) > -1;
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
    public Iterator<T> iterator() {
        return new Iter();
    }

    @Override
    public String toString() {
        String output = "";

        try {
            output += "The list contains " + this.size() + " elements.\n"
                    + "The first element is: " + this.first().toString()
                    + "\nThe last element is: " + this.last().toString();
        } catch (EmptyCollectionException ex) {
            output += ex;
        }

        return output;
    }

    protected int getElementIndex(T element) {
        int index = -1;
        for (int i = 0; i < this.count; i++) {
            if (this.list[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Shifts the elements from the array when it's removing an element from it
     *
     * @param index index of the element in the list
     */
    private void shiftRemove(int index) {
        //size represents the array size ignoring the element to remove
        int size = this.count - 1;

        for (int i = index; i < size; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.list[this.count] = null;
    }

    public class Iter implements Iterator<T> {

        private int cursor;
        private int expectedModCount;
        private boolean canRemove;

        Iter() {
            this.cursor = 0;
            this.expectedModCount = modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < size();
        }

        @Override
        public T next() {
            checkForModification();

            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            this.cursor++;
            this.canRemove = true;

            return list[this.cursor - 1];
        }

        @Override
        public void remove() {
            checkForModification();

            // Implementacao alternativa para evitar uso de flag e necessidade
            // de dar sempre depois do remove
//            try {
//                ArrayList.this.remove(next());
//            } catch (EmptyCollectionException ex) {
//                throw new IllegalStateException();
//            }

            if (!this.canRemove) {
                throw new IllegalStateException();
            }

            try {
                ArrayList.this.remove(list[this.cursor - 1]);
            } catch (EmptyCollectionException ex) {
                throw new IllegalStateException();
            }

            this.cursor--;
            this.expectedModCount++;
            this.canRemove = false;

        }

        private void checkForModification() {
            if (modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
