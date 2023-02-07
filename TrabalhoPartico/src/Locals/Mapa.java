/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import arrayunorderedlist.ArrayUnorderedList;
import java.util.Iterator;
import linkedheap.LinkedHeap;
import linkedstack.EmptyCollectionException;
import linkedstack.LinkedStack;
import network.Network;

/**
 * Class that represents the map formad by all the locals(Portals and
 * Connectors), extends class Network
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class Mapa<T> extends Network<T>{

    public Mapa() {
        super();
    }

    /**
     * Returns an array of all Portals
     *
     * @return ArrayUnorderedList
     */
    public ArrayUnorderedList getPortals() {
        ArrayUnorderedList<Portal> tempPortals = new ArrayUnorderedList<Portal>();
        for (T vertice : vertices) {
            if (vertice != null && vertice.getClass() != Connectors.class) {
                tempPortals.addToRear((Portal) vertice);
            }
        }
        return tempPortals;
    }

    /**
     * Returns an array of all Connectors
     *
     * @return ArrayUnorderedList
     */
    public ArrayUnorderedList getConnectors() {
        ArrayUnorderedList<Connectors> tempConnectors = new ArrayUnorderedList<Connectors>();
        for (T vertice : vertices) {
            if (vertice != null && vertice.getClass() != Portal.class) {
                tempConnectors.addToRear((Connectors) vertice);
            }
        }
        return tempConnectors;
    }

    public ArrayUnorderedList getLocals() {
        ArrayUnorderedList<Local> tempLocal = new ArrayUnorderedList<Local>();
        for (T vertice : vertices) {
            if (vertice != null) {
                tempLocal.addToRear((Local) vertice);
            }
        }
        return tempLocal;
    }

    public int getIdByPos(int pos) {
        Local tempLocal = (Local) this.vertices[pos];
        return tempLocal.getId();
    }

    public Local getLocalById(int id) {
        Iterator iter = getLocals().iterator();
        while (iter.hasNext()) {
            Local tempLocal = (Local) iter.next();
            if (tempLocal.getId() == id) {
                return tempLocal;
            }
        }
        return null;
    }

    
   public Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws linkedheap.EmptyCollectionException, EmptyCollectionException {
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
}
