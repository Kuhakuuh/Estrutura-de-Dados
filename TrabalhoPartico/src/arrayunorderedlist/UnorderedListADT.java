/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayunorderedlist;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public interface UnorderedListADT<T> extends ListADT<T>{
    /**
     * Adds the specified element to this list at the front of the list
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);
    
    /**
     * Adds the specified element to this list at the rear of the list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);
    
    /**
     * Adds the specified element to this list after a target element
     *
     * @param element the element to be added to this list
     */
    public void addAfter(T element, T target);
}
