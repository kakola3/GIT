package Xd;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaFX extends Application
{
    FileChooser fileChooserToSave = new FileChooser();
    GridPane buttons = new GridPane();
    GridPane textGrid = new GridPane();
    FileChooserr fileChooserr = new FileChooserr();
    public static File file;
    String filePath;
    TextArea text1 = new TextArea();
    Stage primaryStage;
    Stage secondStage = new Stage();
    public static Stage thirdStage = new Stage();

    public static String chosenPackageForRemove;
    String chosenClassForRemove;
    String chosenMethodForRemove;
    String chosenFieldForRemove;
    String chosenConstructorForRemove;

    ListView classesView = new ListView();

    ListView packageView = new ListView();
    static ListView methodsView = new ListView();
    ListView constructorView = new ListView();
    ListView fieldsView = new ListView();

    JarFilee jarFilee;

    public static ArrayList<String> methodsList; // wrote at the beginning
    //public static ArrayList<String> methodsListToSave; // prepared to save as new .jar
    public static ArrayList<String> constructorsList;
    public static ArrayList<String> fieldsList;
    public static String packagesList;


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
        //button4.setText("RemoveClass");
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

        buttons.addRow(0, button1, button2, button3);//, button4);//, button5);
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

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saving the jar...");
                fileChooserToSave.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
                File fileToSave = fileChooserToSave.showSaveDialog(thirdStage);
                List<String> allClasses = JarFilee.allFiles;
                if (FileSaver.saveJar(fileToSave, allClasses)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully saved a jar", "");
                }
            }
        });

