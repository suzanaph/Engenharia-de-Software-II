package com.mygdx.jogo;

import com.badlogic.gdx.Game;
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
