package com.mygdx.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.janelas.JanelaMenu;

public class Jogo extends Game  {
        private static Jogo instance;
        private   Music musica;
        private   int dificuldade;
        private  boolean somAtivo;
        public static Jogo getInstance(){
            
            return instance;
        }

    /**
     * @return the dificuldade
     */
    public  int getDificuldade() {
        return dificuldade;
    }

    /**
     * @param aDificuldade the dificuldade to set
     */
    public  void setDificuldade(int aDificuldade) {
        dificuldade = aDificuldade;
    }
        public  Music getMusica(){
            return musica;
        }
        public  void setMusica(String url){
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

    /**
     * @return the somAtivo
     */
    public boolean isSomAtivo() {
        return somAtivo;
    }

    /**
     * @param somAtivo the somAtivo to set
     */
    public void setSomAtivo(boolean somAtivo) {
        this.somAtivo = somAtivo;
    }
}