//        button4.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                ClassOperation.removeChosenClass(chosenClassForRemove);
//                thirdStage.close();
//                thirdStage.show();
//            }
//        });

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
                    JarFilee.getConstructorsFromClass(string);
                    JarFilee.getFieldsFromClass(string);
                    JarFilee.showPackage(string);
                    methodsList = JarFilee.onlyMethods(string);
                    constructorsList = JarFilee.getConstructorsFromClass(string);
                    fieldsList = JarFilee.getFieldsFromClass(string);
                    packagesList = JarFilee.showPackage(string);
                    //methodsView.setItems(methodList);
                    System.out.println("Metody pobrane z jara dla klasy: " + jarFilee.onlyMethods(string));
                    chosenClassForRemove = string;
                    System.out.println("chosenClassForRemove: " + chosenClassForRemove);
                    thirdStage();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        packageView.setPrefSize(600, 160);
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
        HBox packagesOverview = new HBox(packageView);
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
        final ObservableList<String> constructorList = FXCollections.observableArrayList(constructorsList);
        final ObservableList<String> fieldList = FXCollections.observableArrayList(fieldsList);
        final ObservableList<String> packageList = FXCollections.observableArrayList(packagesList);
        System.out.println("metodList: " + methodList);
        System.out.println("constructorList: " + constructorList);
        System.out.println("fieldsList: " + fieldsList);
        classesView.setItems(classesList);
        classesView.setPrefSize(600,660);

        packageView.setItems(packageList);
        System.out.println("package: " + packageList);
        packageView.setPrefSize(600, 160);
        packageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String string = (String) packageView.getSelectionModel().getSelectedItem();
                chosenPackageForRemove = string;
            }
        });

            methodsView.setItems(methodList);
            System.out.println("metody: " + methodList);
            methodsView.setPrefSize(600,160);
            methodsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String string = (String) methodsView.getSelectionModel().getSelectedItem();
                    chosenMethodForRemove = string;
                }
            });

            constructorView.setItems(constructorList);
            System.out.println("konstruktory: " + constructorList);
            constructorView.setPrefSize(600, 160);
            constructorView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String string = (String) constructorView.getSelectionModel().getSelectedItem();
                    chosenConstructorForRemove = string;
                }
            });

            fieldsView.setItems(fieldList);
            System.out.println("pola: " + fieldList);
            fieldsView.setPrefSize(600,160);
            fieldsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String string = (String) fieldsView.getSelectionModel().getSelectedItem();
                chosenFieldForRemove = string;
            }
        });

        HBox hBoxWithoutButtons = new HBox();
        hBoxWithoutButtons.setPrefSize(1400,660);
        hBoxWithoutButtons.setAlignment(Pos.TOP_CENTER);

            buttons.setAlignment(Pos.BOTTOM_CENTER);

        VBox jarOverview = new VBox(classesView); // classesView
        jarOverview.setPrefSize(600, 660);
        jarOverview.setAlignment(Pos.CENTER_LEFT);

        final Button buttonVerticalOne = new Button();
        final Button buttonVerticalTwo = new Button();
        final Button buttonVerticalThree = new Button();
        final Button buttonVerticalFour = new Button();
        final Button buttonOverrideMethod = new Button();
        final Button buttonInsertBefore = new Button();
        final Button buttonInsertAfter = new Button();
        final Button buttonVerticalFive = new Button();
        final Button buttonVerticalSix = new Button();
        final Button buttonVerticalSeven = new Button();
        final Button buttonVerticalEight = new Button();
        final Button buttonOverrideConstructor = new Button();
        buttonVerticalOne.setPrefHeight(20);buttonVerticalTwo.setPrefHeight(20);buttonVerticalThree.setPrefHeight(20);buttonVerticalFour.setPrefHeight(20);
        buttonVerticalFive.setPrefHeight(20);buttonVerticalSix.setPrefHeight(20);buttonVerticalSeven.setPrefHeight(20);buttonVerticalEight.setPrefHeight(20);
        buttonOverrideMethod.setPrefHeight(20);buttonInsertBefore.setPrefHeight(20);buttonInsertAfter.setPrefHeight(20);buttonOverrideConstructor.setPrefHeight(20);
        buttonVerticalOne.setPrefWidth(195);buttonVerticalTwo.setPrefWidth(195);buttonVerticalThree.setPrefWidth(195);buttonVerticalFour.setPrefWidth(195);
        buttonVerticalFive.setPrefWidth(195);buttonVerticalSix.setPrefWidth(195);buttonVerticalSeven.setPrefWidth(195);buttonVerticalEight.setPrefWidth(195);
        buttonOverrideMethod.setPrefWidth(195);buttonInsertBefore.setPrefWidth(195);buttonInsertAfter.setPrefWidth(195);buttonOverrideConstructor.setPrefWidth(195);
        buttonVerticalOne.setText("Add Class");buttonVerticalTwo.setText("Remove Class");
        buttonVerticalThree.setText("Add Method");buttonVerticalFour.setText("Remove Method");
        buttonOverrideMethod.setText("Override Method");buttonInsertBefore.setText("Insert before method body");
        buttonInsertAfter.setText("Insert after method body");buttonVerticalFive.setText("Add Field");
        buttonVerticalSix.setText("Remove Field");buttonVerticalSeven.setText("Add Constructor");
        buttonVerticalEight.setText("Remove Constructor");buttonOverrideConstructor.setText("Override Constructor");

        VBox verticalButtonsBox = new VBox();
        verticalButtonsBox.setPrefSize(200, 660);
        verticalButtonsBox.setAlignment(Pos.CENTER);
        verticalButtonsBox.getChildren().addAll(buttonVerticalOne, buttonVerticalTwo, buttonVerticalThree, buttonVerticalFour, buttonOverrideMethod, buttonInsertBefore, buttonInsertAfter, buttonVerticalFive, buttonVerticalSix, buttonVerticalSeven, buttonVerticalEight, buttonOverrideConstructor);

        VBox restDesignStuff = new VBox();
        restDesignStuff.setPrefSize(600, 660);
        restDesignStuff.setAlignment(Pos.CENTER_RIGHT);
        HBox packagesOverview = new HBox(packageView);
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
        hBoxWithoutButtons.getChildren().add(verticalButtonsBox);
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
//
//        buttonVerticalOne.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                ClassOperation.addNewClass();
//                classesView.setItems(classesList);
//            }
//        });

        buttonVerticalTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println("ClassPool before removing: " + JarFilee.classPool.getCtClass(chosenClassForRemove));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                ClassOperation.removeChosenClass(chosenClassForRemove);
                    thirdStage.close();
                    thirdStage.show();
                try {
                    System.out.println("ClassPool after removing: " + JarFilee.classPool.get(chosenClassForRemove));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonVerticalThree.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MethodOperation.addingNewMethod(chosenClassForRemove);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
                methodsView.setItems(methodList);
            }
        });

        buttonVerticalFour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> newMethodsList = MethodOperation.removeChosenMethod(chosenMethodForRemove);
                System.out.println("Updating methods for class...");
                try {
                    System.out.println("choosenMethodForRemove: " + chosenMethodForRemove);
                    JarFilee.updateMethodsForClass(chosenClassForRemove, chosenMethodForRemove);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                final ObservableList<String> newMethodList = FXCollections.observableArrayList(newMethodsList);
                methodsView.setItems(newMethodList);

            }
        });

        buttonOverrideMethod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MethodOperation.overrideMethod(chosenClassForRemove,chosenMethodForRemove);
                    methodsView.setItems(methodList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonInsertBefore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MethodOperation.addBeforeMethodBody(chosenClassForRemove, chosenMethodForRemove);
                    methodsView.setItems(methodList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonInsertAfter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MethodOperation.addAfterMethodBody(chosenClassForRemove, chosenMethodForRemove);
                    methodsView.setItems(methodList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonVerticalFive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FieldOperation.addingNewField(chosenClassForRemove);
                    fieldsView.setItems(fieldList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonVerticalSix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> newFieldsList = FieldOperation.removeChosenField(chosenFieldForRemove);
                System.out.println("Updating fields for class...");
                try {
                    System.out.println("chosenFieldForRemove: " + chosenFieldForRemove);
                    JarFilee.updateFieldsForClass(chosenClassForRemove, chosenFieldForRemove);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                final ObservableList<String> newFieldList = FXCollections.observableArrayList(newFieldsList);
                fieldsView.setItems(newFieldList);
            }
        });

        buttonVerticalSeven.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ConstructorOperation.addingNewConstructor(chosenClassForRemove);
                    constructorView.setItems(constructorList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonVerticalEight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> newConstructorsList = ConstructorOperation.removeChosenConstructor(chosenConstructorForRemove);
                System.out.println("Updating fields for class...");
                try {
                    System.out.println("chosenConstructorForRemove: " + chosenConstructorForRemove);
                    JarFilee.updateConstructorsForClass(chosenClassForRemove, chosenConstructorForRemove);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                final ObservableList<String> newConstructorList = FXCollections.observableArrayList(newConstructorsList);
                constructorView.setItems(newConstructorList);
            }
        });

        buttonOverrideConstructor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ConstructorOperation.overrideConstructor(chosenClassForRemove, chosenConstructorForRemove);
                    constructorView.setItems(constructorList);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void enterTextArea(){
        text1.setText(filePath);
        System.out.println("enterTextArea() filePath: " + filePath);
        text1.setPrefSize(200, 20);
        text1.setDisable(true);
        textGrid.addRow(0, text1);
        textGrid.setAlignment(Pos.TOP_RIGHT);
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
