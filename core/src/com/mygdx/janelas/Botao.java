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
    BotaoAcao acao;
    private boolean clicado;
    public Botao(String url,int x ,int y,BotaoAcao a){
        this.imagem =  new Image(new Texture(url));
        this.imagem.setPosition(x, y);
        this.acao = a;
    };
    public void checarClique(Actor entrada){
        if(!clicado){
            if(entrada.equals(imagem)){
                clicado = true;// marca que o botão foi clicado
                imagem.addAction(Actions.sequence(
                        Actions.color(Color.GREEN),
                        Actions.delay(0.2f),
                        Actions.color(Color.RED)
                ));
            }
        }
    }
    public void executar(){
        if(clicado && imagem.getActions().size==0){
            acao.realizar();
            clicado = false;
        }
    }
}
