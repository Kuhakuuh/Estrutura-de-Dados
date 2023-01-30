/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.util.Timer;

/**
 * Class that represents the iteraction between a Player and a Connector
 * ConnectorIteraction atributs:
 *      player: player that perform the iteraction
 *      lastIteraction: time of the last iteraction performed
 * @author Tiago Lopes, Rafael
 */
public class ConnectorIteraction {

    private Player player;
    private Timer lastIteraction;
    
    
    /**
     * Returns the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Assigns the player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Returns the lastIteraction
     * @return Timer
     */
    public Timer getLastIteraction() {
        return lastIteraction;
    }
    
    /**
     * Assigns the lastIteraction
     * @param lastIteraction
     */
    public void setLastIteraction(Timer lastIteraction) {
        this.lastIteraction = lastIteraction;
    }
    
    /**
     *  Returns all informtation of the ConnectorIteraction in a unique String 
     * @return String
     */
    @Override
    public String toString() {
        return "ConnectorIteraction{" + "player=" + player + ", lastIteraction=" + lastIteraction + '}';
    }

}
