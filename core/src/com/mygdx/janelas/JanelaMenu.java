/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.jogo.Jogo;
import java.util.ArrayList;

/**
 *
 * @author manue_000
 */


public class JanelaMenu extends Janela {

    
    

    public JanelaMenu() {
         super();
         botoes = new ArrayList<Botao>();
        //Botão para iniciar acao partida.
        botoes.add(new Botao("iniciar.png", 250, 300,new BAMudarTela(BAMudarTela.JOGO)));
        //Botão para ver acao pontuação
        botoes.add(new Botao("pontuacao.png", 250, 100,new BAMudarTela(BAMudarTela.PONTUACAO)));
        //Botão para ver as regras
        botoes.add(new Botao("regras.png", 350, 200,new BAAbrirArquivo("regras")));
        //Botão para ver as configurações
        botoes.add(new Botao("configuracoes.png", 450, 300,new BAMudarTela(BAMudarTela.CONFIGURACOES)));
        //Botão para sair do jogo
        botoes.add(new Botao("sair.png", 450, 100,new BASair("jogo")));
        
        fundo = new Image(new Texture("fundo.png"));
        fundo.addAction(Actions.sequence(Actions.fadeOut(0),Actions.delay(1),Actions.fadeIn(1)));
        estagio.addActor(fundo);
        for (Botao botao : botoes) {
            botao.imagem.addAction(Actions.sequence(Actions.fadeOut(0),Actions.delay(2),Actions.fadeIn(1)));
            estagio.addActor(botao.imagem);
        }
         Jogo.getInstance().setMusica("audios/menu.ogg");
         Jogo.getInstance().getMusica().play();
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
