/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue_000
 */
public abstract class Janela implements Screen {
   protected Camera camera; 
   protected Vector3 vetor;
   protected Stage estagio;
   protected List<Botao> botoes;
   protected List<Sound> sons;
   protected Actor fundo;
   public Janela(){
       vetor = new Vector3();
        camera = new OrthographicCamera(800, 600);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        ScreenViewport view = new ScreenViewport(camera);
        estagio = new Stage(view);
        sons = new ArrayList<Sound>();
        botoes = new ArrayList<Botao>();
   }
   
   @Override
    public void render(float f) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0,0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        estagio.act(Gdx.graphics.getDeltaTime());

        estagio.draw();
         cliques();
         processa();
    }
    // template method
    abstract void processa();

    private void cliques() {
        Actor a = null;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //Realiza o tratamento das alterações de dimenção da janela
            camera.unproject(vetor.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            //hit retorna dentre todos os objetos do estagio aquele que foi clicado
            a = estagio.hit(vetor.x, vetor.y, true);
            //Se o usuario clicar na botão inciar acao janela do Jogo.getInstance() é exibida    
            //qual botão possui a imagem que foi clicada
            
        }
         for (Botao botao : botoes) {       
            botao.checarClique(a);
         } 
    }
}
