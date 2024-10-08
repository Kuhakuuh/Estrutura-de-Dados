package Player;

import Enumerations.Estado;
import Locals.Local;

/**
 * Class that represents a player Player atributs: name: name of the player
 * equipa: team that the player belongs energy: max amount of energy of the
 * player currentEnergy: current level of the energy of the player level: level
 * of the player experience: experience points of the player startLevel: start
 * level of the new player startEnergy: start energy of the new player enable:
 * indicates if the player is playable numConquerPortals: number of portals
 * conquer by the player
 *
 * @author Tiago Lopes, Rafael Dias
 */
public class Player implements Comparable {

    private String name;
    private Estado equipa;
    private int energy;
    private int currentEnergy;
    private long experience;
    private int level;

    private boolean enable;
    private int numConquerPortals;
    private Local position;

    private final int startLevel = 1;
    private final int startEnergy = 50;
    private final double X = 0.07;
    private final int Y = 2;

    /**
     * Empty construtor
     */
    public Player() {
        this.level = (int) (X * Math.sqrt(experience));

    }

    /**
     * Full Construtor
     *
     * @param name
     * @param equipa
     * @param energy
     * @param currentEnergy
     * @param experience
     * @param enable
     * @param numConquerPortals
     */
    public Player(String name, Estado equipa, int energy, int currentEnergy, long experience, boolean enable, int numConquerPortals) {
        this.level = (int) (X * Math.sqrt(experience));
        this.name = name;
        this.equipa = equipa;
        this.energy = energy;
        this.currentEnergy = currentEnergy;
        this.level = level;
        this.experience = experience;
        this.enable = enable;
        this.numConquerPortals = numConquerPortals;
    }

    /**
     * Construtor for import
     *
     * @param name
     * @param equipa
     * @param currentEnergy
     * @param level
     * @param experience
     * @param enable
     */
    public Player(String name, Estado equipa, int currentEnergy, int level, long experience, boolean enable) {
        this.level = (int) (X * Math.sqrt(experience));
        this.name = name;
        this.equipa = equipa;
        this.currentEnergy = currentEnergy;
        this.level = level;
        this.experience = experience;
        this.enable = enable;

    }

    /**
     * Construtor that receives the name of the new player
     *
     * @param name
     */
    public Player(String name) {
        this.level = (int) (X * Math.sqrt(experience));
        this.name = name;
        this.energy = startEnergy;
        this.currentEnergy = startEnergy;
        this.level = startLevel;
        this.experience = 0;
        this.enable = true;
        this.equipa = Estado.NEUTRO;
        this.numConquerPortals = 0;
    }

    public void setLevel(long experience) {
        this.level = (int) (X * Math.sqrt(experience));
    }

    /**
     * Returns the name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the team
     *
     * @return Estado
     */
    public Estado getEquipa() {
        return equipa;
    }

    /**
     * Assigns the team
     *
     * @param equipa
     */
    public void setEquipa(Estado equipa) {
        this.equipa = equipa;
    }

    /**
     * Returns the energy
     *
     * @return int
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Assigns the energy
     *
     * @param energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Returns the currentEnergy
     *
     * @return int
     */
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * Assigns the currentEnergy
     *
     * @param currentEnergy
     */
    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    /**
     * Returns the level
     *
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Assigns the level
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Assigns the level base on experience points
     */
    public void setLevel() {
        this.level = this.level = (int) (X * Math.sqrt(experience));
    }

    /**
     * Returns the experience
     *
     * @return double
     */
    public long getExperience() {
        return experience;
    }

    /**
     * Assigns the experience
     *
     * @param experience
     */
    public void setExperience(long experience) {
        this.experience = experience;
    }

    /**
     * Returns the enable state of the player
     *
     * @return boolean
     */
    public boolean getEnable() {
        return enable;
    }

    /**
     * Assigns the enable state of the player
     *
     * @param enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * Returns the number of conquer portals
     *
     * @return int
     */
    public int getNumConquerPortals() {
        return numConquerPortals;
    }

    /**
     * Assigns the number of conquer portals
     *
     * @param numConquerPortals
     */
    public void setNumConquerPortals(int numConquerPortals) {
        this.numConquerPortals = numConquerPortals;
    }

    /**
     * Returns the position of the player
     *
     * @return Local
     */
    public Local getPosition() {
        return position;
    }

    /**
     * Assigns the position of the player
     *
     * @param position
     */
    public void setPosition(Local position) {
        this.position = position;
    }

    /**
     * Returns all informtation of the Player in a unique String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", equipa=" + equipa + ", energy=" + energy + ", level=" + level + ", experience=" + experience + '}';
    }

    @Override
    public int compareTo(Object player) {
        Player jogador = (Player) player;
        return this.name.compareTo(jogador.getName());
    }

}
