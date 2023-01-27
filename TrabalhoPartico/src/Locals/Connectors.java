/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import Player.ConnectorIteraction;
import arrayunorderedlist.ArrayUnorderedList;
import arrayunorderedlist.EmptyCollectionException;

/**
 *
 * @author Tiago Lopes
 */
public class Connectors extends Local {

    private int cooldown;
    //private ConnectorIteraction[] iteractions;
    private ArrayUnorderedList<ConnectorIteraction> iteractions = new ArrayUnorderedList<>();

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setIteraction(ConnectorIteraction iteraction) {
        this.iteractions.addToRear(iteraction);

    }

    public void removeIteraction(ConnectorIteraction iteraction) throws EmptyCollectionException {
        this.iteractions.remove(iteraction);

    }

    public ArrayUnorderedList<ConnectorIteraction> getIteractions() {
        return iteractions;
    }

    public void setIteractions(ArrayUnorderedList<ConnectorIteraction> iteractions) {
        this.iteractions = iteractions;
    }

    @Override
    public String toString() {
        return "Connectors{" + "cooldown=" + cooldown + ", iteractions=" + iteractions.toString() + '}';
    }
}
