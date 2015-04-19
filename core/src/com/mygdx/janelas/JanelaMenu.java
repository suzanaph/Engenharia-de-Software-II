/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue_000
 */


public class JanelaMenu extends Janela {

    private List<Botao> botoes;
    Actor fundo;

    public JanelaMenu() {
         super();
         botoes = new ArrayList<Botao>();
        //Botão para iniciar acao partida.
        botoes.add(new Botao("iniciar.png", 10, 50,new BotaoAcaoMudarTela("jogo")));
        //Botão para ver acao pontuação
        botoes.add(new Botao("pontuacao.png", 140, 50,new BotaoAcaoMudarTela("jogo")));
        //Botão para ver as regras
        botoes.add(new Botao("regras.png", 270, 50,new BotaoAcaoMudarTela("jogo")));
        //Botão para ver as configurações
        botoes.add(new Botao("configuracoes.png", 400, 50,new BotaoAcaoMudarTela("jogo")));
        //Botão para sair do jogo
        botoes.add(new Botao("sair.png", 530, 50,new BotaoAcaoMudarTela("jogo")));
        
        fundo = new Image(new Texture("fundo.png"));
        fundo.addAction(Actions.sequence(Actions.fadeOut(0),Actions.delay(1),Actions.fadeIn(1)));
        estagio.addActor(fundo);
        for (Botao botao : botoes) {
            botao.imagem.addAction(Actions.sequence(Actions.fadeOut(0),Actions.delay(2),Actions.fadeIn(1)));
            estagio.addActor(botao.imagem);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float f) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        estagio.act(Gdx.graphics.getDeltaTime());

        estagio.draw();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //Realiza o tratamento das alterações de dimenção da janela
            camera.unproject(vetor.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            //hit retorna dentre todos os objetos do estagio aquele que foi clicado
            Actor a = estagio.hit(vetor.x, vetor.y, true);
            //Se o usuario clicar na botão inciar acao janela do Jogo.getInstance() é exibida    
            //qual botão possui a imagem que foi clicada
            
                for (Botao botao : botoes) {
                    if (a != null) {
                        botao.checarClique(a);
                    }
                    botao.executar();
                }

             //Se o usuario clicar na botão inciar acao janela do Jogo é exibida 

//                if (Desktop.isDesktopSupported()) {
//                    try {
//                        Desktop.getDesktop().browse(new URI("http://www.xadrezregional.com.br/regrasdm.html"));
//                    } catch (IOException ex) {
//                        Logger.getLogger(JanelaMenu.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (URISyntaxException ex) {
//                        Logger.getLogger(JanelaMenu.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
        }

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

}
