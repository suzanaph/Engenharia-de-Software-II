/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * classe que pega as entradas do pc para movimentar as peças
 *
 * @author fabio
 */
public class Humano extends Jogador {

    @Override
    public boolean update(Actor entrada,Stage estagio) {
       
        Area a = Jogo.getInstance().getTabuleiro().getArea(entrada);
        if (a != null) {
            if (a.peca != null) {// fazer algo para isso executar uma unica vez
                if (getPecas().contains(a.peca)) {
                    if(getSelAreaPeca()!=null && getSelAreaPeca().peca!= null){
                        getSelAreaPeca().peca.imagem.setColor(getSelAreaPeca().peca.getColorOriginal());
                        ocultarVizinhos();
                    }
                    setSelAreaPeca(a);
                    setVizinhosSelAreaPeca(Jogo.getInstance().getTabuleiro().vizinhos(a,a.peca,getQtdJogadas(),0));
                    if(getVizinhosSelAreaPeca().size()==0 && getQtdJogadas()>0)
                        return true;
                    getSelAreaPeca().peca.imagem.setColor(Color.RED);
                    exibirVizinhos();
                }
            } else {
                 
                if( getSelAreaPeca()!=null && getVizinhosSelAreaPeca().contains(a) ) {
                    setSelAreaVazia(a);
                    return moverPeca(estagio);
                  
                }
            }
        }
        return false;
    }

}
