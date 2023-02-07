/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Locals.Local;
import Locals.Mapa;
import Excepcions.*;
import Locals.*;
import arrayunorderedlist.ArrayUnorderedList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import trabalhopartico.CalculateDistance;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class GameManagement {

    Mapa<Local> map = new Mapa<>();
    ArrayUnorderedList shortesPath = new ArrayUnorderedList<>();
    ArrayUnorderedList shortesPathPortals = new ArrayUnorderedList<>();
    ArrayUnorderedList shortesPathConnectors = new ArrayUnorderedList<>();

    public GameManagement(Mapa map) {
        this.map = map;

    }

    /**
     * Receive the start and end destination locals and calculates/returns the
     * short path between
     *
     * @param Localstart
     * @param localEnd
     * @return String
     * @throws Excepcions.EmptyCollectionException if the collection is empty
     */
    public String calculateShortestPathBeetweenTwoPoints(Local Localstart, Local localEnd) throws EmptyCollectionException {
        if (this.map.isEmpty()) {
            throw new EmptyCollectionException("Mapa vazio/inexistente");
        }
        String shortPath = "";
        Iterator shortPathTwoPoints = this.map.iteratorShortestPath(Localstart, localEnd);
        while (shortPathTwoPoints.hasNext()) {
            Local tempLocal = (Local) shortPathTwoPoints.next();
            this.shortesPath.addToRear(tempLocal.getId());
            shortPath += "\n" + tempLocal.toString();
        }
        return shortPath;
    }

    /**
     * Calculate the shortest path beetween the start and end destination locals
     * only throw Connectors if the path is possible
     *
     * @param start local
     * @param end local
     * @return
     * @throws EmptyCollectionException
     */
    public String calculateShortestPathThrowConnectors(Local start, Local end) throws EmptyCollectionException {
        if (map.isEmpty()) {
            throw new EmptyCollectionException("Mapa vazio/inexistente");
        }
        int[] routes = new int[100];
        int count = 0;
        ArrayUnorderedList portais = map.getPortals();
        Mapa mapaConnectors = map;
        //Copio os ids das rotas
        for (int i = 0; i < map.getNumberVertices(); i++) {
            for (int j = map.getNumberVertices() - 1; j > i; j--) {
                if (map.getAdjMatrixNetwork()[i][j] < Double.POSITIVE_INFINITY) {
                    routes[count] = map.getIdByPos(i);
                    count++;
                    routes[count] = map.getIdByPos(j);
                    count++;
                }
            }
        }
        // removo os connectors
        Iterator iter = mapaConnectors.getPortals().iterator();
        while (iter.hasNext()) {
            Portal tempPortal = (Portal) iter.next();
            map.removeVertex(tempPortal);
        }
        // calculo o caminho mais curto
        String str = "";
        Iterator iterSP = map.iteratorShortestPath(start, end);
        while (iterSP.hasNext()) {
            Connectors tempConnector = (Connectors) iterSP.next();
            this.shortesPathConnectors.addToRear(tempConnector.getId());
            str += "\n" + tempConnector.toString();
        }
        //Volto a adicionar os connectors 
        Iterator iterPortais = portais.iterator();
        while (iterPortais.hasNext()) {
            Portal tempPortal = (Portal) iterPortais.next();
            map.addVertex(tempPortal);
        }
        //volto a adicionar as rotas
        int i = 0;
        CalculateDistance calculate = new CalculateDistance();
        while (count > 0) {
            Local from = map.getLocalById(routes[i]);
            i++;
            Local to = map.getLocalById(routes[i]);
            i++;
            map.addEdge(from,
                    to,
                    calculate.distance(from.getLatitude(),
                            to.getLatitude(),
                            from.getLongitude(),
                            to.getLongitude()));

            count = count - 2;

        }
        //System.out.println(map.toString());
        return str;

    }

    /**
     * Calculate the shortest path beetween the start and end destination locals
     * only throw Portal if the path is possible
     *
     * @param start
     * @param end
     * @return
     * @throws EmptyCollectionException
     */
    public String calculateShortestPathThrowPortals(Local start, Local end) throws EmptyCollectionException {
        if (map.isEmpty()) {
            throw new EmptyCollectionException("Mapa vazio/inexistente");
        }
        int[] routes = new int[100];
        int count = 0;
        ArrayUnorderedList connectors = map.getConnectors();
        Mapa mapaConnectors = map;
        //Copio os ids das rotas
        for (int i = 0; i < map.getNumberVertices(); i++) {
            for (int j = map.getNumberVertices() - 1; j > i; j--) {
                if (map.getAdjMatrixNetwork()[i][j] < Double.POSITIVE_INFINITY) {
                    routes[count] = map.getIdByPos(i);
                    count++;
                    routes[count] = map.getIdByPos(j);
                    count++;
                }
            }
        }
        // removo os connectors
        Iterator iter = mapaConnectors.getConnectors().iterator();
        while (iter.hasNext()) {
            Connectors tempConnectores = (Connectors) iter.next();
            map.removeVertex(tempConnectores);
        }
        // calculo o caminho mais curto
        String str = "";
        Iterator iterSP = map.iteratorShortestPath(start, end);
        while (iterSP.hasNext()) {
            Portal tempPortal = (Portal) iterSP.next();
            this.shortesPathPortals.addToRear(tempPortal.getId());
            str += "\n" + tempPortal.toString();
        }
        //Volto a adicionar os connectors 
        Iterator iterPortais = connectors.iterator();
        while (iterPortais.hasNext()) {
            Connectors tempConnectors = (Connectors) iterPortais.next();
            map.addVertex(tempConnectors);
        }
        //volto a adicionar as rotas
        int i = 0;
        CalculateDistance calculate = new CalculateDistance();
        while (count > 0) {
            Local from = map.getLocalById(routes[i]);
            i++;
            Local to = map.getLocalById(routes[i]);
            i++;
            map.addEdge(from,
                    to,
                    calculate.distance(from.getLatitude(),
                            to.getLatitude(),
                            from.getLongitude(),
                            to.getLongitude()));
            count = count - 2;
        }
        //System.out.println(map.toString());
        return str;

    }

    /**
     * Export all shortest path to a json file
     *
     * @return 
     * @throws IOException
     */
    public JSONObject exportShortsPaths() throws IOException {
        JSONArray shortestPathNormal = new JSONArray();
        Iterator iterNormal = this.shortesPath.iterator();
        while (iterNormal.hasNext()) {
            JSONObject shortestPath = new JSONObject();
            shortestPath.put("Local", iterNormal.next());
            shortestPathNormal.add(shortestPath);
        }

        JSONArray shortestPathPortalsJson = new JSONArray();
        Iterator iter = this.shortesPathPortals.iterator();
        while (iter.hasNext()) {
            JSONObject shortestPath = new JSONObject();
            shortestPath.put("Portal", iter.next());
            shortestPathPortalsJson.add(shortestPath);
        }
        JSONObject spp = new JSONObject();
        spp.put("ShortestPathPortal", shortestPathPortalsJson);

        JSONArray shortestPathConnectorssJson = new JSONArray();
        Iterator iterC = this.shortesPathConnectors.iterator();
        while (iterC.hasNext()) {
            JSONObject shortestPath = new JSONObject();
            shortestPath.put("Portal", iterC.next());
            shortestPathConnectorssJson.add(shortestPath);
        }
        JSONObject spc = new JSONObject();
        spc.put("ShortestPathConnectors", shortestPathConnectorssJson);

        JSONObject shortest = new JSONObject();
        shortest.put("ShortestPath", shortestPathNormal);
        shortest.put("ShortestPathPortal", shortestPathPortalsJson);
        shortest.put("ShortestPathConnector", shortestPathConnectorssJson);
 
        return shortest;
    }

    public void exportGameSettings() {

    }

    public void importGameSettings() {

    }

}
