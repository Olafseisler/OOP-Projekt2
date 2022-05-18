package com.example.pjkt;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application{
    public static void main(String[] args){
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Peenar peenar = new Peenar(100,100,0);
        Taim kurk = new Taim("Kurk",7);
        Taim tomat = new Taim("Tomat",7);
        peenar.külva(kurk);
        peenar.külva(tomat);
        peenar.kasvata(); //kui siin korraga kasvatada siis kõik töötab, järjest ei tööta, ei oska kuidagi ekraani värskendada
        List<Taim> taimed = peenar.getTaimed();

        Group group = new Group();
        Scene scene = new Scene(group, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle muld = new Rectangle(1500, 250, Color.BROWN);
        group.setLayoutX(200);
        group.setLayoutY(650);

        Button külva = new Button("Külva");
        külva.setLayoutX(100);
        külva.setLayoutY(275);
        külva.setOnAction(actionEvent -> {
            //TODO küsida mida külvata
            System.out.println(peenar.toString());
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
        kasta.setOnAction(actionEvent -> {
            peenar.väeta();
        });
        Button rohi = new Button("Rohi");
        rohi.setLayoutX(250);
        rohi.setLayoutY(275);
        kasta.setOnAction(actionEvent -> {
            peenar.rohi();
        });
        Button lõika = new Button("Lõika");
        lõika.setLayoutX(300);
        lõika.setLayoutY(275);
        lõika.setOnAction(actionEvent -> {
            peenar.lõika();
        });

        group.getChildren().addAll(muld, külva, kasta, väeta, rohi, lõika);

        for (int i = 0; i < taimed.size(); i++) {
            System.out.println(taimed.get(i).toString());
            Line istik = new Line((i + 1) * 100, -25 * (taimed.get(i).getKõrgus()), (i + 1) * 100, 0);
            group.getChildren().add(istik);
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //parempool
                if (taimed.get(i).getParempool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 + 10, (j + 20) * -j - 10, (i + 1) * 100, (j + 20) * -j);
                    group.getChildren().add(leht);
                    if (taimed.get(i).getParempool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 + 10, (j + 20) * -j - 10, 2, 2);
                        group.getChildren().add(vili);
                    }
                }
            }
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //vasak
                if (taimed.get(i).getVasakpool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 - 10, (j + 23) * -j - 10, (i + 1) * 100, (j + 23) * -j);
                    group.getChildren().add(leht);
                    if (taimed.get(i).getVasakpool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 - 10, (j + 23) * -j - 10, 2, 2);
                        group.getChildren().add(vili);
                    }
                }
            }
        }
    }
}
