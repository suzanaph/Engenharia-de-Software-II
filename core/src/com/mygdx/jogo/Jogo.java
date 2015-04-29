package com.mygdx.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.janelas.JanelaMenu;

public class Jogo extends Game  {
        private static Jogo instance;
        private static Music musica;
        public static Jogo getInstance(){
            
            return instance;
        }
        public Music getMusica(){
            return musica;
        }
        public void setMusica(String url){
            musica = Gdx.audio.newMusic(Gdx.files.internal(url));
        }
        public Jogo(){}
        
	@Override
	public void create () {
                instance = this;
		setScreen(new JanelaMenu());
	}

	@Override
	public void render () {
		super.render();
	}
}
