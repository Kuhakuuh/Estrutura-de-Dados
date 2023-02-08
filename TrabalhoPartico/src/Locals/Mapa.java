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

    /**
     * Return ab ArrayUnorderedList of all locals
     *
     * @return
     */
    public ArrayUnorderedList getLocals() {
        ArrayUnorderedList<Local> tempLocal = new ArrayUnorderedList<Local>();
        for (T vertice : vertices) {
            if (vertice != null) {
                tempLocal.addToRear((Local) vertice);
            }
        }
        return tempLocal;
    }

    /**
     * Return the local id base on position
     *
     * @param pos
     * @return
     */
    public int getIdByPos(int pos) {
        Local tempLocal = (Local) this.vertices[pos];
        return tempLocal.getId();
    }

    /**
     * Return a Local with the same id
     *
     * @param id
     * @return
     */
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

}
