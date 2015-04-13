package com.mygdx.jogo;

import com.badlogic.gdx.Game;
import com.mygdx.janelas.JanelaMenu;

public class Jogo extends Game  {
        private static Jogo instance;
        public static Jogo getInstance(){
            
            return instance;
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
