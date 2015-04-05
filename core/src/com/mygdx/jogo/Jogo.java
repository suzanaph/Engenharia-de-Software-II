package com.mygdx.jogo;

import Exemplos.JanelaExemplo;
import com.badlogic.gdx.Game;
import com.mygdx.janelas.JanelaJogo;
import com.mygdx.janelas.JanelaMenu;

public class Jogo extends Game  {
    
        
	
	@Override
	public void create () {
		setScreen(new JanelaMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
