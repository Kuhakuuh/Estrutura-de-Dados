/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Local;
import execeptions.InvalidPathValueExeception;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Tiago Lopes
 */
public class ExportManagement {

    private LocalManagement<Local> lm = new LocalManagement<Local>();
    private RouteManagement rm = new RouteManagement(lm.getMap());
    private PlayerManagement pm = new PlayerManagement();
    private GameManagement gm = new GameManagement(lm.getMap());

    /**
     * Construtor do Export
     * @param localManagement
     * @param playerManagement
     * @param routeManagement
     * @param gameManagement 
     */
    public ExportManagement(LocalManagement localManagement, PlayerManagement playerManagement, RouteManagement routeManagement, GameManagement gameManagement) {
        this.lm = localManagement;
        this.pm = playerManagement;
        this.rm = routeManagement;
        this.gm = gameManagement;

    }
    /**
     * Export to a json file with all information about the game
     * @param path
     * @throws IOException
     * @throws InvalidPathValueExeception 
     */
    public void exportData(String path) throws IOException, InvalidPathValueExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        JSONArray locals = lm.exportJson();
        JSONArray routes = rm.exportRoute();
        JSONArray players = pm.exportJson();
        JSONObject shortest = gm.exportShortsPaths();

        JSONObject json = new JSONObject();
        json.put("locals", locals);
        json.put("routes", routes);
        json.put("Players", players);
        json.put("ShortestsPaths", shortest);

        try (FileWriter file = new FileWriter(path)) {
            file.write(json.toJSONString());
            file.flush();
        }

    }

}
