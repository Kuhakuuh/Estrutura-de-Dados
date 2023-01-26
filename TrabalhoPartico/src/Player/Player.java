/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Enumerations.Estado;

/**
 *
 * @author Tiago Lopes
 */
public class Player {
    private String name;
    private Estado equipa;
    private int energy;
    private int level;
    private double experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Estado getEquipa() {
        return equipa;
    }

    public void setEquipa(Estado equipa) {
        this.equipa = equipa;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }
    
    
    
}
