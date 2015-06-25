/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import javax.swing.JOptionPane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.dado.Usuario;
import com.mygdx.jogo.Estado;
import com.mygdx.jogo.Jogador;
import com.mygdx.jogo.Jogo;
import com.mygdx.jogo.Tabuleiro;

/**
 *
 * @author manue_000
 */
public class JanelaJogo extends Janela {
	Actor jogada;
	float tempoJogada = 0;
	int contadorTurnos =0;
	Estado jogadaAnterior;
	public JanelaJogo() {
		super();
		Jogo jogo = Jogo.getInstance();
		Tabuleiro tabuleiro = jogo.getTabuleiro();
		fundo = new Image(new Texture("dama.jpg"));
		fundo.setSize(camera.viewportWidth, camera.viewportHeight);
		estagio.addActor(fundo);
		jogada = new Image(new Texture("peca.png"));
		jogada.setPosition(700, 150);
		jogada.setColor(Jogo.COLORJOGADOR1);
		tabuleiro.reiniciarTabuleiro();
		tabuleiro.adicionaArea(estagio);
		botoes.add(new Botao("iniciar.png", 650, 50, new BAMudarTela(
				BAMudarTela.MENU)));
		estagio.addActor(botoes.get(0).imagem);
		estagio.addActor(jogada);
		if (jogo.getMusica() != null)
			jogo.getMusica().stop();
		jogo.setMusica("audios/jogo.ogg");
		jogo.getMusica().play();
		jogo.getMusica().setLooping(true);
		jogo.setFont("fontes/score");
		jogo.getFont().setScale(2);

	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int i, int i1) {
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

	@Override
	void processa() {
		// desenha o score
		Jogador jogador1 =Jogo.getInstance().getJogador1();
		Jogador jogador2 =Jogo.getInstance().getJogador2();
		estagio.getBatch().begin();
		if (Jogo.getInstance().getFont() != null
				&& Jogo.getInstance().getUsuario() != null)
			Jogo.getInstance()
					.getFont()
					.draw(estagio.getBatch(),
							"" + Jogo.getInstance().getUsuario().getPontuacao(),
							650, 500);
		estagio.getBatch().end();
		Estado estado = Jogo.getInstance().getTabuleiro().getEstado();
		// término de jogo
		if (estado.getQtdPecaJ1()
				+ estado.getQtdDamaJ1() == 0) {
			JOptionPane.showMessageDialog(null,
					"Que pena você perdeu,tente denovo! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		} else if (estado.getQtdPecaJ2()
				+ estado.getQtdDamaJ2() == 0) {
			JOptionPane.showMessageDialog(null, "Voce venceu! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		} else if (estado.getQtdPecaJ2() == 0
				&& estado.getQtdPecaJ1() == 0
				&& estado.getQtdDamaJ2() == 2
				&& estado.getQtdDamaJ1() == 2) {
			JOptionPane.showMessageDialog(null, "Empatou! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		}else if(estado.getQtdPecaJ2() == 0
				&& estado.getQtdPecaJ1() == 0
				&& ((estado.getQtdDamaJ2() == 2
				&& estado.getQtdDamaJ1() == 1))||
				((estado.getQtdDamaJ2() == 1
				&& estado.getQtdDamaJ1() == 2))){
			JOptionPane.showMessageDialog(null, "Empatou! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		}else if((estado.getQtdPecaJ2() == 0
				&& estado.getQtdPecaJ1() == 1
				&& estado.getQtdDamaJ2() == 2
				&& estado.getQtdDamaJ1() == 1)||
				(estado.getQtdPecaJ2() == 1
				&& estado.getQtdPecaJ1() == 0
				&& estado.getQtdDamaJ2() == 1
				&& estado.getQtdDamaJ1() == 2)){
			JOptionPane.showMessageDialog(null, "Empatou! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		}else if((estado.getQtdPecaJ2() == 0
				&& estado.getQtdPecaJ1() == 1
				&& estado.getQtdDamaJ2() == 1
				&& estado.getQtdDamaJ1() == 1)||
				(estado.getQtdPecaJ2() == 1
				&& estado.getQtdPecaJ1() == 0
				&& estado.getQtdDamaJ2() == 1
				&& estado.getQtdDamaJ1() == 1)){
			if(contadorTurnos==5){
				JOptionPane.showMessageDialog(null, "Empatou! ");
				Jogo.getInstance().setScreen(new JanelaMenu());
			}
		}else if(contadorTurnos==20){
			JOptionPane.showMessageDialog(null, "Empatou! ");
			Jogo.getInstance().setScreen(new JanelaMenu());
		}
		if (jogador1.isTurno()) {
			if (clicado != null
					&& Jogo.getInstance()
							.getJogador1()
							.update(Jogo.getInstance().getTabuleiro()
									.getCasa(clicado), estagio)) {

				if (Jogo.tempo - tempoJogada <= 10) {
					Jogo.getInstance()
							.getUsuario()
							.setPontuacao(
									Jogo.getInstance().getUsuario()
											.getPontuacao() + 2);
				}
				Usuario usuario = Jogo.getInstance().getUsuario();
				switch (jogador1.getQtdJogadas()) {
				case 1:
					usuario.setPontuacao(usuario.getPontuacao() + 10);
					break;
				case 2:
					usuario.setPontuacao(usuario.getPontuacao() + 25);
					break;
				case 3:
					usuario.setPontuacao(usuario.getPontuacao() + 50);
					break;
				default:
					usuario.setPontuacao(usuario.getPontuacao() + 100);
					break;
				}

				jogador1.setTurno(false);
				jogador1.setQtdJogadas(0);
				jogador2.setTurno(true);
				jogada.setColor(Jogo.COLORJOGADOR2);
				// tempoJogada = Jogo.tempo+10;
				
				contaTurno();
				
			}
		} else if (jogador2.isTurno()) {
			if (clicado != null
					&& Jogo.getInstance()
							.getJogador2()
							.update(Jogo.getInstance().getTabuleiro()
									.getCasa(clicado), estagio)) {

				// Jogo.getInstance().getUsuario().setPontuacao(jogador2.getQtdJogadas()*10);
				jogador2.setQtdJogadas(0);

				jogador2.setTurno(false);
				jogador2.setQtdJogadas(0);
				jogador1.setTurno(true);
				jogada.setColor(Jogo.COLORJOGADOR1);
				tempoJogada = Jogo.tempo + 10;
				contaTurno();
			}
		}
	}

	private void contaTurno() {
		Estado atual = Tabuleiro.retornaInstancia().getEstado();
		if(jogadaAnterior!=null && jogadaAnterior.getQtdDamaJ1()==atual.getQtdDamaJ1()
				&& jogadaAnterior.getQtdDamaJ2()==atual.getQtdDamaJ2()&&
				jogadaAnterior.getQtdPecaJ1()==atual.getQtdPecaJ1()&&
				jogadaAnterior.getQtdPecaJ2()==atual.getQtdPecaJ2()){
			contadorTurnos++;
		}else{
			contadorTurnos=0;
		}
		jogadaAnterior = atual;
	}

}
