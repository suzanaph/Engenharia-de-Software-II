/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue_000
 */
public class Estado {

    int[][] matriz;
    int qtdPecaJ1;
    int qtdPecaJ2;
    //Valores para representar os tipos de objetos que poderão estar nas casas do tabuleiro
    public final static int PECAJOGADOR1 = 1;
    public final static int DAMAJOGADOR1 = 2;
    public final static int PECAJOGADOR2 = 3;
    public final static int DAMAJOGADOR2 = 4;
    public final static int VAZIA = 0;
    //diz qual o estado que chegou naquele após o movimento de uma peça
    Estado anterior;

    public Estado() {
        // tabuleiro inicial
        matriz = new int[][]{
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 3, 0, 3, 0, 3, 0, 3},
            {3, 0, 3, 0, 3, 0, 3, 0},
            {0, 3, 0, 3, 0, 3, 0, 3}
        };
        qtdPecaJ1 = 12;
        qtdPecaJ2 = 12;
    }
    // método construtor para gerar os novos estados apartir de uma matriz
    public Estado(int[][] matriz) {
        this.matriz = matriz;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == PECAJOGADOR1 || matriz[i][j] == DAMAJOGADOR1) {
                    qtdPecaJ1++;
                } else if (matriz[i][j] == PECAJOGADOR2 || matriz[i][j] == DAMAJOGADOR2) {
                    qtdPecaJ2++;
                }
            }
        }
    }
    // métodos de custo da IA também usado para escolher o melhor caminho.
    public int saldoJ1() {
        return qtdPecaJ1 - qtdPecaJ2;
    }

    public int saldoJ2() {
        return qtdPecaJ2 - qtdPecaJ1;
    }
    //método para criar uma cópia da matriz do estado do tabuleiro para que a copia possa ser editada
    public int[][] copia() {
        int[][] matrizCopia = new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizCopia[i][j] = matriz[i][j];
            }
        }

        return matrizCopia;
    }
    // método retorna todos os estados possiveis como a arvore de jogadas pra aquela peça especifica a IA sera usar esse método pra todas as peça 
    public List<Estado> movimentosPossiveis(int lin, int col, boolean capturou) {
        List<Estado> estados = new ArrayList<Estado>();
        Estado temp = null;
        //indica qual tipo de peça pode se capturar
        int eliminar = 0;
        System.out.println("lin : "+lin+" col:"+col);
        //limitar o jogador 2 a não comer pra trás
        if (matriz[lin][col] == PECAJOGADOR2) {
           System.out.println("jogador2");
            if (!capturou) {

                temp = moverDID(lin, col);
                if (temp != null) {
                    estados.add(temp);
                    temp.anterior = this;

                }
                temp = moverDIE(lin, col);
                if (temp != null) {
                    estados.add(temp);
                    temp.anterior = this;
                }
            }
            eliminar = PECAJOGADOR1;
        //limitar o jogador 2 a não comer pra trás
        } else if (matriz[lin][col] == PECAJOGADOR1) {
            System.out.println("jogador1");
            if (!capturou) {
                temp = moverDSD(lin, col);
                if (temp != null) {
                    estados.add(temp);
                }
                temp = moverDSE(lin, col);
                if (temp != null) {
                    estados.add(temp);
                }
            }
            eliminar = PECAJOGADOR2;
        }
         System.out.println("escolhido: "+ eliminar);
        // tentar realizar movimento de captura na diagonal superior direita caso não consiga retorna null
        temp = capturaDSD(lin, col, eliminar);
        if (temp != null) {
            System.out.println("DSD");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            //caso haja captura de peça, checar todos os movimentos que a peça pode realizar após se deslocar
            estados.addAll(temp.movimentosPossiveis(lin + 2, col + 2, true));
        }
        // tentar realizar movimento de captura na diagonal superior esquerda caso não consiga retorna null
        temp = capturaDSE(lin, col, eliminar);
        if (temp != null) {
            System.out.println("DSE");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
           //caso haja captura de peça, checar todos os movimentos que a peça pode realizar após se deslocar
            estados.addAll(temp.movimentosPossiveis(lin + 2, col - 2, true));
        }
        // tentar realizar movimento de captura na diagonal inferior direita caso não consiga retorna null
        temp = capturaDID(lin, col, eliminar);
        if (temp != null) {
            System.out.println("DID");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            //caso haja captura de peça, checar todos os movimentos que a peça pode realizar após se deslocar
            estados.addAll(temp.movimentosPossiveis(lin - 2, col + 2, true));
        }
        // tentar realizar movimento de captura na diagonal inferior esquerda caso não consiga retorna null
        temp = capturaDIE(lin, col, eliminar);
        if (temp != null) {
            System.out.println("DIE");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            //caso haja captura de peça, checar todos os movimentos que a peça pode realizar após se deslocar
            estados.addAll(temp.movimentosPossiveis(lin - 2, col - 2, true));
        }
        return estados;
    }
    // método captura usando peça normal na diagonal superior direita
    public Estado capturaDSD(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            //checa se a casa possui uma peça do jogador inimigo para ser capturada e se a casa após essa esta vazia.
            if ((cop[lin + 1][col + 1] == jogador || cop[lin + 1][col + 1] == jogador + 1) && cop[lin + 2][col + 2] == 0) {
                // mover peça da casa que ela estava para a nova
                cop[lin + 2][col + 2] = cop[lin][col];
                cop[lin][col] = 0;
                 //deletar peça capturada
                cop[lin + 1][col + 1] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
        // método captura usando peça normal na diagonal superior esquerda
    public Estado capturaDSE(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            //checa se a casa possui uma peça do jogador inimigo para ser capturada e se a casa após essa esta vazia.
            if ((cop[lin + 1][col - 1] == jogador || cop[lin + 1][col - 1] == jogador + 1) && cop[lin + 2][col - 2] == 0) {
                // mover peça da casa que ela estava para a nova
                cop[lin + 2][col - 2] = cop[lin][col];
                cop[lin][col] = 0;
                //deletar peça capturada
                cop[lin + 1][col - 1] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    // método captura usando peça normal na diagonal inferior direita
    public Estado capturaDID(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            //checa se a casa possui uma peça do jogador inimigo para ser capturada e se a casa após essa esta vazia.
            if ((cop[lin - 1][col + 1] == jogador || cop[lin - 1][col + 1] == jogador + 1) && cop[lin - 2][col + 2] == 0) {
                // mover peça da casa que ela estava para a nova
                cop[lin - 2][col + 2] = cop[lin][col];
                cop[lin][col] = 0;
                 //deletar peça capturada
                cop[lin - 1][col + 1] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    // método captura usando peça normal na diagonal inferior esquerda
    public Estado capturaDIE(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            //checa se a casa possui uma peça do jogador inimigo para ser capturada e se a casa após essa esta vazia.
            if ((cop[lin - 1][col - 1] == jogador || cop[lin - 1][col - 1] == jogador + 1) && cop[lin - 2][col - 2] == 0) {
                // mover peça da casa que ela estava para a nova
                cop[lin - 2][col - 2] = cop[lin][col];
                cop[lin][col] = 0;
                 //deletar peça capturada
                cop[lin - 1][col - 1] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
// movimento simples de peça normal para diagonal superior direita
    public Estado moverDSD(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin + 1][col + 1] == 0) {
                // mover peça da casa que ela estava para a nova 
                cop[lin + 1][col + 1] = cop[lin][col];
                cop[lin][col] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
// movimento simples de peça normal para diagonal superior esquerda
    public Estado moverDSE(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin + 1][col - 1] == 0) {
                // mover peça da casa que ela estava para a nova 
                cop[lin + 1][col - 1] = cop[lin][col];
                cop[lin][col] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
// movimento simples de peça normal para diagonal inferior direita
    public Estado moverDID(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin - 1][col + 1] == 0) {
                // mover peça da casa que ela estava para a nova 
                cop[lin - 1][col + 1] = cop[lin][col];
                cop[lin][col] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
// movimento simples de peça normal para diagonal inferior esquerda
    public Estado moverDIE(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin - 1][col - 1] == 0) {
                // mover peça da casa que ela estava para a nova 
                cop[lin - 1][col - 1] = cop[lin][col];
                cop[lin][col] = 0;
                Estado novo = new Estado(cop);
                return novo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    //retorna o inicial diferente do estado atual do tabuleiro para descobrir qual movimento foi usado para chegar no mesmo 
    public Estado inicial(Estado other) {
        Estado inicial = other;
       while (inicial.anterior != null && !inicial.anterior.equals(this)) {
            inicial = inicial.anterior;
        }
        return inicial;
    }
// dados aquela arvore de estados geradas pelas possibilidades de movimento e retorna as folhas de maior cutso , por lógica as folhas da arvore tem o melhor custo
   
    public List<Estado> melhorCusto(int lin, int col, List<Estado> estados) {
        List<Estado> melhor = new ArrayList<Estado>();
        int custo = Integer.MIN_VALUE;
        if (matriz[lin][col] == PECAJOGADOR1 || matriz[lin][col] == DAMAJOGADOR1) {
            for (Estado estado : estados) {
                if (custo < estado.saldoJ1()) {
                    custo = estado.saldoJ1();
                }
            }
            for (Estado estado : estados) {
                if (custo == estado.saldoJ1()) {
                    melhor.add(estado);
                }
            }
        } else if (matriz[lin][col] == PECAJOGADOR2 || matriz[lin][col] == DAMAJOGADOR2) {
            for (Estado estado : estados) {
                if (custo < estado.saldoJ2()) {
                    custo = estado.saldoJ2();
                }
            }
            for (Estado estado : estados) {
                if (custo == estado.saldoJ2()) {
                    melhor.add(estado);
                }
            }
        }

        return melhor;
    }
// ao subtrair o estado atual do estado gerado por um movimento gera-se uma matriz cheia de zeros 
    //com 2 digitos de valor igual que representa o movimentoe um diferente que é peca a ser removida 
    public int[][] posicoesValidas(int lin, int col, Estado m) {
        return subtracaoMatrizes(matriz, m.matriz);
    }

    public int[][] somaMatrizes(int[][] m1, int[][] m2) {
        int[][] m3 = new int[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                m3[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return m3;
    }

    public int[][] subtracaoMatrizes(int[][] m1, int[][] m2) {
        int[][] m3 = new int[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                m3[i][j] = Math.abs(m1[i][j] - m2[i][j]);
            }
        }
        return m3;
    }
    public void exibir(){
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print( matriz[i][j]+" ");
            }
        }
         System.out.println("");
    }
}
