package Xd;

import javafx.scene.control.TextInputDialog;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

public class FieldOperation
{
    public static ArrayList<String> removeChosenField(String chosenField){ // FROM VIEW
        ArrayList<String> temporaryOnlyFieldArray  = JavaFX.fieldsList;
        System.out.println("temporaryOnlyFieldArray: " + temporaryOnlyFieldArray);
        if(temporaryOnlyFieldArray.contains(chosenField)){
            System.out.println("Czy chcesz usunac pole " + chosenField + "?");
            temporaryOnlyFieldArray.remove(chosenField);
            System.out.println("temporaryOnlyMethodArray: " + temporaryOnlyFieldArray);
            JavaFX.fieldsList = temporaryOnlyFieldArray;
        }
        return temporaryOnlyFieldArray;
    }

    public static void addingNewField(String classToAdd) throws NotFoundException, CannotCompileException {
        ArrayList<String> temporaryOnlyFieldArray  = JavaFX.fieldsList;
        CtClass ctClass = JarFilee.classPool.get(classToAdd);
        System.out.println("JarFilee.classPool.get(classToAdd): " + JarFilee.classPool.get(classToAdd));
        TextInputDialog dialog = new TextInputDialog("public int newField;");
        dialog.setTitle("New Field");
        dialog.setHeaderText("Write some code as field declaration");
        dialog.setContentText("Field:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(fieldText -> {
            try {
                ctClass.addField(CtField.make(fieldText, ctClass));
                System.out.println("fieldText: " + fieldText);
                temporaryOnlyFieldArray.add(fieldText);
                System.out.println("temporaryOnlyFieldArray: " + temporaryOnlyFieldArray);
                JavaFX.fieldsList = temporaryOnlyFieldArray;
                System.out.println("temporaryOnlyFieldArray: " + temporaryOnlyFieldArray);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
    }
}
