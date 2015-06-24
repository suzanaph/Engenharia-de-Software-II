package IA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.mygdx.jogo.Estado;
import com.mygdx.jogo.Jogo;
import com.mygdx.jogo.MovimentoEstado;

import com.mygdx.jogo.Tabuleiro;

public class MinMax {
	// Profundida da arvore de busca
	private int nivelMaximo = 0;
	// Armazena o melhor caminho
	private List<List<MovimentoEstado>> caminho;
	private List<Integer> valores;
	int jogadorInicial;
	public MinMax(int jogador){
		jogadorInicial = jogador;
	}

	// Metodo principal
	/**
	 * Recebe o estado atual do tabuleiro.
	 * 
	 * @param raiz
	 *            Estado atual do tabuleiro.
	 * @return A melhor jogada que foi encontrada pelo MinMax
	 */

	public List<MovimentoEstado> MinMaxDecision(Estado raiz) {

		// o valor da melhor escolha até agora (maior valor) ao longo do caminho
		// de MAX
		int alfa = Integer.MIN_VALUE;
		// o valor da melhor escolha até agora (menor valor) ao longo do caminho
		// de MIN
		int beta = Integer.MAX_VALUE;
		// o jogador que esta na vez
	
	
		if(jogadorInicial==1){
			nivelMaximo = 4;
		}else{
			nivelMaximo = 6;
		}
		caminho = new ArrayList<List<MovimentoEstado>>();
		valores = new ArrayList<Integer>();
		int v = MaxValue(raiz, alfa, beta, 0,jogadorInicial);

		// Busca na arvore o caminho com o custo V (Caso 2 ou mais caminhos
		// tiverem o melhor custo, um deles é escolhido aleatoriamente)
		return verificaCaminho(caminho, v);

	}

	private List<MovimentoEstado> verificaCaminho(
			List<List<MovimentoEstado>> caminhos, int v) {
		List<MovimentoEstado> maiorNumeroCapturas = null;
		//A lista "caminhos" possui todos os movimentos, o maior numero de capturas é a maior lista
		int maiorNumeroDeCapturas = -1;
		Random r = new Random();

		for (int i=0;i<caminhos.size();i++) {
			List<MovimentoEstado> list = caminhos.get(i);
			//Se a lista nao esta vazia e o valor for igual ao melhor valor encontrado no MinMax
			if (!list.isEmpty() && valores.get(i) == v) {

				//Se for melhor caminho encontrado anteriormente com relacao ao numero de capturas
				if (list.size() > maiorNumeroDeCapturas) {
					maiorNumeroCapturas = list;
					maiorNumeroDeCapturas = list.size();
					//Se encontrar um caminho com o numero de capturas igual ao melhor caminho encontrado anteriormente, é escolhido um caminho aleatoriamente 
				} else if (list.size() == maiorNumeroDeCapturas && r.nextInt(100) > 29) {//70% de escolher outra
					maiorNumeroCapturas = list;
					maiorNumeroDeCapturas = list.size();
				}
//				if(maiorNumeroCapturas==null || r.nextInt(100)>29)
//				maiorNumeroCapturas = list;
			}
		}
	
		return maiorNumeroCapturas;
	}

