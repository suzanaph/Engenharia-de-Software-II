/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.jogo.Jogo;
import com.mygdx.jogo.Tabuleiro;

/**
 *
 * @author manue_000
 */
public class JanelaJogo extends Janela {
    
    public JanelaJogo() { 
        super();
        Actor imagemDeFundo = new Image(new Texture("dama.jpg"));
        imagemDeFundo.setSize(camera.viewportWidth, camera.viewportHeight);
        estagio.addActor(imagemDeFundo);
        Tabuleiro tabuleiro= Tabuleiro.retornaInstancia();
        tabuleiro.adcionaArea(estagio);
        botoes.add(new Botao("iniciar.png", 700, 50,new BAMudarTela(BAMudarTela.MENU)));
        estagio.addActor(botoes.get(0).imagem);
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    @Override
    void processa() {
    }

}
