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

    private Usuario usuario = new Usuario();
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public static void salvaPontuacao(String nome, int pontuacao) {
        Formatter salva;
        lePontuacao();
        Usuario temp= new Usuario(nome,pontuacao);
        
        usuarios.add(temp);
        Collections.sort(usuarios);
        
        try {
            salva = new Formatter(new File("pontuacao.txt"));
            for (Usuario usuario : usuarios) {
                salva.format(usuario.nome + ":" + usuario.pontuacao+"\n");
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

                temp = new Usuario(le.nextLine(), le.nextInt());
               if(temp!=null)
                usuarios.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao tentar ler o arquivo");
        }

    }


}
