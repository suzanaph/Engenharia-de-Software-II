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
   
    
    MovimentoEstado(Casa matrizCasa, Estado inicial) {
        this.t = inicial;
        this.atual = matrizCasa; 
   
    }
    
    MovimentoEstado(Casa matrizCasa,Casa eliminar, Estado inicial) {
        this.t = inicial;
        this.atual = matrizCasa;  
        this.eliminar = eliminar;
       
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



  
   
}
