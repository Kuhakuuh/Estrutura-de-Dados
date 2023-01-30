/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Enumerations.Estado;

/**
 * Class that represents a player
 * Player atributs:
 *      name: name of the player
 *      equipa: team that the player belongs
 *      energy: max amount of energy of the player
 *      currentEnergy: current level of the energy of the player
 *      level: level of the player
 *      experience: experience points of the player
 * @author Tiago Lopes, Rafael Dias
 */
public class Player {
    private String name;
    private Estado equipa;
    private int energy;
    private int currentEnergy;
    private int level;
    private double experience;

    /**
     * Returns the name
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /**
     * Assigns the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the team
     * @return Estado
     */
    public Estado getEquipa() {
        return equipa;
    }
    
    /**
     * Assigns the team
     * @param equipa     
     */
    public void setEquipa(Estado equipa) {
        this.equipa = equipa;
    }
    
    /**
     * Returns the energy
     * @return int
     */
    public int getEnergy() {
        return energy;
    }
    
    /**
     * Assigns the energy
     * @param energy     
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    /**
    * Returns the currentEnergy
    * @return int
    */
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * Assigns the currentEnergy
     * @param currentEnergy     
     */
    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    /**
    * Returns the level
    * @return int
    */
    public int getLevel() {
        return level;
    }

    /**
     * Assigns the level
     * @param level     
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
    * Returns the experience
    * @return double
    */
    public double getExperience() {
        return experience;
    }
    
    /**
     * Assigns the experience
     * @param experience     
     */
    public void setExperience(double experience) {
        this.experience = experience;
    }
    
    /**
     * Returns all informtation of the Player in a unique String 
     * @return String
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", equipa=" + equipa + ", energy=" + energy + ", level=" + level + ", experience=" + experience + '}';
    }
    
    
    
}
