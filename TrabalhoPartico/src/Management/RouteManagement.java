/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Connectors;
import Locals.Local;
import Locals.Mapa;
import Locals.Portal;
import arrayunorderedlist.ArrayUnorderedList;
import execeptions.InvalidPathValueExeception;
import execeptions.NullLocalExeception;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import trabalhopartico.CalculateDistance;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 * @param <T>
 */
public class RouteManagement<T> {

    Mapa<Local> map = new Mapa<>();
    private int numberOfRoutes;

    public RouteManagement(Mapa map) {
        this.map = map;
        this.numberOfRoutes = 0;
    }

    /**
     * Adds a route(edge) to map
     *
     * @param local1
     * @param local2
     * @param weight
     * @throws NullLocalExeception
     */
    public void addRoute(Local local1, Local local2, double weight) throws NullLocalExeception {
        if (local1 == null || local2 == null) {
            throw new NullLocalExeception("Valor inválido");
        }
        map.addEdge(local1, local2, weight);
        this.numberOfRoutes++;
    }

    /**
     * Removes a route from the map
     *
     * @param local1
     * @param local2
     * @throws NullLocalExeception
     */
    public void removeRoute(Local local1, Local local2) throws NullLocalExeception {
        if (local1 == null || local2 == null) {
            throw new NullLocalExeception("Valor inválido");
        }
        map.removeEdge(local1, local2);
        this.numberOfRoutes--;

    }

    /**
     * Imports the routes from the json file assuming that the locals are
     * already imported
     *
     * @param path
     * @throws execeptions.InvalidPathValueExeception
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     * @throws execeptions.NullLocalExeception
     */
    public void importRoute(String path) throws InvalidPathValueExeception, FileNotFoundException, IOException, ParseException, NullLocalExeception {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        ArrayUnorderedList<JSONObject> routesList = new ArrayUnorderedList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        JSONArray routes = (JSONArray) json.get("routes");
        for (int i = 0; i < routes.size(); i++) {
            JSONObject route = (JSONObject) routes.get(i);
            routesList.addToRear(route);
        }
        CalculateDistance calculate = new CalculateDistance();
        Iterator iterRoute = routesList.iterator();
        while (iterRoute.hasNext()) {
            JSONObject route = (JSONObject) iterRoute.next();
            Local local1 = findLocalById((int) (long) route.get("from"));
            Local local2 = findLocalById((int) (long) route.get("to"));
            addRoute(local1, local2, calculate.distance(local1.getLatitude(),
                    local2.getLatitude(), local1.getLongitude(),
                    local2.getLongitude()));
        }
    }

    private Local findLocalById(int id) {
        Iterator iter = map.getLocals().iterator();
        while (iter.hasNext()) {
            Local tempLocal = (Local) iter.next();
            if (tempLocal.getId() == id) {
                return tempLocal;
            }
        }
        return null;
    }

    private int getIndiceByPos(int pos) {
        return map.getIdByPos(pos);
    }

    public JSONArray exportRoute() throws IOException {
        JSONArray routesArray = new JSONArray();
        for (int i = 0; i < map.getNumberVertices(); i++) {
            for (int j = map.getNumberVertices() - 1; j > i; j--) {
                if (map.getAdjMatrixNetwork()[i][j] < Double.POSITIVE_INFINITY) {
                    JSONObject route = new JSONObject();
                    route.put("from", getIndiceByPos(i));
                    route.put("to", getIndiceByPos(j));
                    routesArray.add(route);
                }
            }
        }
        return routesArray;
    }

}
