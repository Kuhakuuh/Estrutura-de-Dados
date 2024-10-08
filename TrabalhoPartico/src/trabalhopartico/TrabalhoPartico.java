/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopartico;

import Enumerations.Estado;
import Locals.Connectors;
import Locals.Portal;
import Management.ExportManagement;
import Management.GameManagement;
import Management.LocalManagement;
import Management.PlayerManagement;
import Management.RouteManagement;
import Player.ConnectorIteraction;
import Player.Player;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class TrabalhoPartico {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        CalculateDistance calculate = new CalculateDistance();

        System.out.println(calculate.distance(40.7656918, 40.7697989, -73.9737489, -73.9723702));
        System.out.println("########################################");
        LocalManagement lm = new LocalManagement();

        Player player1 = new Player();
        player1.setName("Kuuhaku Sensei");
        player1.setCurrentEnergy(2500);
        player1.setEnergy(10000);
        player1.setExperience(34235345);
        player1.setLevel(10);
        player1.setEquipa(Estado.SPARKS);

        Player player2 = new Player();
        player2.setName("Raickou");
        player2.setCurrentEnergy(2500);
        player2.setEnergy(10000);
        player2.setExperience(34235345);
        player2.setLevel(10);
        player2.setEquipa(Estado.GIANTS);

        Portal portal1 = new Portal();
        portal1.setNome("Ionia");
        portal1.setId(110);
        portal1.setEstado(Estado.NEUTRO);
        portal1.setEnergyAmount(150);
        portal1.setLatitude(35.432542);
        portal1.setLongitude(-44.973748);
        portal1.setJogador(player1);

        Portal portal2 = new Portal();
        portal2.setNome("Demacia");
        portal2.setId(112);
        portal2.setEstado(Estado.GIANTS);
        portal2.setEnergyAmount(140);
        portal2.setLatitude(90.432542);
        portal2.setLongitude(-32.973748);
        portal2.setJogador(player2);

        Portal portal3 = new Portal();
        portal3.setNome("Piltover");
        portal3.setId(113);
        portal3.setEstado(Estado.SPARKS);
        portal3.setEnergyAmount(130);
        portal3.setLatitude(67.432542);
        portal3.setLongitude(-42.973748);
        portal3.setJogador(player1);

        Portal portal4 = new Portal();
        portal4.setNome("Noxus");
        portal4.setId(114);
        portal4.setEstado(Estado.SPARKS);
        portal4.setEnergyAmount(120);
        portal4.setLatitude(91.432542);
        portal4.setLongitude(-13.973748);
        portal4.setJogador(player2);

        ConnectorIteraction ci = new ConnectorIteraction();
        ci.setLastIteraction(LocalDateTime.now());
        ci.setPlayer(player1.getName());

        Connectors c1 = new Connectors();
        c1.setCooldown(5000);
        c1.setEnergyAmount(140);
        c1.setId(111);
        c1.setLatitude(23.23123);
        c1.setLatitude(-53.2314);
        c1.setIteraction(ci);

        Connectors c2 = new Connectors();
        c2.setCooldown(5000);
        c2.setEnergyAmount(140);
        c2.setId(222);
        c2.setLatitude(32.23123);
        c2.setLatitude(-22.2314);

        lm.addPortal(portal1);
        lm.addPortal(portal2);
        lm.addPortal(portal3);
        lm.addPortal(portal4);
        lm.addConnector(c1);
        lm.addConnector(c2);
        RouteManagement rm = new RouteManagement(lm.getMap());

        rm.addRoute(portal1, c1, calculate.distance(portal1.getLatitude(), c1.getLatitude(), portal1.getLongitude(), c1.getLongitude()));
        rm.addRoute(portal2, c1, calculate.distance(portal2.getLatitude(), c1.getLatitude(), portal2.getLongitude(), c1.getLongitude()));
        rm.addRoute(portal3, c1, calculate.distance(portal3.getLatitude(), c1.getLatitude(), portal3.getLongitude(), c1.getLongitude()));
        rm.addRoute(portal4, c1, calculate.distance(portal4.getLatitude(), c1.getLatitude(), portal4.getLongitude(), c1.getLongitude()));
        rm.addRoute(portal1, portal2, 1.5445);
        rm.addRoute(portal2, portal3, 1.3043);
        rm.addRoute(c1, c2, 1.56);
        System.out.println(lm.getMap().isConnected());
        lm.importJson("src/exemplo(1).json");
        rm.importRoute("src/exemplo(1).json");

        lm.listPortals();
        lm.listConnectors();
        System.out.println(lm.getMap().toString());
        System.out.println(lm.getMap().shortestPathWeight(portal4, portal1));
        //lm.importJson();
        System.out.println("-----Caminho mas curto------");
        Iterator iter = lm.getMap().iteratorShortestPath(portal2, c1);
        while (iter.hasNext()) {

            System.out.println("Ponto:" + iter.next());
        }

        PlayerManagement pm = new PlayerManagement();
        pm.addPlayer("Raickou");
        pm.addPlayer("Elafar");
        pm.addTeam("Elafar", Estado.SPARKS);
        System.out.println(pm.toString());
        pm.addTeam("Raickou", Estado.GIANTS);
        pm.addPlayer("P3");
        pm.updatePlayer("Raickou", -1, -1, 3, 5433, true, 2);
        pm.updatePlayer("Elafar", -1, -1, 4, 543234, true, 6);
        pm.updatePlayer("P3", -1, -1, 1, 12353, true, 3);
        pm.importJson("src/exemplo(1).json");

        System.out.println(pm.toString());
        System.out.println("\nLista de jogadores ordenados de forma crescente pelo level" + pm.listPlayerPerLevel());
        System.out.println("\nLista de jogadores ordenados de forma crescente pelo numero de portais conquistados" + pm.listPlayerPerConquestPortals());

        System.out.println(lm.getMap().toString());

        GameManagement gm = new GameManagement(lm.getMap());

        System.out.println("\n\nShort Path\n\n");
        System.out.println(gm.calculateShortestPathBeetweenTwoPoints(portal2, portal3));

        System.out.println("\n\nShort Path throw portals\n\n");
        System.out.println(gm.calculateShortestPathThrowPortals(portal2, portal3));

        System.out.println("\n\nShort Path2\n\n");

        System.out.println("\n\nShort Path throw connectors\n\n");
        System.out.println(gm.calculateShortestPathThrowConnectors(c1, c2));
        System.out.println(lm.getMap().toString());
        ExportManagement export = new ExportManagement(lm, pm, rm, gm);
        export.exportData("src/game.json");

    }

}
