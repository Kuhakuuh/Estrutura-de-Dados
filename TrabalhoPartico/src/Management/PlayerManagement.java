/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import Enumerations.Estado;
import Excepcions.*;
import Player.Player;
import Excepcions.InvalidTeamException;
import arrayOrderedList.ArrayOrderedList;
import arrayunorderedlist.*;
import execeptions.InvalidPathValueExeception;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import linkedheap.PriorityQueueNode;
import linkedstack.LinkedStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class used to managed the players
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class PlayerManagement {

    ArrayUnorderedList<Player> playersList = new ArrayUnorderedList<Player>();

    /**
     * Empty construtor
     */
    public PlayerManagement() {

    }
    
    /**
     *  Method that return the list of players
     * @return ArrayUnorderedList of type Player
     */
    public ArrayUnorderedList<Player> getPlayerList(){
        return playersList;
    }
    
    /**
     * Method tha returns the number of players
     * @return int
     */
    public int numbPlayers(){
        return playersList.size();
    }
    
    /**
     * Method that adds a new player receiving the name
     *
     * @param name
     * @throws Excepcions.InvalidNameException when alredy exits a player with
     * that name
     */
    public void addPlayer(String name) throws InvalidNameException {
        if (validateName(name)) {
            throw new InvalidNameException("Invalid name, name alredy in use");
        }
        Player jogador = new Player(name);
        playersList.addToRear(jogador);
    }

    /**
     * Method tha checks if the name of the new player is valid
     *
     * @param nome
     * @return boolean
     */
    private boolean validateName(String nome) {
        Iterator iterPlayer = playersList.iterator();
        Boolean exist = false;
        while (iterPlayer.hasNext()) {
            Player jogador = (Player) iterPlayer.next();
            if (nome.equals(jogador.getName())) {
                exist = true;
            }

        }
        return exist;
    }

    /**
     * Method tha turns the player disable to play
     *
     * @param name
     * @throws Excepcions.NoSuchElementeException if the request element dont
     * exist
     */
    public void removePlayer(String name) throws NoSuchElementeException {
        Player jogador = findPlayer(name);
        if (jogador == null) {
            throw new NoSuchElementeException("The requested player dont exist");
        }
        jogador.setEnable(false);
    }

    /**
     * Method to update determined atributs with changes of the player except
     * the name and team If the received atributs is -1 tha indicates that
     * atribut will not be change, the enable atribute receives always the
     * boolean passed value
     *
     * @param name
     * @param energy
     * @param currentEnergy
     * @param level
     * @param experience
     * @param enable
     * @param numConquerPortals
     * @throws Excepcions.NoSuchElementeException if the request element dont
     * exist
     */
    public void updatePlayer(String name, int energy, int currentEnergy, int level, long experience, boolean enable, int numConquerPortals) throws NoSuchElementeException {
        Player jogador = findPlayer(name);
        if (jogador == null) {
            throw new NoSuchElementeException("The requested player dont exist");
        }
        if (energy > -1) {
            jogador.setEnergy(energy);
        }
        if (currentEnergy > -1) {
            jogador.setCurrentEnergy(currentEnergy);
        }
        if (level > -1) {
            jogador.setLevel(level);
        }
        if (experience > -1) {
            jogador.setExperience(experience);
        }
        jogador.setEnable(enable);
        if (numConquerPortals > -1) {
            jogador.setNumConquerPortals(numConquerPortals);
        }
    }

    /**
     * Method thats adds the team to the requested player by name
     *
     * @param nome
     * @param equipa
     * @throws Excepcions.InvalidTeamException if the team is invalid
     * @throws Excepcions.NoSuchElementeException if the request element dont
     * exist
     */
    public void addTeam(String nome, Estado equipa) throws InvalidTeamException, NoSuchElementeException {
        Player jogador = findPlayer(nome);
        if (jogador == null) {
            throw new NoSuchElementeException("The requested player dont exist");
        }
        if (!equipa.equals(Estado.GIANTS) && !equipa.equals(Estado.SPARKS)) {
            throw new InvalidTeamException("Invalid tem, teams available are GIANTS or SPARKS");
        }
        jogador.setEquipa(equipa);

    }

    /**
     * Find and returns the player with the requestes name or null if dont find
     *
     * @param name
     * @return Player
     */
    public Player findPlayer(String name) {
        Iterator iter = playersList.iterator();
        Player jogador;
        while (iter.hasNext()) {
            jogador = (Player) iter.next();
            if (name.equals(jogador.getName())) {
                return jogador;
            }
        }
        return null;
    }

    /**
     * Method that removes the team to the requested player by name
     *
     * @param name
     * @throws Excepcions.NoSuchElementeException if the request element dont
     * exist
     */
    public void removeTeam(String name) throws NoSuchElementeException {
        Player jogador = findPlayer(name);
        if (jogador == null) {
            throw new NoSuchElementeException("The requested player dont exist");
        }
        if (jogador.getEquipa() == null) {
            throw new NoSuchElementeException("The requested player dont had team to remove");
        }
        jogador.setEquipa(Estado.NEUTRO);
    }

    /**
     * Method that return list of the playres per team first the team GIANT
     * players, SPARKS players and then players that dont have team
     */
    public String listPlayerPerTeam() {
        LinkedStack<Player> teamSparks = new LinkedStack();
        LinkedStack<Player> teamGiants = new LinkedStack();
        LinkedStack<Player> noTeam = new LinkedStack();
        Iterator iterPlayer = playersList.iterator();
        while (iterPlayer.hasNext()) {
            Player jogador = (Player) iterPlayer.next();
            switch (jogador.getEquipa()) {
                case SPARKS:
                    teamSparks.push(jogador);
                    break;
                case GIANTS:
                    teamGiants.push(jogador);
                    break;
                default:
                    noTeam.push(jogador);
                    break;
            }
        }
        return ("Team GIANTS\n" + teamGiants.toString() + "\nTeam SPARKS\n" + teamSparks.toString()
                + "\nSem equipa\n" + noTeam.toString());

    }

    /**
     * Method that returns the list the players in crescent order by level
     *
     * @return String
     */
    public String listPlayerPerLevel() {
        ArrayOrderedList<PriorityQueueNode<Player>> listPlayerPerLevel = new ArrayOrderedList();
        Iterator iterPlayers = playersList.iterator();
        while (iterPlayers.hasNext()) {
            Player jogador = (Player) iterPlayers.next();
            PriorityQueueNode<Player> jogadorLevel = new PriorityQueueNode(jogador, jogador.getLevel());
            listPlayerPerLevel.add(jogadorLevel);
        }
        return listPlayerPerLevel.toString();
    }

    /**
     * Method that returns the list the players in crescent order by number of
     * portals conquer
     *
     * @return String
     */
    public String listPlayerPerConquestPortals() {
        ArrayOrderedList<PriorityQueueNode<Player>> listPlayerPerConquestPortals = new ArrayOrderedList();
        Iterator iterPlayers = playersList.iterator();
        while (iterPlayers.hasNext()) {
            Player jogador = (Player) iterPlayers.next();
            PriorityQueueNode<Player> jogadorConquerPortals = new PriorityQueueNode(jogador, jogador.getNumConquerPortals());
            listPlayerPerConquestPortals.add(jogadorConquerPortals);
        }
        return listPlayerPerConquestPortals.toString();
    }

    public void importJson(String path) throws InvalidPathValueExeception, FileNotFoundException, IOException, ParseException {
        if ("".equals(path)) {
            throw new InvalidPathValueExeception("Valor inválido");
        }
        ArrayUnorderedList<JSONObject> playersList = new ArrayUnorderedList<>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject json = (JSONObject) obj;
        JSONArray players = (JSONArray) json.get("Players");

        for (int i = 0; i < players.size(); i++) {
            JSONObject player = (JSONObject) players.get(i);
            playersList.addToRear(player);
        }
        for (JSONObject player : playersList) {
            Player tempPlayer = new Player(
                    (String) player.get("name"),
                    getPlayerTeam((String) player.get("team")),
                    (int) (long) player.get("currentEnergy"),
                    (int) (long) player.get("level"),
                    (long) player.get("experiencePoints"),
                    true);
            this.playersList.addToRear(tempPlayer);
        }
    }

    /**
     * Convert a string to enum
     *
     * @param team
     * @return
     */
    public Estado getPlayerTeam(String team) {
        return switch (team) {
            case "Sparks" ->
                Estado.SPARKS;
            case "Giants" ->
                Estado.GIANTS;
            default ->
                Estado.NEUTRO;
        };
    }

    public String estadoToString(Estado team) {
        return switch (team) {
            case GIANTS ->
                "Giants";
            case SPARKS ->
                "Sparks";
            default ->
                "Neutro";
        };
    }

    public JSONArray exportJson() throws IOException {
        JSONArray playerList = new JSONArray();
        for (Player player : this.playersList) {
            JSONObject jsonPlayer = new JSONObject();
            jsonPlayer.put("name", player.getName());
            jsonPlayer.put("team", estadoToString(player.getEquipa()));
            jsonPlayer.put("currentEnergy", player.getCurrentEnergy());
            jsonPlayer.put("level", player.getLevel());
            jsonPlayer.put("experiencePoints", player.getExperience());
            playerList.add(jsonPlayer);
        }

        return playerList;

    }

    /**
     * Returns a string with all the information on the clients list
     *
     * @return String
     */
    @Override
    public String toString() {
        Iterator iterPlayers = playersList.iterator();
        String msg = "Lista de jogadores";
        while (iterPlayers.hasNext()) {
            Player jogador = (Player) iterPlayers.next();
            if (jogador.getEnable()) {
                msg += "\n" + jogador.toString();
            }
        }
        return msg;
    }

}
