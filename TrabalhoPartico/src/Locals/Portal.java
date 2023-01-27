/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locals;

import Enumerations.Estado;
import Player.Player;

/**
 *
 * @author Tiago Lopes
 */
public class Portal extends Local {

    private String nome;
    private Estado estado;
    private Player jogador;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Player getJogador() {
        return jogador;
    }

    public void setJogador(Player jogador) {
        this.jogador = jogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Portal{" + "nome=" + nome + ", estado=" + estado + ", jogador=" + jogador + '}';
    }

    
}
