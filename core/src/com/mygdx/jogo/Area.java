/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author manue_000
 */
public class Area {
    int[][] posicaon=new int[1][2];
    Actor peca;
    Actor imagem;
    String diretorio="area.pgn";
    Area(int x,int y ,int posicao) {
      imagem.setPosition(x, y);
          this.imagem = new Image(new Texture(diretorio));
    }
}
