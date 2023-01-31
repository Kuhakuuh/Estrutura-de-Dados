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
 * Class that represents a Connector, who extends the class Local
 * Atributs of Connector:
 *      cooldown: specific range of power supply
 *      iteractions: last interaction between connector and each player
 * @author Tiago Lopes, Rafael Dias
 */
public class Connectors extends Local {

    private int cooldown;
    //private ConnectorIteraction[] iteractions;
    private ArrayUnorderedList<ConnectorIteraction> iteractions = new ArrayUnorderedList<>();
    
    
    /**
     *  Returns the cooldown
     * @return int
     */
    public int getCooldown() {
        return cooldown;
    }
    
    /**
     * Assigns the cooldown
     * @param cooldown
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    
    public void setIteraction(ConnectorIteraction iteraction) {
        this.iteractions.addToRear(iteraction);

    }

    public void removeIteraction(ConnectorIteraction iteraction) throws EmptyCollectionException {
        this.iteractions.remove(iteraction);

    }

    /**
     *  Returns the iteractions
     * @return ArrayUnorderedList
     */
    public ArrayUnorderedList<ConnectorIteraction> getIteractions() {
        return iteractions;
    }

    /**
     * Assigns the iteractions
     * @param iteractions
     */
    public void setIteractions(ArrayUnorderedList<ConnectorIteraction> iteractions) {
        this.iteractions = iteractions;
    }

    /**
     *  Returns all informtation of the connector in a unique String 
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+" Connectors{" + "cooldown=" + cooldown + ", iteractions=" + iteractions + '}';
    }
}
