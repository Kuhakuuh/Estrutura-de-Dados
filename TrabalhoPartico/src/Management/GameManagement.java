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
import com.sun.jdi.connect.Connector;
import java.util.Iterator;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class GameManagement {
    
    Mapa<Local> map = new Mapa<>();

    public GameManagement(Mapa map) {
        this.map = map;
    }
    
    
    /**
     * Receive the start and end destination locals and calculates/returns the short path between
     * @param Localstart
     * @param localEnd
     * @return String
     * @throws Excepcions.EmptyCollectionException if the collection is empty
     */
    public String calculateShortestPathBeetweenTwoPoints(Local Localstart, Local localEnd) throws EmptyCollectionException {
        if(map.isEmpty()){
            throw new EmptyCollectionException("Mapa vazio/inexistente");
        }
        String shortPath="";
        Iterator shortPathTwoPoints = map.iteratorShortestPath(Localstart, localEnd);
        while(shortPathTwoPoints.hasNext()){
            shortPath+="\n"+shortPathTwoPoints.next().toString();
        }
        return shortPath;
        
    }
    
    /**
     * Receive the start and end destination locals and calculates/returns the short path between pass only for portals
     * @param startLocal
     * @param endLocal
     * @return String
     * @throws Excepcions.EmptyCollectionException if the collection is empty
     */
    public String calculateShortestPathOnlyThrowPortalsorConnectors(Local startLocal,Local endLocal) throws EmptyCollectionException {
        if(map.isEmpty()){
            throw new EmptyCollectionException("Mapa vazio/inexistente");
        }
        String shortPath="";
        Mapa<Local> mapa2 = map;
        Iterator itermap = mapa2.iteratorBFS(0);
        while(itermap.hasNext()){
            if(itermap.next().getClass()==Connector.class){
                itermap.remove();
            }
        }
        Iterator shortPathOnlyPortals = map.iteratorShortestPath(startLocal, endLocal);
        while(shortPathOnlyPortals.hasNext()){
            shortPath+="\n"+shortPathOnlyPortals.next().toString();
        }
        return shortPath;
        
    }

    public void exportShortsPaths() {

    }

    public void exportGameSettings() {

    }

    public void importGameSettings() {
        
    }
}
