package com.projetBDML;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Main {

    public static void main(String[] args) throws IOException {


        //---------------------------------------------------Variables--------------------------------------------------

        int n = 300;
        ; //dimension
        ArrayList<String> lines = new ArrayList<String>();
        //List<ArrayList<String>> mots = new ArrayList<ArrayList<String>>();
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

        ArrayList<ArrayList<String>> mots = new ArrayList<ArrayList<String>>();
        for (int i=0;i<lines.size();i++) {
            List<String> temp = Arrays.asList(lines.get(i).split("\\s+"));
            ArrayList<String> temp1 = new ArrayList<String>();
            temp1.addAll(temp);
            if (i==0){
            }
            else{
                temp1.remove(0);
            }
            mots.add(temp1);
        }

        //Structure mots : [0 , mot , x1 , ... , x300 ]
        //                  0   1      2          301

        //---------------------------------------------Test avec phrase ------------------------------------------------

        //a recuperer du corpus et a stocker dans des arraylist string puis split et chercher position et sommer
        // de la le et à pour l'instant
        String phrase = "de la";
        double[] poidsphrase = new double[n];
        String[] phraseS = phrase.split("\\s+");


        for (int i = 0; i < phraseS.length; i++) {//on parcourt les mots de la phrase
            for (int j = 0; j < mots.size(); j++) { // on parcourt les mots vectorialises
                if (phraseS[i].equals(mots.get(j).get(1))) { //si on trouve une occurrence
                    System.out.println("Mot reconnu "+mots.get(j).get(1)+ " en position " +i+ " de la phrase");
                    for (int l = 0; l < n; l++) { //on somme les vecteurs associes a chaque mot dans le vecteur
                         //on ajoute dans le poids de la phrase les valeurs pour chaque mot reconnu
                         poidsphrase[l] += Double.valueOf(mots.get(j).get(l+2));
                    }
                }
            }
        }

    }
}
