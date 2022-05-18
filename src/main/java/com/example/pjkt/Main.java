package com.example.pjkt;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;

public class Main extends Application{

    public static Group groupTaimed;
    public static Group groupTaust;
    private static String[] taimeSordid = new String[] {"hernes", "tomat", "kurk", "petersell", "uba"};

    public static void main(String[] args){
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Peenar peenar = new Peenar(100,100,0);
//        Taim kurk = new Taim("Kurk",7);
//        Taim tomat = new Taim("Tomat",7);
        groupTaimed = new Group();
        groupTaust = new Group();

//        peenar.külva(kurk);
//        peenar.külva(tomat);

        List<Taim> taimed = peenar.getTaimed();

        Scene scene1 = new Scene(groupTaust, Color.WHITE);
        primaryStage.setScene(scene);

        Rectangle muld = new Rectangle(1500, 250, Color.BROWN);
        groupTaust.setLayoutX(200);
        groupTaust.setLayoutY(650);

        Button külva = new Button("Külva");
        külva.setLayoutX(100);
        külva.setLayoutY(275);
        külva.setOnAction(actionEvent -> {
            //TODO küsida mida külvata
            sisendiKontroll(peenar);
        });
        Button kasta = new Button("Kasta");
        kasta.setLayoutX(150);
        kasta.setLayoutY(275);
        kasta.setOnAction(actionEvent -> {
            peenar.kasta();

        });
        Button väeta = new Button("Väeta");
        väeta.setLayoutX(200);
        väeta.setLayoutY(275);
        väeta.setOnAction(actionEvent -> {
            peenar.väeta();
        });
        Button rohi = new Button("Rohi");
        rohi.setLayoutX(250);
        rohi.setLayoutY(275);
        rohi.setOnAction(actionEvent -> {
            peenar.rohi();
        });
        Button lõika = new Button("Lõika");
        lõika.setLayoutX(300);
        lõika.setLayoutY(275);
        lõika.setOnAction(actionEvent -> {
            peenar.lõika();
        });

        groupTaust.getChildren().addAll(muld, külva, kasta, väeta, rohi, lõika);

        primaryStage.setScene(groupTaust);
        primaryStage.show();
    }

    public static void kasvataGraafika(Peenar peenar){
        List<Taim> taimed = peenar.getTaimed();
        groupTaimed.getChildren().clear();

        for (int i = 0; i < taimed.size(); i++) {
            if (taimed.get(i) == null) continue;
            //System.out.println(taimed.get(i).toString());
            Line istik = new Line((i + 1) * 100, -25 * (taimed.get(i).getKõrgus()), (i + 1) * 100, 0);
            groupTaimed.getChildren().add(istik);
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //parempool
                if (taimed.get(i).getParempool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 + 10, (j + 20) * -j - 10, (i + 1) * 100, (j + 20) * -j);
                    groupTaimed.getChildren().add(leht);
                    if (taimed.get(i).getParempool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 + 10, (j + 20) * -j - 10, 2, 2);
                        groupTaimed.getChildren().add(vili);
                    }
                }
            }
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //vasak
                if (taimed.get(i).getVasakpool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 - 10, (j + 23) * -j - 10, (i + 1) * 100, (j + 23) * -j);
                    groupTaimed.getChildren().add(leht);
                    if (taimed.get(i).getVasakpool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 - 10, (j + 23) * -j - 10, 2, 2);
                        groupTaimed.getChildren().add(vili);
                    }
                }
            }
        }
    }

    /**
     * Kontrollib kasutajalt saadud sisendit ning sooritab antud sõnale sarnaseima tegevuse
     */
    public static void sisendiKontroll(Peenar peenar) {
        // Loo scanner
        Scanner sc = new Scanner(System.in);
        boolean protsess = true;
        String sarnaseimTaim = "";
        int taim = 0;

        // Küsi kuni saad sobiva vastuse
        while (protsess) {
            System.out.println("Sisesta taim mida soovid istutada: ");
            String[] vastus = sc.nextLine().toLowerCase().split(" ");

            tagasi: // Label kust loopidest välja saab
            for (String s : taimeSordid) {
                int sarnasusi = 0;
                for (int j = 0; j < s.length(); j++) {
                    // Kui sõna on piisavalt sarnane antud taimega, murra vali see ja murra välja
                    if (sarnasusi >= 3) {
                        sarnaseimTaim = s;
                        protsess = false;
                        break tagasi; // Murra kahest loopist välja
                    }

                    // Kui kasutaja antud sõnas on sarnane täht, lisa sarnasuspunkte
                    if (vastus[0].contains(s.substring(j, j + 1))) {
                        sarnasusi++;
                    }
                }
            }

            // Kui sõnal pole piisavalt sarnasusi, küsi uuesti
            if (sarnaseimTaim.equals("")) {
                System.out.println("Ei tea soovitud taime. Proovi uuesti ");
            }
        }

        System.out.println("Istutan taime: " + sarnaseimTaim);
        switch(sarnaseimTaim) {
            case "hernes" :
                peenar.külva(new Taim("Hernes", 9));
                break;
            case "tomat" :
                peenar.külva(new Taim("Hernes", 4));
                break;
            case "kurk" :
                peenar.külva(new Taim("Hernes", 7));
                break;
            case "petersell" :
                peenar.külva(new Taim("Hernes", 6));
                break;
            case "uba" :
                peenar.külva(new Taim("Hernes", 10));
                break;

        }

    }
}
