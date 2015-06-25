/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.janelas.JanelaMenu;

import java.util.List;

import javax.swing.JOptionPane;

/**
 * classe que pega as entradas do pc para movimentar as peças
 *
 * @author fabio
 */
public class Humano extends Jogador {

    public Casa anterior;
    /**Método utilizado para atualizar os estados do tabuleiro conforme as casas que o jogador escolher,
    * @param selecionada é a casa selecionada pelo jogador.  
    * @param estagio utilizado no método de mover invocado por este método para remover a peça capturada.
    * @return se a saida for verdadeira o turno do jogador é finalizado ,caso for falsa ele continua atualizando.
    */
    @Override
    public boolean update(Casa selecionada, Stage estagio) {
     

            if (getSelAreaPeca() != null && getCaminhoEscolhido() != null) {
                 if(moverPeca(estagio)){
                	 anterior = null;
                	 return true;
                }else{
                	return false;
                }
            } else if (selecionada != null) {
            	// impede que recalcule o caminho de uma pe�a ja clicada ao clicar nela denovo.
                if (selecionada.peca != null && selecionada != anterior) {// fazer algo para isso executar uma unica vez
                    if (getPecas().contains(selecionada.peca)) {
                    	// caso vc tenha uma pe�a selecionada antes e selecione outro vc apaga a sele��o
                        if (getSelAreaPeca() != null && getSelAreaPeca().peca != null
                       && getVizinhosSelAreaPeca()!=null) {
                            getSelAreaPeca().peca.imagem.setColor(getSelAreaPeca().peca.getColorOriginal());
                            ocultarVizinhos();
                        }
                        setSelAreaPeca(selecionada);
                        setVizinhosSelAreaPeca(Jogo.getInstance().getTabuleiro().caminhosDisponiveis(selecionada, getQtdJogadas() > 0));
                       
                        if (getVizinhosSelAreaPeca().size() == 0 && getQtdJogadas() > 0) {
                            return true;
                        }
                        getSelAreaPeca().peca.imagem.setColor(Color.RED);
                        anterior = getSelAreaPeca();
                        exibirVizinhos();
                       
                    }
                } else if (getVizinhosSelAreaPeca() != null) {
                    casaValida(selecionada);
                }
            }
        
        return false;
    }
    /**Método utilizado para descobrir se a casa selecionada pelo jogador pertence a um caminho possivel gerado 
     * pela casa selecionada anteriormente contendo uma peça do jogador.
    * @param selecionada é a casa selecionada pelo jogador que não deve possuir peça.  
    */
    private void casaValida(Casa selecionada) {
        for (List<MovimentoEstado> col : getVizinhosSelAreaPeca()) {
            if (col.get(0).atual.equals(selecionada)) {
                setCaminhoEscolhido(col);

            }
        }
    }

}
