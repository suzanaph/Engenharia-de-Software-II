/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    public abstract boolean update(Actor entrada, Stage estagio);
    private List<Peca> pecas;
    private Casa selAreaPeca;
    private MovimentoEstado selAreaVazia;
    private List<MovimentoEstado> vizinhosSelAreaPeca;
    private int qtdJogadas;

    public Jogador() {
        pecas = new ArrayList<Peca>();
        vizinhosSelAreaPeca = new ArrayList<MovimentoEstado>();
    }
    // muda a cor da casa que jogodor atual pode se mover.
    public void exibirVizinhos() {
        for (int i = 0; i < vizinhosSelAreaPeca.size(); i++) {

            MovimentoEstado vizinho = vizinhosSelAreaPeca.get(i);
            if (vizinho.c.peca == null) {
                vizinho.c.imagem.setColor(Color.GREEN);
            }
        }
    }
    //ao termino de uma jogada fazer as casas voltarem a sua cor normal
    public void ocultarVizinhos() {
        for (MovimentoEstado vizinho : vizinhosSelAreaPeca) {
            vizinho.c.imagem.setColor(Color.BLACK);
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
     * @return the selAreaVazia
     */
    public MovimentoEstado getSelAreaVazia() {
        return selAreaVazia;
    }

    /**
     * @param selArea the selAreaVazia to set
     */
    public void setSelAreaVazia(MovimentoEstado selAreaVazia) {
        this.selAreaVazia = selAreaVazia;
    }

    /**
     * @return the vizinhosSelAreaPeca
     */
    public List<MovimentoEstado> getVizinhosSelAreaPeca() {
        return vizinhosSelAreaPeca;
    }

    /**
     * @param vizinhosSelAreaPeca the vizinhosSelAreaPeca to set
     */
    public void setVizinhosSelAreaPeca(List<MovimentoEstado> vizinhosSelAreaPeca) {
        this.vizinhosSelAreaPeca = vizinhosSelAreaPeca;
    }
//para que a peça seja movida o jogador deve ter selecionado uma peça e uma casa das disponiveis para o movimento.
    public boolean moverPeca(Stage estagio) {
        if (selAreaPeca != null && selAreaVazia != null) {

            
            MovimentoEstado t = selAreaVazia;
            Peca p = selAreaPeca.peca;
            p.imagem.addAction(Actions.sequence(Actions.moveTo(t.c.imagem.getX(), t.c.imagem.getY(), 0.5f)));
            t.c.peca = p;
            t.c.peca.imagem.setColor(p.getColorOriginal());

            selAreaPeca.peca = null;
            selAreaPeca = null;
            selAreaVazia = null;
            ocultarVizinhos();
            this.vizinhosSelAreaPeca = null;
            //limitar os movimentos a capturas após uma captura
            setQtdJogadas(qtdJogadas + 1);
            // se na troca de estado ouver uma captura esse método retorna um booleano para que jogador ganhe mais uma jogada de captura
            return Jogo.getInstance().getTabuleiro().setEstado(t.t, estagio);
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
