/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayOrderedList;


/**
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList() {
        super();
    }

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void add(T element) {
       Comparable<T> comparableElement = (Comparable<T>)element;
       
       if(size()== count){
           expandCapacity();
       }
       
       int scan=front;
       
       while (scan<rear && comparableElement.compareTo(list[scan])>0){
           scan++;
       }
       
       for(int shift=rear;shift>scan;shift--){
           list[shift]=list[shift-1];
       }
       
       list[scan]=element;
       rear++;
       count++;
       modcount++;
    }
    
    @Override
    public String toString(){
        String msg="";
        int scan=front;
        while (scan<rear){
            msg+="\n"+list[scan];
            scan++;
       }
        return msg;
    }
    

}
