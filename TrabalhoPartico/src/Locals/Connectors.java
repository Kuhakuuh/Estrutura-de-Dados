/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import Player.ConnectorIteraction;

/**
 *
 * @author Tiago Lopes
 */
public class Connectors extends Local {

    private int cooldown;
    private ConnectorIteraction[] iteractions;

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public ConnectorIteraction[] getIteractions() {
        return iteractions;
    }

    public void setIteractions(ConnectorIteraction[] iteractions) {
        this.iteractions = iteractions;
    }

}
