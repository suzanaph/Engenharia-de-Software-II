/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public abstract class Jogador {

    private boolean turno;

    public abstract boolean update(Casa entrada, Stage estagio);
    private List<Peca> pecas;
    private Casa selAreaPeca;
    private List<MovimentoEstado> caminhoEscolhido;
    private List<List<MovimentoEstado>> caminhos;
    private int qtdJogadas;

    public Jogador() {
        pecas = new ArrayList<Peca>();
        caminhos = new ArrayList<List<MovimentoEstado>>();
    }

    /**Método utilizado para modificar a cor das casas para qual a peça do jogador pode se mover
    */
    public void exibirVizinhos() {
        for (List<MovimentoEstado> vizinho : caminhos) {
            if (vizinho.get(0).c.peca == null) {
                vizinho.get(0).c.imagem.setColor(Color.GREEN);
            }
        }
    }

    /**Método utilizado para ao termino de uma jogada fazer as casas voltarem a sua cor inicial
    *
    */
    public void ocultarVizinhos() {
        for (List<MovimentoEstado> vizinho : caminhos) {
            vizinho.get(0).c.imagem.setColor(Color.BLACK);
        }
    }

    /**
     * @return the turno
     */
    public boolean isTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    /**
     * @return the pecas
     */
    public List<Peca> getPecas() {
        return pecas;
    }

    /**
     * @param pecas the pecas to set
     */
    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    /**
     * @return the selAreaPeca
     */
    public Casa getSelAreaPeca() {
        return selAreaPeca;
    }

    /**
     * @param selPeca the selAreaPeca to set
     */
    public void setSelAreaPeca(Casa selAreaPeca) {
        this.selAreaPeca = selAreaPeca;

    }

    /**
     * @return the caminhoEscolhido
     */
    public List<MovimentoEstado> getCaminhoEscolhido() {
        return caminhoEscolhido;
    }

    /**
     * @param selArea the caminhoEscolhido to set
     */
    public void setCaminhoEscolhido(List<MovimentoEstado> selAreaVazia) {
        this.caminhoEscolhido = selAreaVazia;
    }

    /**
     * @return the caminhos
     */
    public List<List<MovimentoEstado>> getVizinhosSelAreaPeca() {
        return caminhos;
    }

    /**
     * @param vizinhosSelAreaPeca the caminhos to set
     */
    public void setVizinhosSelAreaPeca(List<List<MovimentoEstado>> vizinhosSelAreaPeca) {
        this.caminhos = vizinhosSelAreaPeca;
    }
/**Método utilizado para realizar o movimento das peças , trocando as refências da casa que ela estava 
 * e a casa para qual ela se moveu.
    * @param estagio utilizado para remover a peça que for capturada.
    * @return se verdadeiro termina o turno do jogador falso ele continua atualizando,
    */
    public boolean moverPeca(Stage estagio) {
        if (selAreaPeca != null && caminhoEscolhido != null) {
            List<MovimentoEstado> to = caminhoEscolhido;
            MovimentoEstado alvo = to.get(0);
            Peca p = selAreaPeca.peca;
            
            p.imagem.addAction(Actions.sequence(Actions.moveTo(alvo.c.imagem.getX(), alvo.c.imagem.getY(), 0.5f)));
            alvo.c.peca = p;
            alvo.c.peca.imagem.setColor(p.getColorOriginal());
            selAreaPeca.peca = null;
            selAreaPeca = alvo.c;
            ocultarVizinhos();
            // removendo o passo ja dado
            to.remove(alvo);
            //limitar os movimentos a capturas após uma captura
            setQtdJogadas(qtdJogadas + 1);
            Jogo.getInstance().getTabuleiro().estado.exibir();
            //t.t.exibir();
            // se na troca de estado ouver uma captura esse método retorna um booleano para que jogador ganhe mais uma jogada de captura         
            Jogo.getInstance().getTabuleiro().setEstado(alvo, estagio);
       
             if (!to.isEmpty()) {
                return false;
            } else {
            	 
                qtdJogadas=0;
                selAreaPeca = null;
                caminhoEscolhido = null;
                this.caminhos = null;
                return true;
            }
        }
        return false;
    }

    
	


	/**
     * @return the qtdJogadas
     */
    public int getQtdJogadas() {
        return qtdJogadas;
    }

    /**
     * @param qtdJogadas the qtdJogadas to set
     */
    public void setQtdJogadas(int qtdJogadas) {
        this.qtdJogadas = qtdJogadas;
    }

}
