package com.example.pjkt;

import java.util.ArrayList;
import java.util.List;

public class Peenar {
    private int toitained;
    private int niiskus;
    private int umbrohi;
    private List<Taim> taimed;

    public Peenar(int toitained, int niiskus, int umbrohi) {
        this.toitained = toitained;
        this.niiskus = niiskus;
        this.umbrohi = umbrohi;
        this.taimed = new ArrayList<Taim>();
    }

    public boolean kasKasvab(){
        return 10 * Math.random() * (niiskus/100.0) * (toitained/100.0) > 0.3;
    }

    public void kasvata(){
        if (kasKasvab())
        {
            for (Taim taim : taimed) {
                taim.kasva();
            }
            Main.kasvataGraafika(this);
            tarbiToitaineid();
            kasvataUmbrohtu();
        }
        kuiva();


        System.out.println(this.toString());
    }

    public void külva(Taim taim){
        if (taimed.size() < 14) {
            taimed.add(taim);
        }
        kasvata();
    }

    /**
     * Kasta peenart
     */
    public void kasta() {
        niiskus = Math.min(niiskus + 10, 100);
        kasvata();
    }

    /**
     * Väeta peenart
     */
    public void väeta() {
        toitained = Math.min(toitained + 10, 100);
        kasvata();
    }

    /*
     * Eemalda umbrohtu
     */
    public void rohi() {
        umbrohi = Math.max(0, umbrohi - 10);
        kasvata();
    }

    /**
     * Korja saak taimelt ning eemalda see potist
     */
    public void lõika() {
        if (taimed.size() == 0) return;
        //Main.lisaSaak(taim.getNimetus(), taim.getSaak());
        taimed.remove(taimed.get(0));
        kasvata();
    }

    public int getToitained() {
        return toitained;
    }

    public int getNiiskus() {
        return niiskus;
    }

    public int getUmbrohi() {
        return umbrohi;
    }

    public List<Taim> getTaimed() {
        return taimed;
    }

    /**
     * Muld kuivab kui seda mitte kasta
     */
    public void kuiva() {
        niiskus = Math.max(0, niiskus - (int)(10 * Math.random()));
    }

    /**
     * Taim tarbib toitaineid
     */
    public void tarbiToitaineid() {
        toitained = Math.max(0, toitained - (int)(10 * Math.random()));
    }

    /**
     * Kasvata umbrohtu peenrale
     */
    public void kasvataUmbrohtu() {
        umbrohi = Math.min(umbrohi + (int)(5 * Math.random()), 100);
    }


    @Override
    public String toString() {
        return "Peenar{" +
                "toitained=" + toitained +
                ", niiskus=" + niiskus +
                ", umbrohi=" + umbrohi +
                ", taimed=" + taimed +
                '}';
    }
}
