/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.dado;

/**
 *
 * @author manue_000
 */
public class Usuario implements Comparable {

     String nome;
     int pontuacao;

    public Usuario() {
    }

    public Usuario(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    @Override
    public int compareTo(Object usuario) {
     return this.pontuacao-((Usuario)usuario).pontuacao;
    }

}
