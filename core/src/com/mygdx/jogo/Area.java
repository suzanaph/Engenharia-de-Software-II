/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author manue_000
 */
public class Area {
 
 int[] posicao= new int[2]; // Posição x ,y 
 Actor imagem; //Imagem do quadrado do tabuleiro.
 Actor peca;//Peça

    public Area(Actor imagem, Actor peca) {
        this.imagem = imagem;
        this.peca = peca;
    }
 
}
