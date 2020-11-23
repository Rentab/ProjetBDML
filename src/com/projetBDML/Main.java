package com.projetBDML;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Main {

    public static void main(String[] args) throws IOException {


        //---------------------------------------------------Variables--------------------------------------------------

        int n = 300;
        ; //dimension
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<String> mots = new ArrayList<String>();
        File file = new File("D:\\Cours\\GE5_S1\\Big Data & ML\\Code\\CorpusVec\\data\\test.txt");


        //------------------------------Recuperer chaque mot et ses valeurs vectorielles associees----------------------

        Scanner scr = null;
        try {
            scr = new Scanner(file).useDelimiter("]");
            while (scr.hasNext()) {
                lines.add(scr.next());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //---------------------------------------Enlever les brackets---------------------------------------------------


        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replaceAll("\\[", ""));
        }

        Writer writer = new FileWriter("D:\\Cours\\GE5_S1\\Big Data & ML\\Code\\CorpusVec\\data\\output.txt");

        for (int i = 0; i < lines.size(); i++) {
            writer.write(lines.get(i));
        }
        writer.close();

        //------------Tout stocker dans des ArrayList pos 0+i mot i pos 1+i-999+i representation Word2Vec---------------

        file = new File("D:\\Cours\\GE5_S1\\Big Data & ML\\Code\\CorpusVec\\data\\output.txt");
        scr = new Scanner(file).useDelimiter("\\s+");

        while (scr.hasNext()) {
            mots.add(scr.next());
        }
        /* visualisation pour bounds array
        for (int i=905;i<=1208;i++) {
            System.out.println(mots.get(i));
        }
        */

        //---------------------------------------------Test avec phrase ------------------------------------------------

        //a recuperer du corpus et a stocker dans des arraylist string puis split et chercher position et sommer
        // de la le et à pour l'instant
        String phrase = "de la";
        double[] poidsphrase = new double[n];
        String[] phraseS = phrase.split("\\s+");

        for (int i = 0; i < phraseS.length; i++) {//on parcourt les mots de la phrase
            for (int j = 0; j < mots.size(); j++) { // on parcourt les mots vectorialises
                if (phraseS[i].equals(mots.get(j))) { //si on trouve une occurrence
                    System.out.println("Mot reconnu "+mots.get(j)+ " en position " +i+ " de la phrase");
                    for (int l = 0; l < n; l++) { //on somme les vecteurs associes a chaque mot dans le vecteur
                        // position mot "x" = j , pos coordonnee 1 = j+1  , cordonnee n j+n+i
                        poidsphrase[l] += Double.valueOf(mots.get(j + l + 1));
                    }
                }
            }
        }
    }
}
