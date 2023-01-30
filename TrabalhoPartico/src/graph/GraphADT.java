/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Iterator;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public interface GraphADT<T> {

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex
     */
    public void addVertex(T vertex);

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex
     */
    public void removeVertex(T vertex);

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1
     * @param vertex2
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * Retruns a breadth first iterator starting with the given vertex.
     *
     * @param startVertex
     * @return
     */
    public Iterator iteratorBFS(T startVertex);

    /**
     * Retruns a depth first iterator starting with the given vertex.
     *
     * @param startVertex
     * @return
     */
    public Iterator iteratorDFS(T startVertex);

    /**
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * @param startVertex
     * @param targetVertex
     * @return
     */
    public Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * Returns true if this graph is connected, false otherwise.
     *
     * @return
     */
    public boolean isConnected();

    /**
     * Returns the number of vertices in this graph.
     *
     * @return
     */
    public int size();

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return
     */
    @Override
    public String toString();

}
