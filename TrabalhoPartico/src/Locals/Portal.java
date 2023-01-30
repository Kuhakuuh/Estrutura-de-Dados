/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import Enumerations.Estado;
import Player.Player;

/**
 * Class that represents a Portal, who extends the class Local
 * Portal atributs:
 *      nome: name of the portal
 *      estado: state of the portal NEUTro or the name of one team
 *      jogador: player that had conquer the portal
 * @author Tiago Lopes, Rafael Dias
 */
public class Portal extends Local {

    private String nome;
    private Estado estado;
    private Player jogador;

    /**
     *  Return the state of the portal
     * @return Estado
     */
    public Estado getEstado() {
        return estado;
    }
    
    /**
     *  Assign the state of the portal
     * @param estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    /**
     *  Return the player
     * @return Player
     */
    public Player getJogador() {
        return jogador;
    }
    
    /**
     *  Assign the player
     * @param jogador
     */
    public void setJogador(Player jogador) {
        this.jogador = jogador;
    }
    
    /**
     *  Return the name of the portal
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     *  Assign the name of the portal
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *  Returns all informtation of the portal in a unique String 
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+ " Portal{" + "nome=" + nome + ", estado=" + estado + ", jogador=" + jogador + '}';
    }    
}
