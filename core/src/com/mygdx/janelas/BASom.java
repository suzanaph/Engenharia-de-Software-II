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
public class BASom extends BotaoAcao {

    public BASom(String arg) {
        super(arg);
    }

    @Override
    public void realizar() {
        Jogo.getInstance().setSomAtivo(!Jogo.getInstance().isSomAtivo());
    }
    
}
