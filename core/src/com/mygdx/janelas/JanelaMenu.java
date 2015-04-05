/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.jogo.Jogo;

/**
 *
 * @author manue_000
 */
public class JanelaMenu extends Janela {

    private Actor iniciar;
    private Jogo jogo;

    public JanelaMenu(Jogo jogo) {
        this.jogo = jogo;
        vetor = new Vector3();
        camera = new OrthographicCamera(800, 600);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        ScreenViewport view = new ScreenViewport(camera);
        estagio = new Stage(view);

        iniciar = new Image(new Texture("brinquedo.png"));
       
//Botão para iniciar a partida.
//   private Actor pontuacao= new Image(new Texture (""));; //Botão para ver a pontuação
//   private Actor regras= new Image(new Texture (""));; //Botão para ver as regras
//

        iniciar.setPosition(0, 0);
        estagio.addActor(iniciar);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float f) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        estagio.act(Gdx.graphics.getDeltaTime());

        estagio.draw();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //Realiza o tratamento das alterações de dimenção
            camera.unproject(vetor.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            //hit retorna dentre todos os objetos do estagio aquele que foi clicado
             Actor a = estagio.hit(vetor.x, vetor.y, true);
            //Se o usuario clicar na botão inciar a janela do jogo é exibida    
            if (a.equals(iniciar)) {

                jogo.setScreen(new JanelaJogo(jogo));

            }

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
