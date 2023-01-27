/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Connectors;
import Locals.Local;
import Locals.Portal;
import Player.ConnectorIteraction;
import network.Network;

/**
 *
 * @author Tiago Lopes
 */
public class LocalManagement<T> {

    Network<Local> map = new Network<Local>();

    public void addPortal(Portal portal) {
        map.addVertex(portal);
    }

    public void removePortal(Portal portal) {
        map.removeVertex(portal);
    }

    public void addConnector(Connectors connector) {
        map.addVertex(connector);
    }

    public void removeConnector(Connectors connector) {
        map.removeVertex(connector);
    }

    public void addConnectorIteraction(ConnectorIteraction iteraction) {

    }

    public void removeConnectorIteraction(ConnectorIteraction iteraction) {

    }

}
