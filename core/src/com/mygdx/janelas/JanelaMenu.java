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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author manue_000
 */
public class JanelaMenu extends Janela {

    private Actor iniciar;

    public JanelaMenu() {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
