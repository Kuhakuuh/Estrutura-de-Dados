/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.Iterator;
import linkedheap.EmptyCollectionException;

/**
 *
 * @author Tiago Lopes
 */
public class NetworkDemo {

    /**
     * @param args the command line arguments
     * @throws linkedheap.EmptyCollectionException
     * @throws linkedstack.EmptyCollectionException
     */
    public static void main(String[] args) throws EmptyCollectionException, linkedstack.EmptyCollectionException {
        Network n = new Network();
        n.addVertex("a");
        n.addVertex("b");
        n.addVertex("c");
        n.addVertex("d");
        n.addVertex("e");
        n.addEdge("a", "b", 2);
        n.addEdge("a", "c", 1);
        n.addEdge("a", "d", 3);
        n.addEdge("b", "c", 1);
        n.addEdge("b", "e", 4);
        n.addEdge("c", "d", 1);
        n.addEdge("c", "e", 4);
        n.addEdge("d", "e", 1);

        System.out.println(n.toString());
        System.out.println("Custo de a-e : " + n.shortestPathWeight("a", "e"));
        Iterator iter = n.iteratorShortestPath("d", "b");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        
        System.out.println(n.mstNetwork());

    }

}
