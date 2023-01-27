package graph;

import java.util.Iterator;

/**
 *
 * @author Tiago Lopes
 */
public class MatrixGraphDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MatrixGraph graph = new MatrixGraph();
        //Adicionar vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        //adiciona arestas
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("D", "E");
        graph.addEdge("C", "E");

        System.out.println(graph.toString());

        System.out.println("Grafo é connected: " + graph.isConnected());
        System.out.println("Shortest Path");
        Iterator iter1 = graph.iteratorShortestPath("A", "E");
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }

        MatrixGraph graph1 = new MatrixGraph();
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");

        graph1.addEdge("A", "B");
        graph1.addEdge("A", "C");
        graph1.addEdge("A", "D");
        graph1.addEdge("B", "E");
        graph1.addEdge("E", "C");
        graph1.addEdge("D", "F");

        System.out.println(graph1.toString());

        System.out.println("Grafo é connected: " + graph1.isConnected());

        Iterator iter = graph1.iteratorBFS("A");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

}
