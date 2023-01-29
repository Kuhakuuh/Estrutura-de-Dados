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
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
     * Adds a connector to map(graph)
     *
     * @param portal
     */
    public void addPortal(Portal portal) {
        map.addVertex(portal);
    }

    /**
     * Remove a connector from map(graph)
     *
     * @param portal
     */
    public void removePortal(Portal portal) {
        map.removeVertex(portal);
    }

    /**
     * Edits a specific connector
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
        //Iterator iter = importPortalConnectors(path).iterator();
        Iterator iter = importPortal(path).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }

    public ArrayUnorderedList importPortal(String path) throws FileNotFoundException, IOException, ParseException {
        ArrayUnorderedList<JSONObject> portals = new ArrayUnorderedList<>();
        ArrayUnorderedList<JSONObject> connectors = new ArrayUnorderedList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        JSONArray locals = (JSONArray) json.get("locals");
        for (int i = 0; i < locals.size(); i++) {
            JSONObject local = (JSONObject) locals.get(i);
            if (local.get("type").equals("Portal")) {
                portals.addToRear(local);
            } else if (local.get("type").equals("Connector")) {
                connectors.addToRear(local);
            }
        }
        // Aqui podes trabalhar com os portals e connectors como quiseres
        Iterator iterPortals = portals.iterator();
        ArrayUnorderedList<Portal> portais = new ArrayUnorderedList<>();
        while (iterPortals.hasNext()) {
            JSONObject portalObject = (JSONObject) iterPortals.next();
            Portal tempPortal = new Portal();
            tempPortal.setNome((String) portalObject.get("name"));
            tempPortal.setId((int) (long) portalObject.get("id"));
            JSONObject coordinate = (JSONObject) portalObject.get("coordinates");
            tempPortal.setLatitude((double) coordinate.get("latitude"));
            tempPortal.setLongitude((double) coordinate.get("longitude"));
            JSONObject gameSetting = (JSONObject) portalObject.get("gameSettings");
            tempPortal.setEnergyAmount((int) (long) gameSetting.get("energy"));
            Player tempPlayer = new Player();
            JSONObject player = (JSONObject) gameSetting.get("ownership");
            tempPlayer.setName((String) player.get("player"));
            tempPortal.setJogador(tempPlayer);
            portais.addToRear(tempPortal);
        }
        return portais;
    }

    public JSONArray exportPortal(ArrayUnorderedList<Portal> portals, ArrayUnorderedList<Connectors> connectores, String path) throws IOException {

        JSONArray locals = new JSONArray();
        for (Portal portal : portals) {
            JSONObject jsonPortal = new JSONObject();
            jsonPortal.put("name", portal.getNome());
            jsonPortal.put("id", portal.getId());
            jsonPortal.put("type", "Portal");
            JSONObject coordinates = new JSONObject();
            coordinates.put("latitude", portal.getLatitude());
            coordinates.put("longitude", portal.getLongitude());
            jsonPortal.put("coordinates", coordinates);
            JSONObject gameSettings = new JSONObject();
            gameSettings.put("energy", portal.getEnergyAmount());
            JSONObject ownership = new JSONObject();
            //ownership.put("player", connector.getJogador().getName());
            gameSettings.put("ownership", ownership);
            jsonPortal.put("gameSettings", gameSettings);
            locals.add(jsonPortal);
        }
        for (Connectors connector : connectores) {
            JSONObject jsonConnector = new JSONObject();
            jsonConnector.put("id", connector.getId());
            jsonConnector.put("type", "Connector");
            JSONObject coordinates = new JSONObject();
            coordinates.put("latitude", connector.getLatitude());
            coordinates.put("longitude", connector.getLongitude());
            jsonConnector.put("coordinates", coordinates);
            JSONObject gameSettings = new JSONObject();
            gameSettings.put("energy", connector.getEnergyAmount());
            gameSettings.put("cooldown", connector.getCooldown());
            jsonConnector.put("gameSettings", gameSettings);
            locals.add(jsonConnector);
        }
        return locals;
    }

    public void exportJson(String path) throws IOException {
        JSONObject Jsonportais = new JSONObject();
        JSONArray Jsonlocals = exportPortal(map.getPortals(), map.getConnectors(), path);
        Jsonportais.put("locals", Jsonlocals);
        try (FileWriter file = new FileWriter(path)) {
            file.write(Jsonportais.toJSONString());
            file.flush();
        }
    }

    public Mapa<Local> getMap() {
        return map;
    }

    public void setMap(Mapa<Local> map) {
        this.map = map;
    }

}
