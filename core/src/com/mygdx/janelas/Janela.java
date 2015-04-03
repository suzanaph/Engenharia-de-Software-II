/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

/**
 *
 * @author manue_000
 */
public abstract class Janela implements Screen {
    Camera camera; //Gerencia o posicionamento da camera
    Vector3 vetor; //Gerencia o mouse
    Stage estagio;//Gerencia os Actors
    Music musica;//Gerencia as músicas de fundo
    ArrayList<Sound>  sons; //Gerencia os sons do jogo
}
