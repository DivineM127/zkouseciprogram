/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.divinem.zkouseciprogram.predmet;

/**
 *
 * @author novos
 */
public enum Predmet {
    DAK("Datové komunikace", "seznamy/otazky.txt"),
    POS("Počítačové systémy", "seznamy/otazky.txt"),
    ALP("Algoritizace a programování", "seznamy/otazky.txt"),
    APL("Aplikace na počítači", "seznamy/otazky.txt"),
    GRA("Grafika", "seznamy/otazky.txt");
 
    private String jmeno = "";
    private String jmenoSouboru;
    
    Predmet(String jmeno, String jmenoSouboru) {
        this.jmeno = jmeno;
        this.jmenoSouboru = jmenoSouboru;
    }
    
    public String getJmeno() {
        return jmeno;
    }
    
    public String getJmenoSouboru() {
        return jmenoSouboru;
    }
    
    public static Predmet getPredmet(String jmeno) {
        for (Predmet predmet : Predmet.values()) {
            System.out.println("Ted mam predmet: " + predmet);
            if (predmet.getJmeno().equals(jmeno)) {
                System.out.println("Nasla jsem predmet:  " + predmet);
                return predmet;
            }
        }
        
        System.out.println("Nenasla jsem zadny predmet, vracim nekonecnou prazdnotu.");
        return null;
    }
}
