/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;
import com.mygdx.jogo.Jogo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author fabio
 */
public class BAMudarTela extends BotaoAcao {
    public static String JOGO = "jogo";
    public static String CONFIGURACOES= "configuracoes";
    public static String MENU = "menu";
    public static String PONTUACAO = "pontuacao";
    public BAMudarTela(String arg) {
        super(arg);
    }

    @Override
    public void realizar() {
        Jogo.getInstance().getMusica().stop();
        if (arg.equals("jogo")) {
            Jogo.getInstance().setScreen(new JanelaJogo());
        } else if (arg.equals("pontuacao")) {
            Jogo.getInstance().setScreen(new JanelaPontuacao());
        }  else if (arg.equals("configuracoes")) {
            Jogo.getInstance().setScreen(new JanelaConfiguracoes());
        } else if (arg.equals("menu")) {
            Jogo.getInstance().setScreen(new JanelaMenu());
        }
    }

}
