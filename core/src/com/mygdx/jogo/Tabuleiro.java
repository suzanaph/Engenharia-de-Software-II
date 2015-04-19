/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue_000
 */
public class Tabuleiro {

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
                    cor = Color.RED;
                } else if (!par(lin) && !par(col)) {
                    cor = Color.RED;
                } else {
                    cor = Color.WHITE;
                }

                posicoes[lin][col] = new Area(posX + Area.LARGURA * col, posY + Area.ALTURA * lin, cor);
                if (estado.matriz[lin][col] == 1) {
                    if (lin >= 0 && lin <= 2) {
                        posicoes[lin][col].peca = new Peca(posX + Area.LARGURA * col, posY + Area.ALTURA * lin, true);
                    }
                }

                if (estado.matriz[lin][col] == 1) {
                    if (lin >= 5 && lin <= 7) {
                        posicoes[lin][col].peca = new Peca(posX + Area.LARGURA * col, posY + Area.ALTURA * lin, false);
                    }
                }
            }

        }
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

    }

}
