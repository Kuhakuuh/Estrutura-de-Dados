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
import arrayunorderedlist.EmptyCollectionException;
import java.util.Iterator;
import network.Network;
import Locals.Mapa;
import arrayunorderedlist.ArrayUnorderedList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Tiago Lopes
 * @param <T>
 */
public class LocalManagement<T> {

    //private Network<Local> map = new Network<Local>();
    private Mapa<Local> map = new Mapa<Local>();

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
     * @param connector
     * @param iteraction
     * @throws arrayunorderedlist.EmptyCollectionException
     */
    public void removeConnectorIteraction(Connectors connector, ConnectorIteraction iteraction) throws EmptyCollectionException {
        connector.removeIteraction(iteraction);
    }

    /**
     * List all portals presents in the Map
     */
    public void listPortals() {
        Iterator iter = map.getPortals().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }

    /**
     * Lists all portals present in the Map
     */
    public void listConnectors() {
        Iterator iter = map.getConnectors().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }

    public void importJson() throws FileNotFoundException, Exception {
        String path = "C:\\Users\\Tiago Lopes\\Documents\\TrabalhoEd\\TrabalhoPartico\\src\\exemplo(1).json";
        Iterator iter = importPortalConnectors(path).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }

    public ArrayUnorderedList importPortalConnectors(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(new FileReader(path), JsonObject.class);
        JsonArray locals = json.getAsJsonArray("locals");
        ArrayUnorderedList<JsonObject> portals = new ArrayUnorderedList<>();
        ArrayUnorderedList<JsonObject> connectors = new ArrayUnorderedList<>();
        for (int i = 0; i < locals.size(); i++) {
            JsonObject local = locals.get(i).getAsJsonObject();
            if (local.get("type").getAsString().equals("Portal")) {
                portals.addToRear(local);
            } else if (local.get("type").getAsString().equals("Connector")) {
                connectors.addToRear(local);
            }
        }
        // Aqui podes trabalhar com os portals e connectors como quiseres
        Iterator iterPortals = portals.iterator();
        ArrayUnorderedList<Portal> portais = new ArrayUnorderedList<>();
        while (iterPortals.hasNext()) {
            JsonObject portalObject = (JsonObject) iterPortals.next();
            Portal tempPortal = new Portal();
            tempPortal.setNome(portalObject.get("name").getAsString());
            tempPortal.setId(portalObject.get("id").getAsInt());
            tempPortal.setLatitude(portalObject.get("coordinates").
                    getAsJsonObject().get("latitude").getAsDouble());
            tempPortal.setLongitude(portalObject.get("coordinates")
                    .getAsJsonObject().get("longitude").getAsDouble());
            tempPortal.setEnergyAmount(portalObject.get("gameSettings")
                    .getAsJsonObject().get("energy").getAsInt());
            Player tempPlayer = new Player();
            tempPlayer.setName(portalObject.get("gameSettings")
                    .getAsJsonObject().get("ownership").getAsJsonObject()
                    .get("player").getAsString());
            tempPortal.setJogador(tempPlayer);
            portais.addToRear(tempPortal);
        }
        return portais;
    }

    public Object importLocal(String jsonFile) throws Exception {
        Gson gson = new Gson();
        Object locals = gson.fromJson(new FileReader(jsonFile), Object.class);
        return locals;
    }

    public void exportJson() {

    }

    public Mapa<Local> getMap() {
        return map;
    }

    public void setMap(Mapa<Local> map) {
        this.map = map;
    }

}
