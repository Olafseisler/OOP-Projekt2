package com.example.pjkt;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class Main extends Application{

    public static Group group;
    static Pane pane = new Pane();
    private static String[] taimeSordid = new String[] {"hernes", "tomat", "kurk", "petersell", "uba"};

    public static void main(String[] args){
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Peenar peenar = new Peenar(100,100,0);
        group = new Group();

        List<Taim> taimed = peenar.getTaimed();

        Scene scene = new Scene(group, Color.WHITE);
        primaryStage.setScene(scene);

        Rectangle muld = new Rectangle(1500, 250, Color.BROWN);
        group.setLayoutX(200);
        group.setLayoutY(650);

        Button külva = new Button("Külva");
        külva.setLayoutX(100);
        külva.setLayoutY(275);
        külva.setOnAction(actionEvent -> {
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
        group.getChildren().addAll(muld, külva, kasta, väeta, rohi, lõika,pane);

        primaryStage.show();
    }

    public static void kasvataGraafika(Peenar peenar){
        List<Taim> taimed = peenar.getTaimed();
        pane.getChildren().clear();

        for (int i = 0; i < taimed.size(); i++) {
            if (taimed.get(i) == null) continue;
            //System.out.println(taimed.get(i).toString());
            Line istik = new Line((i + 1) * 100, -25 * (taimed.get(i).getKõrgus()), (i + 1) * 100, 0);
            pane.getChildren().add(istik);
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //parempool
                if (taimed.get(i).getParempool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 + 10, (j + 20) * -j - 10, (i + 1) * 100, (j + 20) * -j);
                    pane.getChildren().add(leht);
                    if (taimed.get(i).getParempool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 + 10, (j + 20) * -j - 10, 2, 2);
                        pane.getChildren().add(vili);
                    }
                }
            }
            for (int j = 0; j < taimed.get(i).getKõrgus(); j++) { //vasak
                if (taimed.get(i).getVasakpool().get(j).get(0)) {
                    Line leht = new Line((i + 1) * 100 - 10, (j + 23) * -j - 10, (i + 1) * 100, (j + 23) * -j);
                    pane.getChildren().add(leht);
                    if (taimed.get(i).getVasakpool().get(j).get(1)) {
                        Ellipse vili = new Ellipse((i + 1) * 100 - 10, (j + 23) * -j - 10, 2, 2);
                        pane.getChildren().add(vili);
                    }
                }
            }
        }
    }

    public static String küsiSisend(){
        TilePane tilePane = new TilePane();
        TextInputDialog textInputDialog = new TextInputDialog("");
        textInputDialog.setHeaderText("Sisestage soovitud taim" + "\n" + "hernes,tomat,kurk,petersell,uba");
        textInputDialog.setTitle("Sisend");
        Optional<String> valik = textInputDialog.showAndWait();
        group.getChildren().add(tilePane);
        if (valik.isPresent()){
            return valik.get();
        }
        else return "välju";
    }

    /**
     * Kontrollib kasutajalt saadud sisendit ning sooritab antud sõnale sarnaseima tegevuse
     */
    public static void sisendiKontroll(Peenar peenar) {

        boolean running = true;
        while(running) {
            String vastus = küsiSisend();
            try {
                switch (vastus) {
                    case "välju":
                        running = false;
                        break;
                    case "hernes":
                        peenar.külva(new Taim("Hernes", 9));
                        running = false;
                        break;
                    case "tomat":
                        peenar.külva(new Taim("Hernes", 4));
                        running = false;
                        break;
                    case "kurk":
                        peenar.külva(new Taim("Hernes", 7));
                        running = false;
                        break;
                    case "petersell":
                        peenar.külva(new Taim("Hernes", 6));
                        running = false;
                        break;
                    case "uba":
                        peenar.külva(new Taim("Hernes", 10));
                        running = false;
                        break;
                    default:
                        throw new VigaseNimetuseErind("Tundmatu taim");
                }
            } catch (VigaseNimetuseErind e) {
                Alert erind = new Alert(Alert.AlertType.ERROR);
                erind.setHeaderText("Tundmatu taim");
                erind.showAndWait();
            }
        }
    }
}
