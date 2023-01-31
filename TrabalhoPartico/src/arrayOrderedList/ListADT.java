/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayOrderedList;

/**
 *
 * @author Rafael
 */

import java.util.Iterator;
import Excepcions.*;


public interface ListADT<T> extends Iterable<T> {
    
    T removeFirst() throws EmptyCollectionException;
    
    T removeLast()throws EmptyCollectionException;
    
    T remove(T element)throws NoSuchElementeException,EmptyCollectionException;
    
    T first();
    
    T last();
    
    boolean contains(T target);
    
    boolean isEmpty();
    
    int size();
    
    @Override
    Iterator<T> iterator();
    
    @Override
    String toString();
    
}
