package Xd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class JavaFX extends Application
{
    GridPane buttons = new GridPane();
    GridPane textGrid = new GridPane();
    FileChooserr fileChooserr = new FileChooserr();
    File file;
    String filePath;
    TextArea text1 = new TextArea();
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("UnJar");

        enterButtonsWithProperties(); // display buttons on scene

        StackPane layout = new StackPane();
        // layout.getChildren().add(textGrid); // less important so it is under stack i think
        layout.getChildren().add(buttons);  // more important so I can click button - uneditable textArea is a trick :)

        Scene scene = new Scene(layout, 1400, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void enterButtonsWithProperties(){
        final Button button1 = new Button();
        final Button button2 = new Button();
        final Button button3 = new Button();
        final Button button4 = new Button();
        final Button button5 = new Button();

        button1.setText("Enter a .jar file");
        button2.setText("Explore an entered .jar file");
        button3.setText("Button3");
        button4.setText("Button4");
        button5.setText("Button5");

        button1.prefWidthProperty().bind(buttons.widthProperty());
        button2.prefWidthProperty().bind(buttons.widthProperty());
        button3.prefWidthProperty().bind(buttons.widthProperty());
        button4.prefWidthProperty().bind(buttons.widthProperty());
        button5.prefWidthProperty().bind(buttons.widthProperty());

        button1.setPrefHeight(40);
        button2.setPrefHeight(40);
        button3.setPrefHeight(40);
        button4.setPrefHeight(40);
        button5.setPrefHeight(40);

        buttons.addRow(0, button1, button2, button3, button4, button5);
        buttons.setAlignment(Pos.BOTTOM_LEFT);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    file = fileChooserr.PickMe();
                    filePath = file.getAbsolutePath();
                    System.out.println("enterButtonsWithProperties() filePath: " + filePath);
                    secondStage();

                    System.out.println("Action after click Button1 end"); // debug
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JarFile jarFile = new JarFile();
               // jarFile.deleteOldFile();
                jarFile.openJar(file);
            }
        });
    }

    public void secondStage(){
        StackPane root2 = new StackPane();

  //      ListVieww listVieww = new ListVieww();
  //      HBox hBox = listVieww.listing();
  //      Group root = new Group();
   //     root2.getChildren().addAll(hBox); // dodac tu moze trzecia scene zeby wyswietlac ta liste gdzies na srodku i moc w niej klikac

        enterTextArea();
        root2.getChildren().add(textGrid);
        root2.getChildren().add(buttons);
        Scene secondScene = new Scene(root2, 1400,700);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene); // set the scene
        secondStage.setTitle("UnJar");
        secondStage.setResizable(false);
        secondStage.show();
        primaryStage.close(); // close the first stage (Window)
    }
    public void enterTextArea(){
        text1.setText(filePath);
        System.out.println("enterTextArea() filePath: " + filePath);
        text1.setPrefSize(1399, 20);
        text1.setDisable(true);
        textGrid.addRow(0, text1);
        textGrid.setAlignment(Pos.TOP_CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
