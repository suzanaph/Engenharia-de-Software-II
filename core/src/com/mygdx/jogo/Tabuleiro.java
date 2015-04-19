/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue_000
 */
public class Tabuleiro {
    private static Tabuleiro instancia;
    int largura=8;
    int altura=8;
    int posX;
    int posY;
    Area[][] posicoes;
    private Tabuleiro(){
        posicoes = new Area[altura][largura];
        for(int lin=0;lin<altura;lin++)
            for(int col=0;col<largura;col++)
               posicoes[lin][col] = new Area(posX + Area.LARGURA* col , posY + Area.ALTURA*lin);
    }
    public static Tabuleiro retornaInstancia(){
        if(instancia==null)
            instancia = new Tabuleiro();
        return instancia;
    }
    
}
