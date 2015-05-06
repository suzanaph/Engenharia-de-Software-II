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
    private Area selAreaPeca;
    private Area selAreaVazia;
    private List<Area> vizinhosSelAreaPeca;
    private int qtdJogadas;

    public Jogador() {
        pecas = new ArrayList<Peca>();
        vizinhosSelAreaPeca = new ArrayList<Area>();
    }

    public void exibirVizinhos() {
        for (Area vizinho : vizinhosSelAreaPeca) {
            if (vizinho.peca == null) {
                vizinho.imagem.setColor(Color.GREEN);
            }
        }
    }

    public void ocultarVizinhos() {
        for (Area vizinho : vizinhosSelAreaPeca) {
            vizinho.imagem.setColor(Color.BLACK);
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
    public Area getSelAreaPeca() {
        return selAreaPeca;
    }

    /**
     * @param selPeca the selAreaPeca to set
     */
    public void setSelAreaPeca(Area selAreaPeca) {
        this.selAreaPeca = selAreaPeca;

    }

    /**
     * @return the selAreaVazia
     */
    public Area getSelAreaVazia() {
        return selAreaVazia;
    }

    /**
     * @param selArea the selAreaVazia to set
     */
    public void setSelAreaVazia(Area selAreaVazia) {
        this.selAreaVazia = selAreaVazia;
    }

    /**
     * @return the vizinhosSelAreaPeca
     */
    public List<Area> getVizinhosSelAreaPeca() {
        return vizinhosSelAreaPeca;
    }

    /**
     * @param vizinhosSelAreaPeca the vizinhosSelAreaPeca to set
     */
    public void setVizinhosSelAreaPeca(List<Area> vizinhosSelAreaPeca) {
        this.vizinhosSelAreaPeca = vizinhosSelAreaPeca;
    }

    public boolean moverPeca(Stage estagio) {
        if (selAreaPeca != null && selAreaVazia != null) {
           
            boolean retorno=true;
            Area t =  selAreaVazia;
            Peca p = selAreaPeca.peca;
            p.imagem.addAction(Actions.sequence( Actions.moveTo(t.imagem.getX(), t.imagem.getY(), 0.5f)));
            t.peca = p;
            t.peca.imagem.setColor(p.getColorOriginal());
            
            for (Area vizinho : vizinhosSelAreaPeca) {
                if(!vizinho.equals(t) && vizinho.rotulo== t.rotulo){
                    estagio.getActors().removeValue(vizinho.peca.imagem, true);
                    vizinho.peca = null;
                    retorno = false;
                    vizinho.rotulo=0;
                }
            }
            selAreaPeca.peca = null;
            selAreaPeca = null;
            selAreaVazia = null;
            t.rotulo=0;
            ocultarVizinhos();
            this.vizinhosSelAreaPeca = null;
            setQtdJogadas(qtdJogadas+1);
            return retorno;
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
