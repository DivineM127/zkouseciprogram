/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.divinem.zkouseciprogram.predmet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author novos
 */
public class Otazka {
    private Map<MoznostOdpovedi, String> dostupneOdpovedi = new HashMap<>();
    private int cisloOtazky;
    private String otazka;
    private String odpovedA;
    private String odpovedB;
    private String odpovedC;
    private String odpovedD;
    private MoznostOdpovedi spravnaOdpoved;
    
    public String getOdpovedA() {
        return odpovedA;
    }

    public void setOdpovedA(String odpovedA) {
        this.odpovedA = odpovedA;
    }
    
    public String getOdpovedB() {
        return odpovedB;
    }

    public void setOdpovedB(String odpovedB) {
        this.odpovedB = odpovedB;
    }

    public String getOdpovedC() {
        return odpovedC;
    }

    public void setOdpovedC(String odpovedC) {
        this.odpovedC = odpovedC;
    }

    public String getOdpovedD() {
        return odpovedD;
    }

    public void setOdpovedD(String odpovedD) {
        this.odpovedD = odpovedD;
    }

    public int getCisloOtazky() {
        return cisloOtazky;
    }

    public void setCisloOtazky(int cisloOtazky) {
        this.cisloOtazky = cisloOtazky;
    }

    public String getOtazka() {
        return otazka;
    }

    public void setOtazka(String otazka) {
        this.otazka = otazka;
    }

    public Map<MoznostOdpovedi, String> getDostupneOdpovedi() {
        return dostupneOdpovedi;
    }

    public void pridatOdpoved(MoznostOdpovedi moznostOdpovedi, String odpoved) {
        dostupneOdpovedi.put(moznostOdpovedi, otazka);
    }
    public MoznostOdpovedi getSpravnaOdpoved() {
        return spravnaOdpoved;
    }

    public void setSpravnaOdpoved(MoznostOdpovedi spravnaOdpoved) {
        this.spravnaOdpoved = spravnaOdpoved;
    }

    @Override
    public String toString() {
        return "Otazka{" + "dostupneOdpovedi=" + dostupneOdpovedi + ", cisloOtazky=" + cisloOtazky + ", otazka=" + otazka + ", spravnaOdpoved=" + spravnaOdpoved + '}';
    }
    
}
