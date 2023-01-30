/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedqueue;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class LinkedQueue_demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedQueue<String> fila = new LinkedQueue<>();

        fila.enqueue("Primeiro");
        fila.enqueue("Segundo");
        fila.enqueue("Terceiro");
        fila.enqueue("Quarto");
        fila.enqueue("Quinto");
        fila.enqueue("Sexto");
        fila.enqueue("Ultimo");

        try {
            System.out.println("Checking first element: ");
            System.out.println(fila.first() + "\n");
            System.out.println("ToString of the queue: ");
            System.out.println(fila.toString() + "\n");
            System.out.println("Removing 6 items");
            System.out.println(fila.dequeue());
            System.out.println(fila.dequeue());
            System.out.println(fila.dequeue());
            System.out.println(fila.dequeue());
            System.out.println(fila.dequeue());
            System.out.println(fila.dequeue());
            System.out.println("\nChecking first element: ");
            System.out.println(fila.first() + "\n");
            System.out.println("Removing last: ");
            System.out.println(fila.dequeue());
            System.out.println("\nToString of the queue: ");
            System.out.println(fila.toString() + "\n");
            

        } catch (EmptyCollectionException ex) {
            System.out.println(ex);
        }

    }

}
