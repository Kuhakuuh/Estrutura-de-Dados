/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Local;
import Locals.Mapa;
import execeptions.NullLocalExeception;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class RouteManagement<T> {

    Mapa<Local> map = new Mapa<>();

    public RouteManagement(Mapa map) {
        this.map = map;
    }

    public void addRoute(Local local1, Local local2, double weight) throws NullLocalExeception {
        if (local1 == null || local2 == null) {
            throw new NullLocalExeception("Valor inválido");
        }
        map.addEdge(local1, local2, weight);
    }

    public void removeRoute(Local local1, Local local2) throws NullLocalExeception {
        if (local1 == null || local2 == null) {
            throw new NullLocalExeception("Valor inválido");
        }
        map.removeEdge(local1, local2);

    }

    public void importRoute(String path) {

    }

    public void exportRoute(String path) {

    }

}
