/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import IA.MinMax;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * classe que usa a IA para movimentar as peças
 * 
 * @author fabio
 */
public class Maquina extends Jogador {

	@Override
	public boolean update(Casa entrada, Stage estagio) {

		if (getCaminhoEscolhido() == null && getSelAreaPeca() == null) {

			MinMax m = new MinMax(getId());
			setCaminhoEscolhido(m.MinMaxDecision( Jogo.getInstance().getTabuleiro().getEstado()));

			setSelAreaPeca(getCaminhoEscolhido().get(0).atual);
			getCaminhoEscolhido().remove(0);
		} else {
			return moverPeca(estagio);
		}

		return false;
	}

}
