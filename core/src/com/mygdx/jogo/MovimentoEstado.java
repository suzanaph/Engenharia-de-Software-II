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
    Casa atual;
    Casa eliminar;// guarda referência da peça que foi eliminada
    MovimentoEstado anterior;
    int jogador=0;
    int valor;
    
    MovimentoEstado(Casa matrizCasa, Estado inicial) {
        this.t = inicial;
        this.atual = matrizCasa; 
        jogador = t.matriz[atual.posicao[0]][atual.posicao[1]];
        if(jogador==Estado.PECAJOGADOR1 || jogador==Estado.DAMAJOGADOR1){
        	jogador=1;
        }else if(jogador==Estado.PECAJOGADOR2 || jogador==Estado.DAMAJOGADOR2){
        	jogador=2;
        }
    }
    MovimentoEstado(int jogador, Estado inicial) {
        this.t = inicial;
        this.jogador = jogador;
    }
    MovimentoEstado(Casa matrizCasa,Casa eliminar, Estado inicial) {
        this.t = inicial;
        this.atual = matrizCasa;  
        this.eliminar = eliminar;
        jogador = t.matriz[atual.posicao[0]][atual.posicao[1]];
        if(jogador==Estado.PECAJOGADOR1 || jogador==Estado.DAMAJOGADOR1){
        	jogador=1;
        }else if(jogador==Estado.PECAJOGADOR2 || jogador==Estado.DAMAJOGADOR2){
        	jogador=2;
        }
    }
    public int getCustoJ1(){
        return t.saldoJ1();
    }
    public int getCustoJ2(){
        return t.saldoJ2();
    }
    public int getDiferencaJ1(){
        return t.diferencaJ1();
    }
    public int getDiferencaJ2(){
        return t.diferencaJ2();
    }
  public int getJogador(){
	  return jogador;
  }
  

public Estado getT() {
	return t;
}
public void setT(Estado t) {
	this.t = t;
}
public Casa getAtual() {
	return atual;
}
public void setAtual(Casa atual) {
	this.atual = atual;
}
public Casa getEliminar() {
	return eliminar;
}
public void setEliminar(Casa eliminar) {
	this.eliminar = eliminar;
}
public MovimentoEstado getAnterior() {
	return anterior;
}
public void setAnterior(MovimentoEstado anterior) {
	this.anterior = anterior;
}
public void setJogador(int jogador) {
	this.jogador = jogador;
}
public int getValor() {
	return valor;
}
public void setValor(int valor) {
	this.valor = valor;
}


  
   
}
