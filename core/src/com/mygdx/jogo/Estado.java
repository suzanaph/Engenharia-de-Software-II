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
    public final static int PECAJOGADOR1 = 1;
    public final static int DAMAJOGADOR1 = 2;
    public final static int PECAJOGADOR2 = 3;
    public final static int DAMAJOGADOR2 = 4;
    public final static int VAZIA = 0;
    Estado anterior;

    public Estado() {
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

    public int saldoJ1() {
        return qtdPecaJ1 - qtdPecaJ2;
    }

    public int saldoJ2() {
        return qtdPecaJ2 - qtdPecaJ1;
    }

    public int[][] copia() {
        int[][] matrizCopia = new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizCopia[i][j] = matriz[i][j];
            }
        }

        return matrizCopia;
    }

    public List<Estado> movimentosPossiveis(int lin, int col, boolean capturou) {
        List<Estado> estados = new ArrayList<Estado>();
        Estado temp = null;
        int escolhido = 0;
        System.out.println("lin : "+lin+" col:"+col);
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
            escolhido = PECAJOGADOR1;
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
            escolhido = PECAJOGADOR2;
        }
         System.out.println("escolhido: "+ escolhido);
        temp = capturaDSD(lin, col, escolhido);
        if (temp != null) {
            System.out.println("DSD");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            estados.addAll(temp.movimentosPossiveis(lin + 2, col + 2, true));
        }
        temp = capturaDSE(lin, col, escolhido);
        if (temp != null) {
            System.out.println("DSE");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            estados.addAll(temp.movimentosPossiveis(lin + 2, col - 2, true));
        }
        temp = capturaDID(lin, col, escolhido);
        if (temp != null) {
            System.out.println("DID");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            estados.addAll(temp.movimentosPossiveis(lin - 2, col + 2, true));
        }
        temp = capturaDIE(lin, col, escolhido);
        if (temp != null) {
            System.out.println("DIE");
            temp.exibir();
            estados.add(temp);
            temp.anterior = this;
            estados.addAll(temp.movimentosPossiveis(lin - 2, col - 2, true));
        }
        return estados;
    }

    public Estado capturaDSD(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            if ((cop[lin + 1][col + 1] == jogador || cop[lin + 1][col + 1] == jogador + 1) && cop[lin + 2][col + 2] == 0) {
                cop[lin + 2][col + 2] = cop[lin][col];
                cop[lin][col] = 0;
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

    public Estado capturaDSE(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            if ((cop[lin + 1][col - 1] == jogador || cop[lin + 1][col - 1] == jogador + 1) && cop[lin + 2][col - 2] == 0) {
                cop[lin + 2][col - 2] = cop[lin][col];
                cop[lin][col] = 0;
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

    public Estado capturaDID(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            if ((cop[lin - 1][col + 1] == jogador || cop[lin - 1][col + 1] == jogador + 1) && cop[lin - 2][col + 2] == 0) {
                cop[lin - 2][col + 2] = cop[lin][col];
                cop[lin][col] = 0;
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

    public Estado capturaDIE(int lin, int col, int jogador) {
        try {
            int[][] cop = copia();
            if ((cop[lin - 1][col - 1] == jogador || cop[lin - 1][col - 1] == jogador + 1) && cop[lin - 2][col - 2] == 0) {
                cop[lin - 2][col - 2] = cop[lin][col];
                cop[lin][col] = 0;
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

    public Estado moverDSD(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin + 1][col + 1] == 0) {
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

    public Estado moverDSE(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin + 1][col - 1] == 0) {
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

    public Estado moverDID(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin - 1][col + 1] == 0) {
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

    public Estado moverDIE(int lin, int col) {
        try {
            int[][] cop = copia();
            if (cop[lin - 1][col - 1] == 0) {
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

    public Estado inicial(Estado other) {
        Estado inicial = other;
       while (inicial.anterior != null && !inicial.anterior.equals(this)) {
            inicial = inicial.anterior;
        }
        return inicial;
    }

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

    public int[][] posicoesValidas(int lin, int col, Estado m) {
        int[][] saida = new int[matriz.length][matriz.length];
        saida = somaMatrizes(saida, subtracaoMatrizes(matriz, m.matriz));
        return saida;
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
