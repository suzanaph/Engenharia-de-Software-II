/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.List;

/**
 * classe que pega as entradas do pc para movimentar as peças
 *
 * @author fabio
 */
public class Humano extends Jogador {

    public Casa anterior;

    @Override
    public boolean update(Actor entrada, Stage estagio) {
        if (getSelAreaPeca() != null && getSelAreaPeca().peca != null && getSelAreaPeca().peca.imagem.getActions().size > 0) {

        } else {
            Casa a = Jogo.getInstance().getTabuleiro().getArea(entrada);
            if (getSelAreaPeca() != null && getCaminhoEscolhido() != null) {
                return moverPeca(estagio);
            } else if (a != null) {

                if (a.peca != null && a != anterior) {// fazer algo para isso executar uma unica vez
                    if (getPecas().contains(a.peca)) {
                        if (getSelAreaPeca() != null && getSelAreaPeca().peca != null) {
                            getSelAreaPeca().peca.imagem.setColor(getSelAreaPeca().peca.getColorOriginal());
                            ocultarVizinhos();
                        }
                        setSelAreaPeca(a);
                        setVizinhosSelAreaPeca(Jogo.getInstance().getTabuleiro().vizinhos(a, getQtdJogadas() > 0));
                        if (getVizinhosSelAreaPeca().size() == 0 && getQtdJogadas() > 0) {
                            return true;
                        }
                        getSelAreaPeca().peca.imagem.setColor(Color.RED);
                        anterior = getSelAreaPeca();
                        exibirVizinhos();
                        System.out.println("caminhos: " + getVizinhosSelAreaPeca().size());
                    }
                } else if (getVizinhosSelAreaPeca() != null) {
                    casaValida(a);
                }
            }
        }
        return false;
    }

    private void casaValida(Casa a) {
        for (List<MovimentoEstado> col : getVizinhosSelAreaPeca()) {
            if (col.get(0).c.equals(a)) {
                setCaminhoEscolhido(col);

            }
        }
    }

}
