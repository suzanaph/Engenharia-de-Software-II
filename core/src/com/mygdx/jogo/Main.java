package com.mygdx.jogo;

import Exemplos.JanelaExemplo;
import com.badlogic.gdx.Game;

public class Main extends Game  {
    
        
	
	@Override
	public void create () {
		setScreen(new JanelaExemplo());
	}

	@Override
	public void render () {
		super.render();
	}
}
