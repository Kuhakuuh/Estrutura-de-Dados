/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.util.Timer;

/**
 *
 * @author Tiago Lopes
 */
public class ConnectorIteraction {

    private Player player;
    private Timer lastIteraction;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Timer getLastIteraction() {
        return lastIteraction;
    }

    public void setLastIteraction(Timer lastIteraction) {
        this.lastIteraction = lastIteraction;
    }

}
