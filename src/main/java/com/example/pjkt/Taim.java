package com.example.pjkt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Taim {

    private String nimetus;
    private int kõrgus = 1;
    private int maxKõrgus; //absoluutne, ei tohi suurendada
    private List<List<Boolean>> parempool = new ArrayList<>();
    private List<List<Boolean>> vasakpool = new ArrayList<>();

    public Taim(String nimetus, int maxKõrgus) {
        this.nimetus = nimetus;
        this.maxKõrgus = maxKõrgus;
        parempool.add(Arrays.asList(true,false)); //kõige alumisel varrel on alati lehed aga kunagi ei ole vilja
        vasakpool.add(Arrays.asList(true,false));
    }

    public static boolean randomBoolean(int tõenäosus){
        Random random = new Random();
        boolean bool = random.nextInt(100) < tõenäosus;
        return bool;
    }

    public void kasva(){
        if(kõrgus < maxKõrgus){
            parempool.add(Arrays.asList(randomBoolean(80),randomBoolean(60))); //siin saab muuta kui suure tõenäosusega leht ja vili kasvab (80%/60%) //TODO väetis,niiskus
            vasakpool.add(Arrays.asList(randomBoolean(80),randomBoolean(60)));
            kõrgus += 1;
        }
    }

    public String getNimetus() {
        return nimetus;
    }

    public int getKõrgus() {
        return kõrgus;
    }

    public List<List<Boolean>> getParempool() {
        return parempool;
    }

    public List<List<Boolean>> getVasakpool() {
        return vasakpool;
    }

    @Override
    public String toString() {
        return "Taim{" +
                "nimetus='" + nimetus + '\'' +
                ", kõrgus=" + kõrgus +
                ", maxKõrgus=" + maxKõrgus +
                ", parempool=" + parempool +
                ", vasakpool=" + vasakpool +
                '}';
    }
}
