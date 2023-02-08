package Player;

import java.time.LocalDateTime;


/**
 * Class that represents the iteraction between a Player and a Connector
 * ConnectorIteraction atributs: player: player that perform the iteraction
 * lastIteraction: time of the last iteraction performed
 *
 * @author Tiago Lopes, Rafael
 */
public class ConnectorIteraction {

    private String player;
    private LocalDateTime lastIteraction;

    /**
     * Returns the player name
     *
     * @return Player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Assigns the player name
     *
     * @param player
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Returns the lastIteraction
     *
     * @return Timer
     */
    public LocalDateTime getLastIteraction() {
        return lastIteraction;
    }

    /**
     * Assigns the lastIteraction
     *
     * @param lastIteraction
     */
    public void setLastIteraction(LocalDateTime lastIteraction) {
        this.lastIteraction = lastIteraction;
    }

    /**
     * Returns all informtation of the ConnectorIteraction in a unique String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ConnectorIteraction{" + "player=" + player + ", lastIteraction=" + lastIteraction + '}';
    }

}
