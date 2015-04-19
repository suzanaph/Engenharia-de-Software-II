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
public class BotaoAcaoMudarTela extends BotaoAcao {

    public BotaoAcaoMudarTela(String arg) {
        super(arg);
    }

    @Override
    public void realizar() {
        if (arg.equals("jogo")) {
            Jogo.getInstance().setScreen(new JanelaJogo());
        } else if (arg.equals("pontuacao")) {
            Jogo.getInstance().setScreen(new JanelaPontuacao());
        } else if (arg.equals("regras")) {
           
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(Gdx.files.getLocalStoragePath()+"regras.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    System.out.println("Não foi possível abrir o domcumento com as regras.");
                }
            }

        } else if (arg.equals("configuracoes")) {
            Jogo.getInstance().setScreen(new JanelaConfiguracoes());
        }
    }

}
