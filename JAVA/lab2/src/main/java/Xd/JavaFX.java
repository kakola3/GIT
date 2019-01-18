package Xd;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaFX extends Application
{
    GridPane buttons = new GridPane();
    GridPane textGrid = new GridPane();
    FileChooserr fileChooserr = new FileChooserr();
    public static File file;
    String filePath;
    TextArea text1 = new TextArea();
    Stage primaryStage;
    Stage secondStage = new Stage();
    Stage thirdStage = new Stage();

    ListView classesView = new ListView();

    ListView packagesView = new ListView();
    ListView methodsView = new ListView();
    ListView constructorView = new ListView();
    ListView fieldsView = new ListView();

    JarFilee jarFilee;

    public static ArrayList<String> methodsList;


    public JavaFX() throws IOException {
    }

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
      //  final Button button4 = new Button();
      //  final Button button5 = new Button();

        button1.setText("Enter a .jar file");
        button2.setText("Explore an entered .jar file");
        button3.setText("Save the modified .jar file");
        //button4.setText("Button4");
        //button5.setText("Button5");

        button1.prefWidthProperty().bind(buttons.widthProperty());
        button2.prefWidthProperty().bind(buttons.widthProperty());
        button3.prefWidthProperty().bind(buttons.widthProperty());
        //button4.prefWidthProperty().bind(buttons.widthProperty());
        //button5.prefWidthProperty().bind(buttons.widthProperty());

        button1.setPrefHeight(40);
        button2.setPrefHeight(40);
        button3.setPrefHeight(40);
        //button4.setPrefHeight(40);
        //button5.setPrefHeight(40);

        buttons.addRow(0, button1, button2, button3);//, button4, button5);
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
                JarFilee jarFile = new JarFilee();
               // jarFile.deleteOldFile();
                try {
                    jarFile.openJar(file);
                    secondStage();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void secondStage() throws IOException {

        primaryStage.close(); // close the first stage (Window)
        final ObservableList<String> classesList = FXCollections.observableArrayList(JarFilee.onlyClassesArray);
        classesView.setItems(classesList);
        classesView.setPrefSize(600,660);
        classesView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String string = (String) classesView.getSelectionModel().getSelectedItem();
                System.out.println("Pobrana klasa: " + string);
                try {
                    JarFilee.onlyMethods(string);
                    methodsList = JarFilee.onlyMethods(string);
                    //methodsView.setItems(methodList);
                    System.out.println("Metody pobrane z jara dla klasy: " + jarFilee.onlyMethods(string));
                    thirdStage();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        packagesView.setPrefSize(600, 160);
        methodsView.setPrefSize(600,160);
        constructorView.setPrefSize(600, 160);
        fieldsView.setPrefSize(600,160);
        HBox hBoxWithoutButtons = new HBox();
        hBoxWithoutButtons.setPrefSize(1400,660);
        hBoxWithoutButtons.setAlignment(Pos.TOP_CENTER);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        VBox jarOverview = new VBox(classesView); // classesView
        jarOverview.setPrefSize(700, 660);
        jarOverview.setAlignment(Pos.CENTER_LEFT);
        VBox restDesignStuff = new VBox();
        restDesignStuff.setPrefSize(700, 660);
        restDesignStuff.setAlignment(Pos.CENTER_RIGHT);
        HBox packagesOverview = new HBox(packagesView);
        packagesOverview.setAlignment(Pos.BASELINE_RIGHT);
        packagesOverview.setPrefSize(500,160);
        HBox methodsOverview = new HBox(methodsView);
        methodsOverview.setAlignment(Pos.BASELINE_RIGHT);
        methodsOverview.setPrefSize(500,160);
        HBox propertiesOverview = new HBox(fieldsView);
        propertiesOverview.setAlignment(Pos.BASELINE_RIGHT);
        propertiesOverview.setPrefSize(500,160);
        HBox constructorsOverview = new HBox(constructorView);
        constructorsOverview.setAlignment(Pos.BASELINE_RIGHT);
        constructorsOverview.setPrefSize(500,160);
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(600, 20);
        label.setText(filePath);
        label.setDisable(true);

        restDesignStuff.getChildren().add(label);
        restDesignStuff.getChildren().add(packagesOverview);
        restDesignStuff.getChildren().add(methodsOverview);
        restDesignStuff.getChildren().add(propertiesOverview);
        restDesignStuff.getChildren().add(constructorsOverview);

        hBoxWithoutButtons.getChildren().add(jarOverview);
        hBoxWithoutButtons.getChildren().add(restDesignStuff);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBoxWithoutButtons);
        vBox.getChildren().add(buttons);

        Scene scene = new Scene(vBox, 1400, 700);

        //Stage secondStage = new Stage();

        secondStage.setTitle("SecondStage");

        secondStage.setScene(scene); // set the scene
        secondStage.setTitle("UnJar");
        secondStage.setResizable(false);
        secondStage.show();
    }

    public void thirdStage(){
        secondStage.close(); // close the first stage (Window)
        final ObservableList<String> classesList = FXCollections.observableArrayList(JarFilee.onlyClassesArray);
        final ObservableList<String> methodList = FXCollections.observableArrayList(methodsList);
        System.out.println("metodList: " + methodList);
        classesView.setItems(classesList);
        classesView.setPrefSize(600,660);
//        classesView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                String string = (String) classesView.getSelectionModel().getSelectedItem();
//                System.out.println("Pobrana klasa: " + string);
//                try {
//                    JarFilee.onlyMethods(string);
//                    methodsList = JarFilee.onlyMethods(string);
//                    //methodsView.setItems(methodList);
//                    System.out.println("Metody pobrane z jara dla klasy: " + jarFilee.onlyMethods(string));
//                } catch (NotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

        packagesView.setPrefSize(600, 160);
        methodsView.setItems(methodList);
        System.out.println("metody: " + methodList);
        methodsView.setPrefSize(600,160);
        constructorView.setPrefSize(600, 160);
        fieldsView.setPrefSize(600,160);
        HBox hBoxWithoutButtons = new HBox();
        hBoxWithoutButtons.setPrefSize(1400,660);
        hBoxWithoutButtons.setAlignment(Pos.TOP_CENTER);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        VBox jarOverview = new VBox(classesView); // classesView
        jarOverview.setPrefSize(700, 660);
        jarOverview.setAlignment(Pos.CENTER_LEFT);
        VBox restDesignStuff = new VBox();
        restDesignStuff.setPrefSize(700, 660);
        restDesignStuff.setAlignment(Pos.CENTER_RIGHT);
        HBox packagesOverview = new HBox(packagesView);
        packagesOverview.setAlignment(Pos.BASELINE_RIGHT);
        packagesOverview.setPrefSize(500,160);
        HBox methodsOverview = new HBox(methodsView);
        methodsOverview.setAlignment(Pos.BASELINE_RIGHT);
        methodsOverview.setPrefSize(500,160);
        HBox propertiesOverview = new HBox(fieldsView);
        propertiesOverview.setAlignment(Pos.BASELINE_RIGHT);
        propertiesOverview.setPrefSize(500,160);
        HBox constructorsOverview = new HBox(constructorView);
        constructorsOverview.setAlignment(Pos.BASELINE_RIGHT);
        constructorsOverview.setPrefSize(500,160);
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(600, 20);
        label.setText(filePath);
        label.setDisable(true);

        restDesignStuff.getChildren().add(label);
        restDesignStuff.getChildren().add(packagesOverview);
        restDesignStuff.getChildren().add(methodsOverview);
        restDesignStuff.getChildren().add(propertiesOverview);
        restDesignStuff.getChildren().add(constructorsOverview);

        hBoxWithoutButtons.getChildren().add(jarOverview);
        hBoxWithoutButtons.getChildren().add(restDesignStuff);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBoxWithoutButtons);
        vBox.getChildren().add(buttons);

        Scene scene = new Scene(vBox, 1400, 700);

        thirdStage.setTitle("Third Stage");

        thirdStage.setScene(scene); // set the scene
        thirdStage.setTitle("UnJar");
        thirdStage.setResizable(false);
        thirdStage.show();
    }
    public void enterTextArea(){
        text1.setText(filePath);
        System.out.println("enterTextArea() filePath: " + filePath);
        text1.setPrefSize(200, 20);
        text1.setDisable(true);
        textGrid.addRow(0, text1);
        textGrid.setAlignment(Pos.TOP_RIGHT);
    }

    private void setConfigurationForGridPane(GridPane gridPane) {
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column1.setPercentWidth(10);
        column2.setPercentWidth(10);
        gridPane.getColumnConstraints().addAll(column1, column2);
    }



    private VBox getMenuBarWithVBox(MenuBar menuBar) {
        return new VBox(menuBar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
