/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import arrayunorderedlist.ArrayUnorderedList;
import java.util.Iterator;
import network.Network;

/**
 * Class that represents the map formad by all the locals(Portals and
 * Connectors), extends class Network
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class Mapa<T> extends Network<T> {

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
            tempLocal.addToRear((Local) vertice);

        }
        return tempLocal;
    }
}
