/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

/**
 *
 * @author fabio
 */
public abstract class BotaoAcao {
    /*
    Classe BotaoAcao é um componente de botao que define a ação que mesmo realiza quando clicado, 
    essa ação é definida pela implementação do método realizar.
    */
    String arg;//argumento auxiliar
    public BotaoAcao(String arg){
        this.arg=arg;
    };
    public abstract void realizar();
}
