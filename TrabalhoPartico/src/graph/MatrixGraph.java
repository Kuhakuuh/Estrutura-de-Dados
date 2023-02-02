/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import arrayunorderedlist.ArrayUnorderedList;
import java.util.Iterator;
import linkedqueue.EmptyCollectionException;
import linkedqueue.LinkedQueue;
import linkedstack.LinkedStack;

/**
 * Graph represents an adjacency matrix implementation of a graph
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class MatrixGraph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numberVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // value of vertices 

    /**
     * Create an empy graph
     */
    public MatrixGraph() {
        numberVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adds a vertex to the graph, expanding the capacity to the graph if
     * necessary
     *
     * @param vertex
     */
    @Override
    public void addVertex(T vertex) {
        if (this.numberVertices == this.vertices.length) {
            expandCapacity();
        }

        this.vertices[this.numberVertices] = vertex;
        for (int i = 0; i <= numberVertices; i++) {
            adjMatrix[numberVertices][i] = false;
            adjMatrix[i][numberVertices] = false;
        }
        this.numberVertices++;
    }

    /**
     * Creates new array to store the contents of the graph with twice the
     * capacity
     */
    protected void expandCapacity() {
        T[] newVertices = (T[]) (new Object[this.vertices.length * 2]);
        boolean[][] newMatrix = new boolean[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];

        System.arraycopy(this.vertices, 0, newVertices, 0, this.numberVertices);
        System.arraycopy(this.adjMatrix, 0, newMatrix, 0, this.numberVertices);

        this.vertices = newVertices;
        this.adjMatrix = newMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        int index = this.getIndex(vertex);
        if (indexIsValid(index)) {
            while (index < this.numberVertices) {
                //shifting the rows to left side
                for (int i = 0; i < this.numberVertices; i++) {
                    this.adjMatrix[i][index] = this.adjMatrix[i][index + 1];
                }
                //shifting the rows to left side
                for (int i = 0; i < this.numberVertices; i++) {
                    this.adjMatrix[index][i] = this.adjMatrix[index + 1][i];
                }
                index++;
            }
            this.numberVertices--;
        }
    }

    /**
     * Find the possition of the vertices in array if it exists
     *
     * @param vertex1
     * @return
     */
    protected int getIndex(T vertex1) {
        int index = this.numberVertices;
        for (int i = 0; i < this.numberVertices; i++) {
            if (this.vertices[i].equals(vertex1)) {
                index = i;
                break;
            }
        }
        return index;
    }

    protected boolean indexIsValid(int index) {
        return (index >= 0 && index < this.numberVertices);
    }

    /**
     * Inserts an edge between two vertices of the graph
     *
     * @param vertex1
     * @param vertex2
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph
     *
     * @param index1
     * @param index2
     */
    protected void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;

        }
    }

    /**
     * Revome an edge between two vertices of the graph
     *
     * @param vertex1
     * @param vertex2
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int index1 = this.getIndex(vertex1);
        int index2 = this.getIndex(vertex2);

        if (this.indexIsValid(index1) && this.indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    /**
     * Returns am iterator that performs a breadth first search traversal
     * starting at the given index
     *
     * @param startVertex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    protected Iterator<T> iteratorBFS(int startIndex) {
        Integer x = null;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[this.numberVertices];
        for (int i = 0; i < this.numberVertices; i++) {
            visited[i] = false;
        }
        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            try {
                x = traversalQueue.dequeue();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
            resultList.addToRear(this.vertices[x]);
            //Find all vertices adjacent to x that have not been visited and 
            //queue them up
            for (int i = 0; i < this.numberVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index
     *
     * @param startVertex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    protected Iterator<T> iteratorDFS(int startIndex) {
        Integer x = null;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[this.numberVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < this.numberVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(this.vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();
            } catch (linkedstack.EmptyCollectionException ex) {
                System.out.println(ex);
            }
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < this.numberVertices) && !found; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                try {
                    traversalStack.pop();
                } catch (linkedstack.EmptyCollectionException ex) {
                    System.out.println(ex);
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return this.iteratorShortestPath(this.getIndex(startVertex), this.getIndex(targetVertex));
    }

    protected Iterator iteratorShortestPath(int startIndex, int endIndex) {
        Integer x = null;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        int[] res = new int[this.numberVertices];
        boolean found = false;

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numberVertices];

        for (int i = 0; i < this.numberVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty() && !found) {
            try {
                x = traversalQueue.dequeue();
            } catch (EmptyCollectionException ex) {
                System.out.println(ex);
            }
            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < this.numberVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    res[i] = x;
                    visited[i] = true;
                    if (i == endIndex) {
                        found = true;
                        break;
                    }
                }
            }
        }
        int current = endIndex;

        if (found) {
            while (current != startIndex) {
                resultList.addToFront(this.vertices[current]);
                current = res[current];
            }
            resultList.addToFront(this.vertices[current]);
        } else {
            resultList.addToFront(this.vertices[startIndex]);
        }

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty() {
        return (this.numberVertices == 0);
    }

    @Override
    public boolean isConnected() {
        Iterator iter = this.iteratorBFS(0);
        int total = 0;
        while (iter.hasNext()) {
            total++;
            iter.next();
        }
        return total == this.numberVertices;
    }

    @Override
    public int size() {
        return this.numberVertices;
    }

    @Override
    public String toString() {
        String matrix = "";

        matrix += " |y";
        for (int i = 0; i < this.numberVertices; i++) {
            matrix += " " + i;
        }

        matrix += "\nx+";

        for (int i = 0; i < this.numberVertices; i++) {
            matrix += "--";
        }

        for (int i = 0; i < this.numberVertices; i++) {
            matrix += "\n" + i + "|";

            for (int j = 0; j < this.numberVertices; j++) {
                if (this.adjMatrix[i][j]) {
                    matrix += " 1";
                } else {
                    matrix += " 0";
                }
            }

        }

        return matrix;

    }

    public T[] getVertices() {
        return vertices;
    }

    public void setVertices(T[] vertices) {
        this.vertices = vertices;
    }

}
