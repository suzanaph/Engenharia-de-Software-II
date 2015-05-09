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
import java.util.Collection;
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
    int largura = 8;
    int altura = 8;
    int posX;
    int posY;
    Estado estado = new Estado();
    Casa[][] matrizCasas; //Matriz que controla as posições do tabuleiro

    private Tabuleiro() {
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
                if (estado.matriz[lin][col] == 1) {
                    if (lin >= 0 && lin <= 2) {//Adiciona peças nas três primeiras linhas do tabuleiro.
                        matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                        matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR1);
                        matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR1);//lol isso deve estar dentro de peca !!
                        Jogo.getInstance().getJogador1().getPecas().add(matrizCasas[lin][col].peca);
                    } else if (lin >= 5 && lin <= 7) {//Adiciona peças nas três últimas linhas do tabuleiro.
                        matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                        matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR2);
                        matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR2);
                        Jogo.getInstance().getJogador2().getPecas().add(matrizCasas[lin][col].peca);
                    }

                }
            }
        }

    }
  //Retorna dada uma imagem , retorna a casa correspondente.
    public Casa getArea(Actor entrada) {
        for (int i = 0; i < matrizCasas.length; i++) {
            for (int j = 0; j < matrizCasas[i].length; j++) {
                if (matrizCasas[i][j].imagem.equals(entrada) || (matrizCasas[i][j].peca != null && matrizCasas[i][j].peca.imagem.equals(entrada))) {
                    return matrizCasas[i][j];
                }
            }
        }
        return null;
    }

    public List<Casa> vizinhos(Casa casa,Peca peca, int qtd , int origem) {
        List<List<Casa>> saidas = new ArrayList<List<Casa>>();

        int lin = casa.getPosicaoMatriz()[0];
        int col = casa.getPosicaoMatriz()[1];
        
        if (peca.getColorOriginal().equals(Jogo.COLORJOGADOR2) || qtd > 0) {// qtd > 0 é pra comer pra tras
            if(origem != DID)
            saidas.add(chacaVizinhos(lin, col, DSE, peca, qtd));
            if(origem != DIE)
            saidas.add(chacaVizinhos(lin, col, DSD, peca, qtd));
             if(origem != DSD)
            saidas.add(chacaVizinhos(lin, col, DIE, peca, qtd+1));
            if(origem != DSE)
            saidas.add(chacaVizinhos(lin, col, DID, peca, qtd+1));
        }
        if (peca.getColorOriginal().equals(Jogo.COLORJOGADOR1) || qtd > 0) {
            if(origem != DID)
            saidas.add(chacaVizinhos(lin, col, DSE, peca, qtd+1));
            if(origem != DIE)
            saidas.add(chacaVizinhos(lin, col, DSD, peca, qtd+1));
            if(origem != DSD)
            saidas.add(chacaVizinhos(lin, col, DIE, peca, qtd));
            if(origem != DSE)
            saidas.add(chacaVizinhos(lin, col, DID, peca, qtd));
        }
        List<Casa> maior = null;
        int tamanho = 0;
        for (List<Casa> s : saidas) {
            if (s != null && s.size() > tamanho) {
                maior = s;
                tamanho = s.size();
            }
        }
        System.out.println("tamanho "+ tamanho);
        List<Casa> soma = new ArrayList<Casa>();
        for (List<Casa> s : saidas) {
            if (s != null && s.size() == tamanho) {
                if(tamanho==1){
                    soma.add(s.get(0));
                }else if(tamanho>=2){
                     soma.add(0,s.get(0));
                      soma.add(soma.size()-1,s.get(1));// faz com que as primeiras posições sejam as casas disponiveis
                }
            }
        }
        System.out.println("tamanho "+ soma.size());
        return soma;
    }

    public boolean par(int numero) {
        return numero % 2 == 0;

    }

    public static Tabuleiro retornaInstancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    public void adcionaArea(Stage estagio) {

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

    private List<Casa> chacaVizinhos(int lin, int col, int direcao, Peca peca, int qtd) {
        List<Casa> saida = new ArrayList<Casa>();
        int linAjuste = 0;
        int linLimite = 0;
        int colAjuste = 0;
        int colLimite = 0;
        switch (direcao) {
            case Tabuleiro.DSE:
                linAjuste = -1;
                linLimite = -1;
                colAjuste = -1;
                colLimite = -1;
                break;
            case Tabuleiro.DSD:
                linAjuste = -1;
                linLimite = -1;
                colAjuste = +1;
                colLimite = matrizCasas[0].length;
                break;
            case Tabuleiro.DIE:
                linAjuste = +1;
                linLimite = matrizCasas.length;
                colAjuste = -1;
                colLimite = -1;
                break;
            case Tabuleiro.DID:
                linAjuste = +1;
                linLimite = matrizCasas.length;
                colAjuste = +1;
                colLimite = matrizCasas[0].length;
                break;
        }
        if (lin + linAjuste != linLimite && col + colAjuste != colLimite) {
            if (matrizCasas[lin + linAjuste][col + colAjuste].peca == null) {
                if (qtd == 0) {
                    saida.add(matrizCasas[lin + linAjuste][col + colAjuste]);
                    matrizCasas[lin + linAjuste][col + colAjuste].rotulo = direcao;
                }
            } else if (!matrizCasas[lin + linAjuste][col + colAjuste].peca.getColorOriginal().equals(peca.getColorOriginal())) {

                if (lin + linAjuste * 2 != linLimite && col + colAjuste * 2 != colLimite) {
                    if (matrizCasas[lin + linAjuste * 2][col + colAjuste * 2].peca == null) {
                        saida.add(matrizCasas[lin + linAjuste][col + colAjuste]);
                        matrizCasas[lin + linAjuste][col + colAjuste].rotulo = direcao;
                        saida.add(matrizCasas[lin + linAjuste * 2][col + colAjuste * 2]);
                        matrizCasas[lin + linAjuste * 2][col + colAjuste * 2].rotulo = direcao;
                        saida.addAll(vizinhos(matrizCasas[lin + linAjuste * 2][col + colAjuste * 2],peca, qtd + 1,direcao));
                    }
                }
            }
        }
        return saida;
    }

}
