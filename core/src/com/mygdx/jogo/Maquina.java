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
			MinMax m = new MinMax();
			setCaminhoEscolhido(m.MinMaxDecision(new MovimentoEstado(getId(), Jogo.getInstance().getTabuleiro().getEstado())));
			setSelAreaPeca(getCaminhoEscolhido().get(0).atual);
			getCaminhoEscolhido().remove(0);
		} else {
			return moverPeca(estagio);
		}

		return false;
	}
	// Plano para elabora��o da IA
	// nivel de profundidade da arvore
	// facil 8 niveis
	// m�dio 12 niveis
	// dificil 15 niveis
	// para todas as pe�as desse jogador pegar todos os caminhos possiveis com
	// m�todo de tabuleiro caminhosDisponiveis, pega primeiro elemento de todos
	// os caminhos e p�e como
	// anterior o estado atual como raiz desse ramo,ai aparti do ultimo elemento
	// de cada caminho usar o m�todo novamente
	// s� que para todas as pe�as do outro jogador e novamente o anterior vai
	// ser esses ultimos elementos dos caminhos anteriores usados
	// repetir isso numero de n niveis , ao final desse processo varificar os
	// saldos em rela��o ao jogador que esta utilizando o mim max
	// fazer com que a classe movimento estado tenha as variaveis alpha e beta e
	// guarde e o tipo mim ou max e qual valor da folha vai subir
}
