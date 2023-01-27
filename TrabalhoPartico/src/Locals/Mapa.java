/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import Locals.Connectors;
import Locals.Local;
import Locals.Portal;
import arrayunorderedlist.ArrayUnorderedList;
import network.Network;

/**
 *
 * @author Tiago Lopes
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
     *
     * @return
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
     *
     * @return
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
