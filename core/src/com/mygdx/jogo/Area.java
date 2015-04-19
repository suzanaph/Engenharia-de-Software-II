/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author manue_000
 */
public class Area {

    int[][] posicaon = new int[1][2];
    Peca peca;
    Actor imagem;
    static int LARGURA = 75;
    static int ALTURA = 75;
    String diretorio = "area.png";

    Area(int x, int y, Color cor) {
        this.imagem = new Image(new Texture(diretorio));
        imagem.setPosition(x, y);
        this.imagem.setColor(cor);
    }
}