	public int MaxValue(Estado noAtual, int alfa, int beta, int nivel,
			int jogadorAtual) {
		//Testa se chegou a profundidade maxima
		if (terminalTeste(nivel) || (noAtual.getQtdPecaJ1()+noAtual.getQtdDamaJ1())==0 ||
				(noAtual.getQtdPecaJ2()+noAtual.getQtdDamaJ2())==0){
			//Retorna o custo em relação ao jogador da vez
			if (jogadorInicial == 1) {
				return noAtual.saldoJ1();
			} else if (jogadorInicial == 2) {
				return noAtual.saldoJ2();
			}
		}
		int v = Integer.MIN_VALUE;
		int[][] matriz = noAtual.getMatriz();
		boolean possuiJogada = false;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				//Para evitar que um jogador jogue com as peças do outro
				if ((jogadorAtual == 1 && (matriz[i][j] == Estado.PECAJOGADOR1 || matriz[i][j] == Estado.DAMAJOGADOR1))
						|| (jogadorAtual == 2 && (matriz[i][j] == Estado.PECAJOGADOR2 || matriz[i][j] == Estado.DAMAJOGADOR2))) {
					//Pega os caminhos disponiveis
					
					List<List<MovimentoEstado>> jogadas = Jogo.getInstance()
							.getTabuleiro().caminhosDisponiveis(i, j, false,noAtual);

					//Loop das jogadas
					for (List<MovimentoEstado> list : jogadas) {
						//Testa se uma jogada nao é vazia
						if (!list.isEmpty()) {

							possuiJogada = true;
							//Pega o maior valor entre os valores de Min
							int min = MinValue(list.get(list.size() - 1).getT(), alfa,
									beta, nivel + 1,jogadorAtual == 1?2:1);
							
							v = Math.max(v,min);
							//Se for o primeiro nivel adiciona a "caminho" para evitar calcular de novo
							if (nivel == 0) {
							
								caminho.add(list);
								valores.add(min);

							}
							//Se v for maior que o melhor valor retornado pelo MIN
							if (v >= beta) {
								return v;

							}
							alfa = Math.max(alfa, v);
						}
					}

				}
			}
		}

		if(!possuiJogada){
			if (jogadorInicial == 1) {
				return noAtual.saldoJ1();
			} else if (jogadorInicial == 2) {
				return noAtual.saldoJ2();
			}
		}

		return v;

	}




	public int MinValue(Estado raiz, int alfa, int beta, int nivel,
		int jogadorAtual) {
		//Testa se chegou na profundidade maxima
		if (terminalTeste(nivel) || (raiz.getQtdPecaJ1()+raiz.getQtdDamaJ1())==0 ||
				(raiz.getQtdPecaJ2()+raiz.getQtdDamaJ2())==0) {
			//Retorna o custo em relação ao jogador da vez
			
			if (jogadorInicial == 1) {
				return raiz.saldoJ1();
			} else if (jogadorInicial == 2) {
				return raiz.saldoJ2();
			}
		}
		int v = Integer.MAX_VALUE;
		int[][] matriz = raiz.getMatriz();
		boolean possuiJogada = false;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				//Para evitar que um jogador jogue com as peças do outro
				if ((jogadorAtual == 1 && (matriz[i][j] == Estado.PECAJOGADOR1 || matriz[i][j] == Estado.DAMAJOGADOR1))
						|| (jogadorAtual  == 2 && (matriz[i][j] == Estado.PECAJOGADOR2 || matriz[i][j] == Estado.DAMAJOGADOR2))) {
					//Pega os caminhos disponiveis
					List<List<MovimentoEstado>> jogadas = Jogo.getInstance()
							.getTabuleiro().caminhosDisponiveis(i, j, false,raiz);

					//Loop das jogadas
					for (List<MovimentoEstado> list : jogadas) {
						//Testa se uma jogada nao é vazia
						if (!list.isEmpty()) {

							possuiJogada = true;
							int max =MaxValue(list.get(list.size() - 1).getT(), alfa,
									beta, nivel + 1, jogadorAtual == 1?2:1);
							//Pega o menor valor entre os valores de MAX
							v = Math.min(v,max);

							//Se v for menor que o melhor valor retornado pelo MAX
							if (v <= alfa) {
								return v;
							}
							beta = Math.min(beta, v);
						}
					}
				}
			}
		}

		if(!possuiJogada){//não teve jogadas
			if (jogadorInicial == 1) {
				return raiz.saldoJ1();
			} else if (jogadorInicial == 2) {
				return raiz.saldoJ2();
			}
		}

		return v;
	}

	private boolean terminalTeste(int nivel) {
		// TODO Auto-generated method stub
		return nivel == nivelMaximo;
	}

}
