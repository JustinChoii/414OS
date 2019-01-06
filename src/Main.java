import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import java.awt.event.WindowEvent;
import javafx.application.Platform;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
    public static Button button = new Button("Start!");
    public static Stage window;
    public static Scene scene;
    public static GridPane pane = new GridPane();
    public static ChoiceBox<String> speedBox = new ChoiceBox<>();
    public static Circle d1 = new Circle(70, Color.RED);
    public static Circle d2 = new Circle(70, Color.RED);
    public static Rectangle u1 = new Rectangle(170, 140, Color.RED);
    public static Rectangle u2 = new Rectangle(170, 140, Color.RED);
    public static Rectangle u3 = new Rectangle(170, 140, Color.RED);
    public static Rectangle u4 = new Rectangle(170, 140, Color.RED);
    public static Rectangle p1 = new Rectangle(170, 140, Color.RED);
    public static Rectangle p2 = new Rectangle(170, 140, Color.RED);
    public static Rectangle p3 = new Rectangle(170, 140, Color.RED);
    public static Circle d[] = {d1, d2};
    public static Rectangle u[] = {u1, u2, u3, u4};
    public static Rectangle p[] = {p1, p2, p3};
    private static final int NUMBER_OF_USERS = 4;
    private static final int NUMBER_OF_DISKS = 2;
    private static final int NUMBER_OF_PRINTERS = 3;
    public static double newSpeed = 1.0;

    public static Disk disk1 = new Disk();
    public static Text textd1 = new Text("");
    public static Disk disk2 = new Disk();
    public static Text textd2 = new Text("");
    public static Disk[] Disks = {disk1, disk2};
    public static Printer printer1 = new Printer(1);
    public static Text textp1 = new Text("");
    public static Printer printer2 = new Printer(2);
    public static Text textp2 = new Text("");
    public static Printer printer3 = new Printer(3);
    public static Text textp3 = new Text("");
    public static Printer[] Printers = {printer1, printer2, printer3};
    public static UserThread User1 = new UserThread(1);
    public static Text textu1 = new Text("");
    public static UserThread User2 = new UserThread(2);
    public static Text textu2 = new Text("");
    public static UserThread User3 = new UserThread(3);
    public static Text textu3 = new Text("");
    public static UserThread User4 = new UserThread(4);
    public static Text textu4 = new Text("");
    public static Text[] tu = {textu1, textu2, textu3, textu4};
    public static Text[] td = {textd1, textd2};
    public static Text[] tp = {textp1, textp2, textp3};

    public static UserThread[] Users = {User1, User2, User3, User4};


    public static final DiskManager DiskM = new DiskManager();
    public static final ResourceManager DiskRM = new ResourceManager(NUMBER_OF_DISKS);
    public static final ResourceManager PrinterRM = new ResourceManager(NUMBER_OF_PRINTERS);

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("141os Simulation");
        Text text1 = new Text("Users");
        Text text2 = new Text("Disks");
        Text text3 = new Text("Printers");





        speedBox.getItems().addAll("0.25x", "0.5x", "1x", "2x", "4x");
        speedBox.setValue("1x");



        speedBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue == "1x"){

            }
            else{
                newValue = newValue.replaceAll("x", "");
                double temp = Double.parseDouble(newValue);
                this.newSpeed = temp;
            }


        });




        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setVgap(40);
        speedBox.setTranslateY(-60);
        button.setTranslateY(-210);

        text1.setTranslateX(66);
        text2.setTranslateX(55);
        text3.setTranslateX(66);
        text1.setTranslateY(-80);
        text2.setTranslateY(-80);
        text3.setTranslateY(-80);
        textu1.setTranslateX(20);
        textu2.setTranslateX(20);
        textu3.setTranslateX(20);
        textu4.setTranslateX(20);
        textp1.setTranslateX(20);
        textp2.setTranslateX(20);
        textp3.setTranslateX(20);
        textd1.setTranslateX(25);
        textd2.setTranslateX(25);



        pane.setHgap(100);
        pane.add(speedBox, 3, 0);
        pane.add(button, 3, 1);
        pane.add(d1, 1, 0);
        pane.add(d2, 1, 1);
        pane.add(u1, 0, 0);
        pane.add(u2, 0, 1);
        pane.add(u3, 0, 2);
        pane.add(u4, 0, 3);
        pane.add(p1, 2, 0);
        pane.add(p2, 2, 1);
        pane.add(p3, 2, 2);
        pane.add(text1, 0, 0);
        pane.add(text2, 1, 0);
        pane.add(text3, 2, 0);
        pane.add(textd1, 1, 0);
        pane.add(textd2, 1, 1);
        pane.add(textp1, 2, 0);
        pane.add(textp2, 2, 1);
        pane.add(textp3, 2, 2);
        pane.add(textu1, 0, 0);
        pane.add(textu2, 0, 1);
        pane.add(textu3, 0 ,2);
        pane.add(textu4, 0, 3);




        Scene scene = new Scene(pane, 900, 743);
        window.setScene(scene);
        window.show();
        button.setOnAction(e ->{
            UserThread User1 = new UserThread(1);
            UserThread User2 = new UserThread(2);
            UserThread User3 = new UserThread(3);
            UserThread User4 = new UserThread(4);
            UserThread[] Users = {User1, User2, User3, User4};
            User1.start();
            User2.start();
            User3.start();
            User4.start();
        });
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

}