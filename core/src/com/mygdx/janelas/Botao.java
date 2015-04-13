/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author fabio
 */
public class Botao {
    Actor imagem;
    BotaoAcao a;
    private boolean clicado;
    public Botao(String url,int x ,int y,BotaoAcao a){
        this.imagem =  new Image(new Texture(url));
        this.imagem.setPosition(x, y);
        this.a = a;
    };
    public void checarClick(Actor entrada){
        if(entrada.equals(imagem)){
            clicado = true;
            imagem.addAction(Actions.sequence(
                    Actions.scaleBy(0.2f, 0.2f, 0.5f),
                    Actions.color(Color.GREEN),
                    Actions.scaleBy(-0.2f, -0.2f, 0.5f),
                    Actions.color(Color.RED)
            ));
        }
    }
}
