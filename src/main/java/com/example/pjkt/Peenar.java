package com.example.pjkt;

import java.util.ArrayList;
import java.util.List;

public class Peenar {
    private int toitained;
    private int niiskus;
    private int umbrohi;
    private List<Taim> taimed = new ArrayList<>();

    public Peenar(int toitained, int niiskus, int umbrohi) {
        this.toitained = toitained;
        this.niiskus = niiskus;
        this.umbrohi = umbrohi;
    }

    public void kasvata(){
        boolean kasKasvab = true; //TODO
        if (kasKasvab){
            for(Taim taim : taimed){
                taim.kasva();
            }
        }
    }

    public void külva(Taim taim){
        try {
            if (taimed.size() < 14) {
                taimed.add(taim);
            }
        } catch (Exception e) {
            taimed.add(taim);
        }
    }

    public void kasta() {
        //TODO
        kasvata();
    }

    public void väeta() {
        //TODO
        kasvata();
    }

    public void rohi() {
        //TODO
        kasvata();
    }

    public void lõika() {
        //TODO
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
