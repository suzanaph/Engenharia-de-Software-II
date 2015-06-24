/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.jogo.Jogo;
import com.mygdx.jogo.Tabuleiro;

/**
 *
 * @author manue_000
 */
public class JanelaJogo extends Janela {
    Actor jogada;
    public JanelaJogo() { 
        super();
        fundo = new Image(new Texture("dama.jpg"));
        fundo.setSize(camera.viewportWidth, camera.viewportHeight);
        estagio.addActor(fundo);
       jogada = new Image(new Texture("peca.png"));
        jogada.setPosition(700, 150);
        jogada.setColor(Jogo.COLORJOGADOR1);
        Jogo.getInstance().getTabuleiro().reiniciarTabuleiro();
        Jogo.getInstance().getTabuleiro().adicionaArea(estagio);
        botoes.add(new Botao("iniciar.png", 650, 50,new BAMudarTela(BAMudarTela.MENU)));
        estagio.addActor(botoes.get(0).imagem);
        estagio.addActor(jogada);
        if(Jogo.getInstance().getMusica()!=null)
            Jogo.getInstance().getMusica().stop();
        Jogo.getInstance().setMusica("audios/jogo.ogg");
        Jogo.getInstance().getMusica().play();
        Jogo.getInstance().getMusica().setLooping(true);
       
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
        if(Jogo.getInstance().getTabuleiro().getEstado().getQtdPecaJ1() +
        		Jogo.getInstance().getTabuleiro().getEstado().getQtdDamaJ1()==0 ||
              Jogo.getInstance().getTabuleiro().getEstado().getQtdPecaJ2()
              +Jogo.getInstance().getTabuleiro().getEstado().getQtdDamaJ2()==0  ){
            Jogo.getInstance().setScreen(new JanelaMenu());
        }
       if(Jogo.getInstance().getJogador1().isTurno()){
            if(clicado != null && Jogo.getInstance().getJogador1().update(Jogo.getInstance().getTabuleiro().getCasa(clicado),estagio)){
                Jogo.getInstance().getJogador1().setTurno(false);
                 Jogo.getInstance().getJogador1().setQtdJogadas(0);
                Jogo.getInstance().getJogador2().setTurno(true);
                jogada.setColor(Jogo.COLORJOGADOR2);
            }
       }else if(Jogo.getInstance().getJogador2().isTurno()){
            if(clicado != null && Jogo.getInstance().getJogador2().update(Jogo.getInstance().getTabuleiro().getCasa(clicado),estagio)){
                 Jogo.getInstance().getJogador2().setTurno(false);
                 Jogo.getInstance().getJogador2().setQtdJogadas(0);
                 Jogo.getInstance().getJogador1().setTurno(true);
                 jogada.setColor(Jogo.COLORJOGADOR1);
            }
       }
    }

}
