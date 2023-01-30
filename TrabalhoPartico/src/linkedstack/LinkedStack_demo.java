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
public class LinkedStack_demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedStack<String> anterior = new LinkedStack<>();

        String string1 = "Primeira";
        String string2 = "Segunda";
        String string3 = "Terceira";
        String string4 = "Quarta";

        anterior.push(string1);
        anterior.push(string2);
        anterior.push(string3);
        anterior.push(string4);
        
        System.out.println(anterior);

        try {

            System.out.println("Remover Quarta\n" + anterior.pop());
            System.out.println("Remover Terceira\n" + anterior.pop());
            System.out.println("Adicionando Terceira");
            anterior.push(string3);
            System.out.println("Remover Terceira\n" + anterior.pop());
            System.out.println("Remover Segunda\n" + anterior.pop());
            System.out.println("Remover Primeira\n" + anterior.pop());
        } catch (EmptyCollectionException ex) {
            System.out.println(ex);
        }

    }
    
}
