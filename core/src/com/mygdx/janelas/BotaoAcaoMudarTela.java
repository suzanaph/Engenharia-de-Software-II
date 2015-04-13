/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.mygdx.jogo.Jogo;

/**
 *
 * @author fabio
 */
public class BotaoAcaoMudarTela extends BotaoAcao{

    public BotaoAcaoMudarTela(String arg) {
        super(arg);
    }

    @Override
    public void ativar() {
        if(arg.equals("jogo")){
         Jogo.getInstance().setScreen(new JanelaJogo());
        }else if(arg.equals("pontuacao")){
            Jogo.getInstance().setScreen(new JanelaPontuacao());
        }else if(arg.equals("regras")){
            Jogo.getInstance().setScreen(new JanelaRegras());
        }else if(arg.equals("configuracoes")){
            Jogo.getInstance().setScreen(new JanelaConfiguracoes());
        }
    }
    
}
