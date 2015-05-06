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
    Area[][] posicoes; //Matriz que controla as posições do tabuleiro

    private Tabuleiro() {
        posicoes = new Area[altura][largura];
        Color cor = Color.WHITE;
        for (int lin = 0; lin < altura; lin++) {

            for (int col = 0; col < largura; col++) {

                if (par(lin) && par(col)) {
                    cor = Color.BLACK;
                } else if (!par(lin) && !par(col)) {
                    cor = Color.BLACK;
                } else {
                    cor = Color.WHITE;
                }

                posicoes[lin][col] = new Area(posX + Area.LARGURA * col, posY + Area.ALTURA * lin, cor);
                posicoes[lin][col].setPosicaoMatriz(lin, col);
                if (estado.matriz[lin][col] == 1) {
                    if (lin >= 0 && lin <= 2) {
                        posicoes[lin][col].peca = new Peca(posX + Area.LARGURA * col, posY + Area.ALTURA * lin);
                        posicoes[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR1);
                        posicoes[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR1);
                        Jogo.getInstance().getJogador1().getPecas().add(posicoes[lin][col].peca);
                    } else if (lin >= 5 && lin <= 7) {
                        posicoes[lin][col].peca = new Peca(posX + Area.LARGURA * col, posY + Area.ALTURA * lin);
                        posicoes[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR2);
                        posicoes[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR2);
                        Jogo.getInstance().getJogador2().getPecas().add(posicoes[lin][col].peca);
                    }

                }
            }
        }

    }

    public Area getArea(Actor entrada) {
        for (int i = 0; i < posicoes.length; i++) {
            for (int j = 0; j < posicoes[i].length; j++) {
                if (posicoes[i][j].imagem.equals(entrada) || (posicoes[i][j].peca != null && posicoes[i][j].peca.imagem.equals(entrada))) {
                    return posicoes[i][j];
                }
            }
        }
        return null;
    }

    public List<Area> vizinhos(Area entrada,Peca p, int qtd , int origem) {
        List<List<Area>> saidas = new ArrayList<List<Area>>();

        int lin = entrada.getPosicaoMatriz()[0];
        int col = entrada.getPosicaoMatriz()[1];
        
        if (p.getColorOriginal().equals(Jogo.COLORJOGADOR2) || qtd > 0) {// qtd > 0 é pra comer pra tras
            if(origem != DID)
            saidas.add(chacaVizinhos(lin, col, DSE, p, qtd));
            if(origem != DIE)
            saidas.add(chacaVizinhos(lin, col, DSD, p, qtd));
             if(origem != DSD)
            saidas.add(chacaVizinhos(lin, col, DIE, p, qtd+1));
            if(origem != DSE)
            saidas.add(chacaVizinhos(lin, col, DID, p, qtd+1));
        }
        if (p.getColorOriginal().equals(Jogo.COLORJOGADOR1) || qtd > 0) {
            if(origem != DID)
            saidas.add(chacaVizinhos(lin, col, DSE, p, qtd+1));
            if(origem != DIE)
            saidas.add(chacaVizinhos(lin, col, DSD, p, qtd+1));
            if(origem != DSD)
            saidas.add(chacaVizinhos(lin, col, DIE, p, qtd));
            if(origem != DSE)
            saidas.add(chacaVizinhos(lin, col, DID, p, qtd));
        }
        List<Area> maior = null;
        int tamanho = 0;
        for (List<Area> s : saidas) {
            if (s != null && s.size() > tamanho) {
                maior = s;
                tamanho = s.size();
            }
        }
        System.out.println("tamanho "+ tamanho);
        List<Area> soma = new ArrayList<Area>();
        for (List<Area> s : saidas) {
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

        for (int i = 0; i < posicoes.length; i++) {
            for (int j = 0; j < posicoes.length; j++) {
                estagio.addActor(posicoes[i][j].imagem);
                if (posicoes[i][j].peca != null) {
                    estagio.addActor(posicoes[i][j].peca.imagem);
                }
            }
        }
        for (int i = 0; i < posicoes.length; i++) {
            for (int j = 0; j < posicoes.length; j++) {
                if (posicoes[i][j].peca != null) {
                    estagio.addActor(posicoes[i][j].peca.imagem);
                }
            }
        }

    }

    private List<Area> chacaVizinhos(int lin, int col, int direcao, Peca p, int qtd) {
        List<Area> saida = new ArrayList<Area>();
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
                colLimite = posicoes[0].length;
                break;
            case Tabuleiro.DIE:
                linAjuste = +1;
                linLimite = posicoes.length;
                colAjuste = -1;
                colLimite = -1;
                break;
            case Tabuleiro.DID:
                linAjuste = +1;
                linLimite = posicoes.length;
                colAjuste = +1;
                colLimite = posicoes[0].length;
                break;
        }
        if (lin + linAjuste != linLimite && col + colAjuste != colLimite) {
            if (posicoes[lin + linAjuste][col + colAjuste].peca == null) {
                if (qtd == 0) {
                    saida.add(posicoes[lin + linAjuste][col + colAjuste]);
                    posicoes[lin + linAjuste][col + colAjuste].rotulo = direcao;
                }
            } else if (!posicoes[lin + linAjuste][col + colAjuste].peca.getColorOriginal().equals(p.getColorOriginal())) {

                if (lin + linAjuste * 2 != linLimite && col + colAjuste * 2 != colLimite) {
                    if (posicoes[lin + linAjuste * 2][col + colAjuste * 2].peca == null) {
                        saida.add(posicoes[lin + linAjuste][col + colAjuste]);
                        posicoes[lin + linAjuste][col + colAjuste].rotulo = direcao;
                        saida.add(posicoes[lin + linAjuste * 2][col + colAjuste * 2]);
                        posicoes[lin + linAjuste * 2][col + colAjuste * 2].rotulo = direcao;
                        saida.addAll(vizinhos(posicoes[lin + linAjuste * 2][col + colAjuste * 2],p, qtd + 1,direcao));
                    }
                }
            }
        }
        return saida;
    }

}
