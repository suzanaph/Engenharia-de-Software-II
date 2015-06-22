package IA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.mygdx.jogo.Estado;
import com.mygdx.jogo.Jogo;
import com.mygdx.jogo.MovimentoEstado;

public class MinMax {
	// Profundida da arvore de busca
	private int nivelMaximo = 7;
	// Armazena o melhor caminho
	private List<List<MovimentoEstado>> caminho;

	// Metodo principal
	/**
	 * Recebe o estado atual do tabuleiro.
	 * 
	 * @param raiz
	 *            Estado atual do tabuleiro.
	 * @return A melhor jogada que foi encontrada pelo MinMax
	 */
	public List<MovimentoEstado> MinMaxDecision(MovimentoEstado raiz) {
		// o valor da melhor escolha até agora (maior valor) ao longo do caminho
		// de MAX
		int alfa = Integer.MIN_VALUE;
		// o valor da melhor escolha até agora (menor valor) ao longo do caminho
		// de MIN
		int beta = Integer.MAX_VALUE;
		// o jogador que esta na vez
		int jogador = raiz.getJogador();
		caminho = new ArrayList<List<MovimentoEstado>>();

		int v = MaxValue(raiz, alfa, beta, 0, raiz.getJogador());
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
		for (List<MovimentoEstado> list : caminhos) {
			//Se a lista nao esta vazia e o valor for igual ao melhor valor encontrado no MinMax
			if (!list.isEmpty() && list.get(0).getValor() == v) {
				//Se for melhor caminho encontrado anteriormente com relacao ao numero de capturas
				if (list.size() > maiorNumeroDeCapturas) {
					maiorNumeroCapturas = list;
					maiorNumeroDeCapturas = list.size();
					//Se encontrar um caminho com o numero de capturas igual ao melhor caminho encontrado anteriormente, é escolhido um caminho aleatoriamente 
				} else if (list.size() == maiorNumeroDeCapturas && r.nextInt(2) == 1) {
					maiorNumeroCapturas = list;
					maiorNumeroDeCapturas = list.size();
				}
			}
		}

		return maiorNumeroCapturas;
	}

	public int MaxValue(MovimentoEstado noAtual, int alfa, int beta, int nivel,
			int jogadorInicial) {
		//Testa se chegou a profundidade maxima
		if (terminalTeste(nivel)) {
			//Retorna o custo em relação ao jogador da vez
			if (jogadorInicial == 1) {
				return noAtual.getCustoJ1();
			} else if (jogadorInicial == 2) {
				return noAtual.getCustoJ2();
			}
		}
		int v = Integer.MIN_VALUE;
		int[][] matriz = noAtual.getT().getMatriz();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				//Para evitar que um jogador jogue com as peças do outro
				if ((noAtual.getJogador() == 1 && (matriz[i][j] == Estado.PECAJOGADOR1 || matriz[i][j] == Estado.DAMAJOGADOR1))
						|| (noAtual.getJogador() == 2 && (matriz[i][j] == Estado.PECAJOGADOR2 || matriz[i][j] == Estado.DAMAJOGADOR2))) {
					//Pega os caminhos disponiveis
					List<List<MovimentoEstado>> jogadas = Jogo.getInstance()
							.getTabuleiro().caminhosDisponiveis(i, j, false);
					//Loop das jogadas
					for (List<MovimentoEstado> list : jogadas) {
						//Testa se uma jogada nao é vazia
						if (!list.isEmpty()) {
							//Pega o maior valor entre os valores de Min
							v = Math.max(
									v,
									MinValue(list.get(list.size() - 1), alfa,
											beta, nivel + 1, jogadorInicial));
							//Se for o primeiro nivel adiciona a "caminho" para evitar calcular de novo
							if (nivel == 0) {
								setarValorLista(list, v);
								caminho.add(list);
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
		return v;

	}

	private void setarValorLista(List<MovimentoEstado> list, int v) {
		for (MovimentoEstado movimentoEstado : list) {
			movimentoEstado.setValor(v);
		}

	}

	public int MinValue(MovimentoEstado raiz, int alfa, int beta, int nivel,
			int jogadorInicial) {
		//Testa se chegou na profundidade maxima
		if (terminalTeste(nivel)) {
			//Retorna o custo em relação ao jogador da vez
			if (jogadorInicial == 1) {
				return raiz.getCustoJ1();
			} else if (jogadorInicial == 2) {
				return raiz.getCustoJ2();
			}
		}
		int v = Integer.MAX_VALUE;
		int[][] matriz = raiz.getT().getMatriz();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				//Para evitar que um jogador jogue com as peças do outro
				if ((raiz.getJogador() == 1 && (matriz[i][j] == Estado.PECAJOGADOR1 || matriz[i][j] == Estado.DAMAJOGADOR1))
						|| (raiz.getJogador() == 2 && (matriz[i][j] == Estado.PECAJOGADOR2 || matriz[i][j] == Estado.DAMAJOGADOR2))) {
					//Pega os caminhos disponiveis
					List<List<MovimentoEstado>> jogadas = Jogo.getInstance()
							.getTabuleiro().caminhosDisponiveis(i, j, false);
					//Loop das jogadas
					for (List<MovimentoEstado> list : jogadas) {
						//Testa se uma jogada nao é vazia
						if (!list.isEmpty()) {
							//Pega o menor valor entre os valores de MAX
							v = Math.min(
									v,
									MaxValue(list.get(list.size() - 1), alfa,
											beta, nivel + 1, jogadorInicial));
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
		return v;
	}

	private boolean terminalTeste(int nivel) {
		// TODO Auto-generated method stub
		return nivel == nivelMaximo;
	}

}
