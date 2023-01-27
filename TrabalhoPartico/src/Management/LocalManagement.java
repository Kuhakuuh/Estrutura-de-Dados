/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Enumerations.Estado;
import Locals.Connectors;
import Locals.Local;
import Locals.Portal;
import Player.ConnectorIteraction;
import Player.Player;
import network.Network;

/**
 *
 * @author Tiago Lopes
 * @param <T>
 */
public class LocalManagement<T> {

    private Network<Local> map = new Network<Local>();

    /**
     * Empty construtor
     */
    public LocalManagement() {

    }

    /**
     * Adds a portal to map(graph)
     *
     * @param portal
     */
    public void addPortal(Portal portal) {
        map.addVertex(portal);
    }

    /**
     * Remove a portal from map(graph)
     *
     * @param portal
     */
    public void removePortal(Portal portal) {
        map.removeVertex(portal);
    }

    /**
     * Edits a specific portal
     *
     * @param portal
     * @param name
     * @param energy
     * @param player
     * @param estado
     */
    public void editPortal(Portal portal, String name, int energy, Player player, Estado estado) {
        portal.setEnergyAmount(energy);
        portal.setEstado(estado);
        portal.setNome(name);
        portal.setJogador(player);

    }

    /**
     *
     * Adds a connector to map (graph)
     *
     * @param connector
     */
    public void addConnector(Connectors connector) {
        map.addVertex(connector);
    }

    /**
     * Remove a connector from the map
     *
     * @param connector
     */
    public void removeConnector(Connectors connector) {
        map.removeVertex(connector);
    }

    /**
     * Edits a connector from a map.
     *
     * @param connector
     * @param cooldown
     * @param energy
     */
    public void editConnector(Connectors connector, int cooldown, int energy) {
        connector.setCooldown(cooldown);
        connector.setEnergyAmount(energy);

    }

    /**
     * Adds an iteraction into the specific connector
     *
     * @param connector
     * @param iteraction
     */
    public void addConnectorIteraction(Connectors connector, ConnectorIteraction iteraction) {
        connector.setIteraction(iteraction);
    }

    /**
     * Removes an iteraction into the specific connector
     *
     * @param iteraction
     */
    public void removeConnectorIteraction(ConnectorIteraction iteraction) {
        
    }

    public void listPortals() {

    }

    public void listConnectors() {

    }

    public void importJson() {

    }

    public void exportJson() {

    }

    public Network<Local> getMap() {
        return map;
    }

    public void setMap(Network<Local> map) {
        this.map = map;
    }

}
