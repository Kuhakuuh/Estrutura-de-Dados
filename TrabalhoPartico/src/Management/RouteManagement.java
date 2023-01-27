/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Local;
import network.Network;

/**
 *
 * @author Tiago Lopes
 */
public class RouteManagement<T> {

    Network<Local> map = new Network<>();

    public RouteManagement(Network map) {
        this.map = map;
    }

    public void addRoute(Local local1, Local local2, double weight) {
        map.addEdge(local1, local2, weight);
    }

    public void removeRoute(Local local1, Local local2) {
        map.removeEdge(local1, local2);

    }

    public void importRoute() {

    }

    public void exportRoute() {

    }

}
