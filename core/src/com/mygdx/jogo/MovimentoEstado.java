/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.jogo;

/**
 *
 * @author fabio
 */
public class MovimentoEstado {
    Estado t;
    Casa c;

    MovimentoEstado(Casa matrizCasa, Estado inicial) {
        this.t = inicial;
        this.c = matrizCasa;  
    }
}
