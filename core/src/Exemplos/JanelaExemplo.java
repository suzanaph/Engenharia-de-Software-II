/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemplos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zideon
 */
public class JanelaExemplo implements Screen {

    Vector3 touchPoint;
    OrthographicCamera camera;
    Stage estagio;
    List<Actor> imagens;

    public JanelaExemplo() {
        camera = new OrthographicCamera(800, 600);
        camera.position.set(camera.viewportWidth / 2,camera.viewportHeight / 2, 0);
        ScreenViewport view = new ScreenViewport( camera); 
	estagio = new Stage(view);
        imagens = new ArrayList<Actor>();
        Actor q1 = new Image(new Texture("exemplo/quadrado.png"));
        q1.setPosition(100, 100);
        Actor q2 = new Image(new Texture("exemplo/quadrado.png"));
        q2.setPosition(100, 300);
        Actor p1 = new Image(new Texture("exemplo/peca.png"));
        p1.setPosition(120 , 120);
        p1.addAction(Actions.sequence(Actions.delay(2),Actions.moveBy(0, 200,2)));
        imagens.add(q1);
        imagens.add(q2);
        imagens.add(p1);
        
        for (Actor imagen : imagens) {
            estagio.addActor(imagen);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
                GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		estagio.act(Gdx.graphics.getDeltaTime());
		
		estagio.draw();    }

    @Override
    public void resize(int width, int height) {
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
