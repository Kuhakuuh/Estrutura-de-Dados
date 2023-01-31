/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayunorderedlist;

import java.util.NoSuchElementException;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        if (super.count == super.list.length) {
            this.expandCapacity();
        }

        this.shiftAddToFront(element);

        super.count++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (super.count == super.list.length) {
            this.expandCapacity();
        }

        super.list[this.count] = element;

        super.count++;
        super.modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws NoSuchElementException {

        int index = super.getElementIndex(target);

        if (index < 0) {
            throw new NoSuchElementException("Target element not in the list");
        }

        if (super.count == super.list.length) {
            this.expandCapacity();
        }

        this.shiftAdd(index, element);

        super.count++;
        super.modCount++;
    }

    private void expandCapacity() {
        T[] newList = (T[]) (new Object[super.list.length * 2]);
        System.arraycopy(super.list, 0, newList, 0, super.list.length);
        super.list = newList;
    }

    private void shiftAddToFront(T element) {
        int rear = super.count - 1;
        for (int i = rear; i >= 0; i--) {
            super.list[i + 1] = super.list[i];
        }
        super.list[0] = element;
    }

    private void shiftAdd(int index, T element) {
        int rear = super.count - 1;
        for (int i = rear; i >= index; i--) {
            super.list[i + 1] = super.list[i];
        }
        super.list[index] = element;
    }
    
    @Override
    public String toString(){
        String msg="";
        for(int i=0;i<count;i++){
            msg+="\n"+list[i];
        }
        return msg;
    }

}
