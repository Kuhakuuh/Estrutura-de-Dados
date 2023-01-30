/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import arrayunorderedlist.ArrayUnorderedList;
import network.Network;

/**
 * Class that represents the map formad by all the locals(Portals and Connectors), extends class Network
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class Mapa<T> extends Network<T> {

    private ArrayUnorderedList<Portal> portals = new ArrayUnorderedList<Portal>();
    private ArrayUnorderedList<Connectors> connectors = new ArrayUnorderedList<Connectors>();

    public Mapa() {
        super();
    }

    /**
     * Returns an array of all Portals
     * @return ArrayUnorderedList
     */
    public ArrayUnorderedList getPortals() {
        for (T vertice : vertices) {
            if (vertice != null && vertice.getClass() != Connectors.class) {
                portals.addToRear((Portal) vertice);
            }
        }
        return portals;
    }

    /**
     * Returns an array of all Connectors
     * @return ArrayUnorderedList
     */
    public ArrayUnorderedList getConnectors() {
        for (T vertice : vertices) {
            if (vertice != null && vertice.getClass() != Portal.class) {
                connectors.addToRear((Connectors) vertice);
            }
        }
        return connectors;
    }

}
