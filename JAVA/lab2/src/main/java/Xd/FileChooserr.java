package Xd;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public class FileChooserr
{
    FileChooser fileChooser = new FileChooser();
    public static File selectedFile;
    public static JarFile jarFile;

    public FileChooserr() throws IOException {
    }

    public File PickMe() throws Exception{
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            this.selectedFile = selectedFile;
            //JarFile jarFile = new JarFile(selectedFile);
            //this.jarFile = jarFile;
            //injectJarReader(selectedFile);
            //listView.getItems().add(selectedFile.getAbsolutePath());
            return selectedFile;
        }else return null;
}

    public static JarFile injectJarReader(File jarToRead) throws IOException {
        JarFile jarFile = new JarFile(jarToRead);
        return jarFile;
    }
}

//    public void Button1Action(ActionEvent event){
//        FileChooser fc = new FileChooser();
//        //fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
//        File selectedFile = fc.showOpenDialog(null);
//
//        if(selectedFile != null){
//            listView.getItems().add(selectedFile.getAbsolutePath());
//        }else {
//            System.out.println("file is not valid");
//        }
//    }