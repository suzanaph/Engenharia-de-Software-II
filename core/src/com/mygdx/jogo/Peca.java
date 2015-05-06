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
public class Peca {

    Boolean dama;
    Actor imagem;
    private Color original;

    Peca(int x, int y) {
        dama = false;
        this.imagem = new Image(new Texture("peca.png"));
        imagem.setPosition(x, y);
        
    }
    public void setColorOriginal(Color original){
        this.original = original;
    }
     public Color getColorOriginal(){
       return this.original;
    }
}
