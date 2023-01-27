/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import arrayunorderedlist.ArrayUnorderedList;
import graph.MatrixGraph;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import linkedheap.LinkedHeap;
import linkedqueue.LinkedQueue;
import linkedstack.EmptyCollectionException;
import linkedstack.LinkedStack;

/**
 *
 * @author Tiago Lopes
 * @param <T>
 */
public class Network<T> extends MatrixGraph<T> implements NetworkADT<T> {

    protected double[][] adjMatrixNetwork;    // adjacency matrix

    /**
     * ****************************************************************
     * Creates an empty network.
     * ****************************************************************
     */
    public Network() {
        numberVertices = 0;
        this.adjMatrixNetwork = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * ****************************************************************
     * Returns a string representation of the adjacency matrix.
     * ****************************************************************
     */
    @Override
    public String toString() {
        if (numberVertices == 0) {
            return "Graph is empty";
        }

        String result = "";

        /**
         * Print the adjacency Matrix
         */
        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numberVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numberVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numberVertices; j++) {
                if (adjMatrixNetwork[i][j] < Double.POSITIVE_INFINITY) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        /**
         * Print the vertex values
         */
        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numberVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        /**
         * Print the weights of the edges
         */
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numberVertices; i++) {
            for (int j = numberVertices - 1; j > i; j--) {
                if (adjMatrixNetwork[i][j] < Double.POSITIVE_INFINITY) {
                    result += i + " to " + j + "\t";
                    result += adjMatrixNetwork[i][j] + "\n";
                }
            }
        }

        result += "\n";
        return result;
    }

