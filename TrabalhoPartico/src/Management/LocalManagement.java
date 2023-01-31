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
import Locals.Mapa;
import arrayunorderedlist.ArrayUnorderedList;
import execeptions.InvalidPathValueExeception;
import execeptions.NullInterationExeception;
import execeptions.NullLocalExeception;
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
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class LocalManagement<T> {

    private Mapa<Local> map = new Mapa<Local>();
    
    /**
     * Empty construtor
     */
    public LocalManagement() {
        super();
    }

    /**
     * Adds a connector to map(graph)
     *
     * @param portal portal to be added
     * @throws execeptions.NullLocalExeception if portal was null
     */
    public void addPortal(Portal portal) throws NullLocalExeception {
        if (portal == null) {
            throw new NullLocalExeception("Valor null");
        }
        map.addVertex(portal);
    }

    /**
     * Remove a connector from map(graph)
     *
     * @param portal portal to be removed
     * @throws execeptions.NullLocalExeception if portal was null
     */
    public void removePortal(Portal portal) throws NullLocalExeception {
        if (portal == null) {
            throw new NullLocalExeception("Valor null");
        }
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
     * @throws execeptions.NullLocalExeception
     */
    public void editPortal(Portal portal, String name, int energy, Player player, Estado estado) throws NullLocalExeception {
        if (portal == null) {
            throw new NullLocalExeception("Valor null");
        }
        portal.setEnergyAmount(energy);
        portal.setEstado(estado);
        portal.setNome(name);
        portal.setJogador(player);

    }

    /**
     *
     * Adds a connector to map (graph)
     *
     * @param connector to be added
     * @throws execeptions.NullLocalExeception
     */
    public void addConnector(Connectors connector) throws NullLocalExeception {
        if (connector == null) {
            throw new NullLocalExeception("Valor null");
        }
        map.addVertex(connector);
    }

    /**
     * Remove a connector from the map
     *
     * @param connector
     * @throws execeptions.NullLocalExeception
     */
    public void removeConnector(Connectors connector) throws NullLocalExeception {
        if (connector == null) {
            throw new NullLocalExeception("Valor null");
        }
        map.removeVertex(connector);
    }

    /**
     * Edits a connector from a map.
     *
     * @param connector
     * @param cooldown
     * @param energy
     * @throws execeptions.NullLocalExeception
     */
    public void editConnector(Connectors connector, int cooldown, int energy) throws NullLocalExeception {
        if (connector == null) {
            throw new NullLocalExeception("Valor null");
        }
        connector.setCooldown(cooldown);
        connector.setEnergyAmount(energy);

    }

    /**
     * Adds an iteraction into the specific connector
     *
     * @param connector
     * @param iteraction
     * @throws execeptions.NullLocalExeception
     * @throws execeptions.NullInterationExeception
     */
    public void addConnectorIteraction(Connectors connector, ConnectorIteraction iteraction) throws NullLocalExeception, NullInterationExeception {
        if (connector == null) {
            throw new NullLocalExeception("Valor null");
        } else if (iteraction == null) {
            throw new NullInterationExeception("Valor null");
        }
        connector.setIteraction(iteraction);
    }

    /**
     * Removes an iteraction into the specific connector
     *
     * @param connector
     * @param iteraction
     * @throws arrayunorderedlist.EmptyCollectionException
     */
    public void removeConnectorIteraction(Connectors connector, ConnectorIteraction iteraction) throws EmptyCollectionException, NullLocalExeception, NullInterationExeception {
        if (connector == null) {
            throw new NullLocalExeception("Valor null");
        } else if (iteraction == null) {
            throw new NullInterationExeception("Valor null");
        }
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

    /**
     * Imports the data from the file
     *
     * @param path
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void importJson(String path) throws FileNotFoundException, Exception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        Iterator iterPortals = importPortals(path).iterator();
        while (iterPortals.hasNext()) {
            Portal tempPortal = (Portal) iterPortals.next();
            addPortal(tempPortal);
        }
        Iterator iterConnectors = importConnectors(path).iterator();
        while (iterConnectors.hasNext()) {
            Connectors tempConnectors = (Connectors) iterConnectors.next();
            addConnector(tempConnectors);
        }
    }

    /**
     * Imports only the portals and returns a list
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws InvalidPathValueExeception
     */
    public ArrayUnorderedList importPortals(String path) throws FileNotFoundException, IOException, ParseException, InvalidPathValueExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }

        ArrayUnorderedList<JSONObject> portals = new ArrayUnorderedList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        JSONArray locals = (JSONArray) json.get("locals");
        for (int i = 0; i < locals.size(); i++) {
            JSONObject local = (JSONObject) locals.get(i);
            if (local.get("type").equals("Portal")) {
                portals.addToRear(local);
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

    /**
     * Imports only connectors and returns a list
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws InvalidPathValueExeception
     */
    public ArrayUnorderedList importConnectors(String path) throws FileNotFoundException, IOException, ParseException, InvalidPathValueExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        ArrayUnorderedList<JSONObject> connectors = new ArrayUnorderedList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        JSONArray locals = (JSONArray) json.get("locals");
        for (int i = 0; i < locals.size(); i++) {
            JSONObject local = (JSONObject) locals.get(i);
            if (local.get("type").equals("Connector")) {
                connectors.addToRear(local);
            }
        }
        // Aqui podes trabalhar com os portals e connectors como quiseres
        Iterator iterConnectores = connectors.iterator();
        ArrayUnorderedList<Connectors> connectores = new ArrayUnorderedList<Connectors>();
        while (iterConnectores.hasNext()) {
            JSONObject portalObject = (JSONObject) iterConnectores.next();
            Connectors tempConnector = new Connectors();
            tempConnector.setId((int) (long) portalObject.get("id"));
            JSONObject coordinate = (JSONObject) portalObject.get("coordinates");
            tempConnector.setLatitude((double) coordinate.get("latitude"));
            tempConnector.setLongitude((double) coordinate.get("longitude"));
            JSONObject gameSetting = (JSONObject) portalObject.get("gameSettings");
            tempConnector.setEnergyAmount((int) (long) gameSetting.get("energy"));
            tempConnector.setCooldown((int) (long) gameSetting.get("cooldown"));
            connectores.addToRear(tempConnector);
        }
        return connectores;
    }

    /**
     * Returns an JSONArray of all Locals
     *
     * @param path
     * @return
     * @throws IOException
     * @throws InvalidPathValueExeception
     */
    public JSONArray exportLocals(String path) throws IOException, InvalidPathValueExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        ArrayUnorderedList<Portal> portals = map.getPortals();
        ArrayUnorderedList<Connectors> connectores = map.getConnectors();
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
            ownership.put("player", portal.getJogador().getName());
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


    /**
     * Export the data of Locals to a json file
     *
     * @param path
     * @throws IOException
     * @throws InvalidPathValueExeception
     */
    public void exportJson(String path) throws IOException, InvalidPathValueExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        JSONObject Jsonportais = new JSONObject();
        JSONArray Jsonlocals = exportLocals(path);
        Jsonportais.put("locals", Jsonlocals);
        try (FileWriter file = new FileWriter(path)) {
            file.write(Jsonportais.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns the map
     *
     * @return
     */
    public Mapa<Local> getMap() {
        return map;
    }

    /**
     * Assign the map
     *
     * @param map
     */
    public void setMap(Mapa<Local> map) {
        this.map = map;
    }

}
