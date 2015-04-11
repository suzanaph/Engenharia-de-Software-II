/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.dado;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author manue_000
 */
public class Pontuacao {
    private Usuario usuario; 
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
      
       public static void salvaPontuacao(ArrayList<Usuario> lista) {
        Formatter salva;
        try {
            salva = new Formatter(new File("pontuacao.txt"));
            for (Usuario usuario : lista) {
                salva.format(usuario.nome + ":" + "pontuacao \n");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao tentar escrever no arquivo");
        }
    }

    public static void lePontuacao() {
        Scanner le;
        try {
le= new Scanner(new File("pontuacao.txt"));
while (le.hasNextLine()){
    Usuario usuario = new
usuarios.add(le.nextLine(),le.nextInt());
}
        } catch (Exception ex) {
            System.out.println("Erro ao tentar ler o arquivo");
        }

    }   
      
      
}
