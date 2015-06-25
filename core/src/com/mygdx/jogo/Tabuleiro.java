/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue_000
 */
public class Tabuleiro {

    public final static int DSE = 1;//diagonal superior esquerda
    public final static int DSD = 2;//diagonal superior direita
    public final static int DIE = 3;//diagonal inferior esquerda
    public final static int DID = 4;//diagonal inferior direita

    private static Tabuleiro instancia;

    int largura = 8;// largura do tabuleiro em quantidade de casas
    int altura = 8;// altura do tabuleiro em quantidade de casas
    int posX;// variavel para ajuste das coordenadas x das casa e peças
    int posY;// variavel para ajuste das coordenadas y das casa e peças 
    private Estado estado;
    Casa[][] matrizCasas; //Matriz que controla as posições do tabuleiro

    /**
     * Método construtor de tabuleiro , privado de acordo com padrão de projeto
     * singleton.
     */
    private Tabuleiro() {
        reiniciarTabuleiro();
    }

    /**
     * Método utilizado para alocar as peças ao estado incial de um jogo de
     * damas, instanciando 12 peças do jogador1, 12 peças do jogador2 e 64 casas
     * ,colorindo tanto peças quanto as casas do tabuleiro.
     */
    public void reiniciarTabuleiro() {
        estado = new Estado();
        matrizCasas = new Casa[altura][largura];
        Color cor = Color.WHITE;
        for (int lin = 0; lin < altura; lin++) {

            for (int col = 0; col < largura; col++) {
                //Define uma cor para uma casa de acorda com sua posição na matriz
                if (par(lin) && par(col)) {
                    cor = Color.BLACK;
                } else if (!par(lin) && !par(col)) {
                    cor = Color.BLACK;
                } else {
                    cor = Color.WHITE;
                }
                //Configura a posição de cada casa de acordo com suas dimesões e ordem  
                matrizCasas[lin][col] = new Casa(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin, cor);
                //Salva as posições da matriz na casa.
                matrizCasas[lin][col].setPosicaoMatriz(lin, col);
                if (getEstado().matriz[lin][col] == Estado.PECAJOGADOR1) {
                    //Adiciona peças nas três primeiras linhas do tabuleiro.
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR1);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR1);//lol isso deve estar dentro de peca !!
                    Jogo.getInstance().getJogador1().getPecas().add(matrizCasas[lin][col].peca);
                } else if (getEstado().matriz[lin][col] == Estado.DAMAJOGADOR1) {
                    //Adiciona peças nas três primeiras linhas do tabuleiro.
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORDAMAJOGADOR1);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORDAMAJOGADOR1);//lol isso deve estar dentro de peca !!
                    matrizCasas[lin][col].peca.dama = true;
                    Jogo.getInstance().getJogador1().getPecas().add(matrizCasas[lin][col].peca);
                } else if (getEstado().matriz[lin][col] == Estado.PECAJOGADOR2) {//Adiciona peças nas três últimas linhas do tabuleiro.
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR2);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR2);
                    Jogo.getInstance().getJogador2().getPecas().add(matrizCasas[lin][col].peca);
                } else if (getEstado().matriz[lin][col] == Estado.DAMAJOGADOR2) {
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORDAMAJOGADOR2);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORDAMAJOGADOR2);
                    matrizCasas[lin][col].peca.dama = true;
                    Jogo.getInstance().getJogador2().getPecas().add(matrizCasas[lin][col].peca);
                }
            }
        }
    }

    /**
     * Método utilizado para descobrir a casa a qual um ator pertence , criado
     * pos o método do estagio que detecta colissão com o mouse ,retorna o actor
     * que foi clicado
     *
     * @param entrada é a casa selecionada pelo jogador que obrigatóriamente
     * deve possuir uma peça do mesmo.
     * @return retorna a casa pertencente a matriz de casas do tabuleiro,cujo
     * ator da casa ou da peça é igual a entrada.
     */
    public Casa getCasa(Actor entrada) {
        for (int i = 0; i < matrizCasas.length; i++) {
            for (int j = 0; j < matrizCasas[i].length; j++) {
                if (matrizCasas[i][j].imagem.equals(entrada) || (matrizCasas[i][j].peca != null && matrizCasas[i][j].peca.imagem.equals(entrada))) {
                    return matrizCasas[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Método utilizado para descobrir casas disponíveis para peca andar,
     * levando em conta as regras Lei da maioria e captura obrigatória.
     *
     * @param casa é a casa selecionada pelo jogador que obrigatóriamente deve
     * possuir uma peça do mesmo.
     * @param capturado responsável por limitar os tipos de movimento da peça a
     * capturas.
     * @return saida lista contendo os caminhos disponiveis, lista simplesmente
     * encadeadas contendo tuplas casa-estado, de melhor custo.
     */
    public List<List<MovimentoEstado>> caminhosDisponiveis(Casa casa, boolean capturado) {
        List<List<MovimentoEstado>> saida = new ArrayList<List<MovimentoEstado>>();
        int lin = casa.posicao[0];
        int col = casa.posicao[1];

        List<MovimentoEstado> estados = getEstado().movimentosPossiveis(lin, col, capturado, null);//new MovimentoEstado(matrizCasas[lin][col],estado)

        estados = getEstado().melhorCusto(lin, col, estados);
        for (MovimentoEstado est : estados) {
            saida.add(getEstado().ordenar(est));
        }

        return saida;
    }

    public List<List<MovimentoEstado>> caminhosDisponiveis(int lin, int col, boolean capturado, Estado t) {
        List<List<MovimentoEstado>> saida = new ArrayList<List<MovimentoEstado>>();

        List<MovimentoEstado> estados = t.movimentosPossiveis(lin, col, capturado, new MovimentoEstado(matrizCasas[lin][col], t));//new MovimentoEstado(matrizCasas[lin][col],estado)

        estados = t.melhorCusto(lin, col, estados);
        for (MovimentoEstado est : estados) {
            saida.add(t.ordenar(est));

        }

        return saida;
    }

    /**
     * Método utilizado para descobrir que numeiro inteiro é par,
     *
     * @param numero é o numero inteiro a ser verificado.
     * @return verdadeiro para numero par e falso para numero ímpar,
     */
    public boolean par(int numero) {
        return numero % 2 == 0;

    }

    /**
     * Método utilizado para retornar a instância unica de tabuleiro que jogo
     * possui.Padrão de projeto singleton.
     *
     * @return instancia ,unica instancia de tabuleiro.
     */
    public static Tabuleiro retornaInstancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    /**
     * Método utilizado para adicionar os atores(imagens) das instanciais das
     * peças e das casas, ao estagio da janela , que é responsavél por desenhar
     * e gerênciar esses atores.
     *
     * @param estagio estagio da janela que invocou o método.
     */
    public void adicionaArea(Stage estagio) {

        for (int i = 0; i < matrizCasas.length; i++) {
            for (int j = 0; j < matrizCasas.length; j++) {
                estagio.addActor(matrizCasas[i][j].imagem);
                if (matrizCasas[i][j].peca != null) {
                    estagio.addActor(matrizCasas[i][j].peca.imagem);
                }
            }
        }
        for (int i = 0; i < matrizCasas.length; i++) {
            for (int j = 0; j < matrizCasas.length; j++) {
                if (matrizCasas[i][j].peca != null) {
                    estagio.addActor(matrizCasas[i][j].peca.imagem);
                }
            }
        }

    }

    /**
     * Método utilizado para moficiar o estado do tabuleiro após a peça realizar
     * um movimento, também responsavel por remover peças que não são
     * refênciadas no novo estado.
     *
     * @param novo é o estado obtido através de um movimento.
     * @param estagio estagio utilizado para deletar a imagem da peça a ser
     * removida.
     */
    public void setEstado(MovimentoEstado novo, Stage estagio) {

        estado = novo.t;
        if (novo.eliminar != null) {
            estagio.getActors().removeValue(novo.eliminar.peca.imagem, true);
            novo.eliminar.peca = null;
            Jogo.getInstance().setSomCaptura("audios/captura.mp3");
            Jogo.getInstance().getSomCaptura().play();
        }
        promoverPecas();

    }

    public void promoverPecas() {
         Jogo.getInstance().setSomDama("audios/dama.wav");
        int fim = getEstado().matriz.length - 1;
        for (int i = 0; i < getEstado().matriz.length; i++) {
            if (getEstado().matriz[0][i] == Estado.DAMAJOGADOR2 && ! matrizCasas[0][i].peca.dama ) {
                matrizCasas[0][i].peca.imagem.setColor(Jogo.COLORDAMAJOGADOR2);
                matrizCasas[0][i].peca.setColorOriginal(Jogo.COLORDAMAJOGADOR2);
                matrizCasas[0][i].peca.dama = true;
               if(Jogo.getInstance().getJogador1().getPecas().contains(matrizCasas[0][i].peca)){
            	   Jogo.getInstance().getUsuario().setPontuacao(Jogo.getInstance().getUsuario().getPontuacao() + 50);
               }
                Jogo.getInstance().getSomDama().play();
            }
        }
        for (int i = 0; i < getEstado().matriz.length; i++) {
            if (getEstado().matriz[fim][i] == Estado.DAMAJOGADOR1 && ! matrizCasas[fim][i].peca.dama) {
                matrizCasas[fim][i].peca.imagem.setColor(Jogo.COLORDAMAJOGADOR1);
                matrizCasas[fim][i].peca.setColorOriginal(Jogo.COLORDAMAJOGADOR1);
                matrizCasas[fim][i].peca.dama = true;
                 Jogo.getInstance().getSomDama().play();
            }
        }
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

}