    /**
     * ****************************************************************
     * Inserts an edge between two vertices of the graph.****************************************************************
     * @param index1
     * @param index2
     * @param weight
     */
    private void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrixNetwork[index1][index2] = weight;
            adjMatrixNetwork[index2][index1] = weight;
        }
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.****************************************************************
     * @param index1
     * @param index2
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrixNetwork[index1][index2] = Double.POSITIVE_INFINITY;
            adjMatrixNetwork[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * ****************************************************************
     * Inserts an edge with the given weight between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * ****************************************************************
     * Inserts an edge between two vertices of the graph. Assumes a weight of 0.
     * ****************************************************************
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary.
     * ****************************************************************
     */
    public void addVertex() {
        if (numberVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numberVertices] = null;
        for (int i = 0; i <= numberVertices; i++) {
            adjMatrixNetwork[numberVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrixNetwork[i][numberVertices] = Double.POSITIVE_INFINITY;
        }
        numberVertices++;
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     * ****************************************************************
     */
    @Override
    public void addVertex(T vertex) {
        if (numberVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numberVertices] = vertex;
        for (int i = 0; i <= numberVertices; i++) {
            adjMatrixNetwork[numberVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrixNetwork[i][numberVertices] = Double.POSITIVE_INFINITY;
        }
        numberVertices++;
    }

    /**
     * ****************************************************************
     * Removes a vertex at the given index from the graph.Note that this may
 affect the index values of other vertices. ****************************************************************
     * @param index
     */
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numberVertices--;

            for (int i = index; i < numberVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numberVertices; i++) {
                for (int j = 0; j <= numberVertices; j++) {
                    adjMatrixNetwork[i][j] = adjMatrixNetwork[i + 1][j];
                }
            }

            for (int i = index; i < numberVertices; i++) {
                for (int j = 0; j < numberVertices; j++) {
                    adjMatrixNetwork[j][i] = adjMatrixNetwork[j][i + 1];
                }
            }
        }
    }

    /**
     * ****************************************************************
     * Removes a single vertex with the given value from the graph.
     * ****************************************************************
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numberVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     ****************************************************************
     * @param startIndex
     * @return
     */
    @Override
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x = null;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numberVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numberVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
            found = false;

            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numberVertices) && !found; i++) {
                if ((adjMatrixNetwork[x][i] < Double.POSITIVE_INFINITY)
                        && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                try {
                    traversalStack.pop();
                } catch (EmptyCollectionException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given vertex.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.****************************************************************
     * @param startIndex
     * @return 
     */
    @Override
    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x = null;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            try {
                x = traversalQueue.dequeue();
            } catch (linkedqueue.EmptyCollectionException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultList.addToRear(vertices[x]);

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numberVertices; i++) {
                if ((adjMatrixNetwork[x][i] < Double.POSITIVE_INFINITY)
                        && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given vertex.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.****************************************************************
     * @param startIndex
     * @param targetIndex
     * @return 
     * @throws linkedheap.EmptyCollectionException
     * @throws linkedstack.EmptyCollectionException
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws linkedheap.EmptyCollectionException, EmptyCollectionException {
        int index;
        double weight;
        int[] predecessor = new int[numberVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<Double>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<Integer>();
        LinkedStack<Integer> stack = new LinkedStack<Integer>();

        int[] pathIndex = new int[numberVertices];
        double[] pathWeight = new double[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < numberVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex]
                        + adjMatrixNetwork[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = (traversalMinHeap.removeMin());
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            /**
             * Update the pathWeight for each vertex that has has not been
             * visited and is adjacent to the last vertex that was visited.
             * Also, add each unvisited vertex to the heap.
             */
            for (int i = 0; i < numberVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrixNetwork[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrixNetwork[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrixNetwork[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns the index of the the vertex that that is adjacent to the vertex
     * with the given index and also has a pathWeight equal to weight.
     *
     ****************************************************************
     * @param visited
     * @param pathWeight
     * @param weight
     * @return
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numberVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numberVertices; j++) {
                    if ((adjMatrixNetwork[i][j] < Double.POSITIVE_INFINITY)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;  // should never get to here
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.****************************************************************
     * @param startIndex
     * @param targetIndex
     * @return 
     */
    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = null;
        try {
            it = iteratorShortestPathIndices(startIndex,
                    targetIndex);
        } catch (linkedheap.EmptyCollectionException | EmptyCollectionException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (it.hasNext()) {
            templist.addToRear(vertices[(it.next())]);
        }
        return templist.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex),
                getIndex(targetVertex));
    }

    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     * ****************************************************************
     */
    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network.Returns
 positive infinity if no path is found.****************************************************************
     * @param startVertex
     * @param targetVertex
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        double result = 0;
        try {
            result = shortestPathWeight(getIndex(startVertex),
                    getIndex(targetVertex));
        } catch (linkedheap.EmptyCollectionException | EmptyCollectionException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    /**
     * 
     * @param startIndex
     * @param targetIndex
     * @return
     * @throws linkedheap.EmptyCollectionException
     * @throws EmptyCollectionException 
     */
    public double shortestPathWeight(int startIndex, int targetIndex) throws linkedheap.EmptyCollectionException, EmptyCollectionException {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = (it.next());
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = (it.next());
            result += adjMatrixNetwork[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * ****************************************************************
     * Returns a minimum spanning tree of the network.****************************************************************
     * @return 
     * @throws linkedheap.EmptyCollectionException
     */
    public Network<T> mstNetwork() throws linkedheap.EmptyCollectionException {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<Double>();
        Network<T> resultGraph = new Network<T>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }

        resultGraph.adjMatrixNetwork = new double[numberVertices][numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            for (int j = 0; j < numberVertices; j++) {
                resultGraph.adjMatrixNetwork[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        resultGraph.vertices = (T[]) (new Object[numberVertices]);

        boolean[] visited = new boolean[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numberVertices++;
        visited[0] = true;

        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numberVertices; i++) {
            minHeap.addElement(adjMatrixNetwork[0][i]);
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
            do {
                weight = (minHeap.removeMin());
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }

            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numberVertices++;

            resultGraph.adjMatrixNetwork[x][y] = this.adjMatrixNetwork[x][y];
            resultGraph.adjMatrixNetwork[y][x] = this.adjMatrixNetwork[y][x];

            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numberVertices; i++) {
                if (!visited[i] && (this.adjMatrixNetwork[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrixNetwork[index][i]);
                }
            }
        }
        return resultGraph;
    }

    /**
     * ****************************************************************
     * Returns the edge with the given weight.Exactly one vertex of the edge
     * must have been visited.
     *
     ****************************************************************
     * @param weight
     * @param visited
     * @return
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numberVertices; i++) {
            for (int j = 0; j < numberVertices; j++) {
                if ((adjMatrixNetwork[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * ****************************************************************
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     * ****************************************************************
     */
    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix
                = new double[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numberVertices; i++) {
            System.arraycopy(adjMatrixNetwork[i], 0, largerAdjMatrix[i], 0, numberVertices);
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrixNetwork = largerAdjMatrix;
    }

}
