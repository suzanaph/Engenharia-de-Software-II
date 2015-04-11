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

/**
 *
 * @author manue_000
 */
public class JanelaJogo extends Janela {
Jogo jogo;
    public JanelaJogo(Jogo jogo) {
        this.jogo=jogo;
        vetor = new Vector3();
        camera = new OrthographicCamera(800, 600);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        ScreenViewport view = new ScreenViewport(camera);
        estagio = new Stage(view);

        Actor imagemDeFundo = new Image(new Texture("dama.jpg"));
        imagemDeFundo.setSize(camera.viewportWidth, camera.viewportHeight);
        estagio.addActor(imagemDeFundo);
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
