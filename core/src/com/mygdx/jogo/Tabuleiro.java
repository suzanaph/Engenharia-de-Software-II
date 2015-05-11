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
                if (estado.matriz[lin][col] == Estado.PECAJOGADOR1) {
                    //Adiciona peças nas três primeiras linhas do tabuleiro.
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR1);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR1);//lol isso deve estar dentro de peca !!
                    Jogo.getInstance().getJogador1().getPecas().add(matrizCasas[lin][col].peca);
                } else if (estado.matriz[lin][col] == Estado.PECAJOGADOR2) {//Adiciona peças nas três últimas linhas do tabuleiro.
                    matrizCasas[lin][col].peca = new Peca(posX + Casa.LARGURA * col, posY + Casa.ALTURA * lin);
                    matrizCasas[lin][col].peca.imagem.setColor(Jogo.COLORJOGADOR2);
                    matrizCasas[lin][col].peca.setColorOriginal(Jogo.COLORJOGADOR2);
                    Jogo.getInstance().getJogador2().getPecas().add(matrizCasas[lin][col].peca);

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
    
    //Retorna casas disponíveis para peca andar
    public List<MovimentoEstado> vizinhos(Casa casa,boolean capturado) {
        List<MovimentoEstado> saida = new ArrayList<MovimentoEstado>();
        int lin = casa.posicao[0];
        int col = casa.posicao[1];
        System.out.println("atual");
        estado.exibir();
        List<Estado> estados = estado.movimentosPossiveis(lin, col, capturado);
        
        System.out.println("estados: " + estados.size());
        for (Estado estado1 : estados) {
            estado1.exibir();
        }
        estados = estado.melhorCusto(lin, col, estados);
        System.out.println("melhor percusso: " + estados.size());
        for (Estado estado1 : estados) {
            estado1.exibir();
        }
        List<Estado> iniciais = new ArrayList<Estado>();
        for (Estado est : estados) {
            iniciais.add(estado.inicial(est));
        }
        System.out.println("iniciais:" + iniciais.size());
        for (Estado inicial : iniciais) {
            int[][] m = estado.posicoesValidas(lin, col, inicial);
            inicial.exibir();
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    if ((i != lin || j != col) && m[i][j] == estado.matriz[lin][col]) {
                        saida.add(new MovimentoEstado(matrizCasas[i][j],inicial));
                    }
                }
            }
        }

        System.out.println("saida:" + saida.size());
        for (MovimentoEstado s : saida) {
            System.out.println("lin "+ s.c.posicao[0]+" col: "+s.c.posicao[1]);
            s.t.exibir();
        }
        return saida;
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
    public boolean setEstado(Estado t,Stage estagio ){
        estado = t;
         for (int i = 0; i < estado.matriz.length; i++) {
            for (int j = 0; j < estado.matriz.length; j++) {
                if (matrizCasas[i][j].peca != null && estado.matriz[i][j]==0) {
                   estagio.getActors().removeValue(matrizCasas[i][j].peca.imagem,true);
                   matrizCasas[i][j].peca=null;
                   return false;
                }
            }
        }
         return true;
    }
}
