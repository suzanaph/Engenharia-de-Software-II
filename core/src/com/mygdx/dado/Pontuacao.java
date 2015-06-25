/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.dado;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author manue_000
 */
public class Pontuacao {

   
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public static void salvaPontuacao(String nome) {
        Formatter salva;
        lePontuacao();
        Usuario temp= getUsuario(nome);
        
        usuarios.add(temp);
        Collections.sort(usuarios);
        
        try {
            salva = new Formatter(new File("pontuacao.txt"));
            for (Usuario usuario : usuarios) {
                salva.format("%s\n%s\n ",usuario.nome ,usuario.pontuacao+"");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao tentar escrever no arquivo");
        }
    }

    public static void lePontuacao() {
        Scanner le;
        Usuario temp;
        try {
            le = new Scanner(new File("pontuacao.txt"));
            while (le.hasNextLine()) {
            	
            	String nome = le.nextLine();
            	int score = Integer.parseInt(le.nextLine());
                temp = new Usuario(nome, score);
               if(temp!=null)
                usuarios.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao tentar ler o arquivo");
        }

    }
    public static Usuario getUsuario(String nome){
    	for (Usuario usuario : usuarios) {
			if(usuario.nome.equals(nome)){
				return usuario;
			}
		}
    	Usuario novo = new  Usuario(nome,0);
    	usuarios.add(novo);
    	return novo;
    }

}
