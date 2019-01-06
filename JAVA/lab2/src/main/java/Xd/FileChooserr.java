package Xd;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;

public class FileChooserr
{
    FileChooser fileChooser = new FileChooser();
    ListView listView;

    public File PickMe() throws Exception{
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            //listView.getItems().add(selectedFile.getAbsolutePath());
            return selectedFile;
        }else return null;
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