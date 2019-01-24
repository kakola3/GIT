package Xd;

import javafx.scene.control.TextInputDialog;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;

import java.util.ArrayList;
import java.util.Optional;

public class ClassOperation
{
    public static void removeChosenClass(String chosenClass){
        ArrayList <String> temporaryOnlyClassesArray  = JarFilee.onlyClassesArray;
        System.out.println("temporaryOnlyClassesArray: " + temporaryOnlyClassesArray);
        if(temporaryOnlyClassesArray.contains(chosenClass)){
            System.out.println("Czy chcesz usunac klase " + chosenClass + "?");
            temporaryOnlyClassesArray.remove(chosenClass);
            System.out.println("temporaryOnlyClassesArray: " + temporaryOnlyClassesArray);
            JarFilee.allFiles = temporaryOnlyClassesArray;
        }
    }

    public static void addNewClass(){
        ClassPool classPool = JarFilee.classPool;
        TextInputDialog dialog = new TextInputDialog("newPublicClass");
        dialog.setTitle("New Class");
        dialog.setHeaderText("Write some code as class declaration");
        dialog.setContentText("Class:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(classText -> {
            try {
                CtClass ctClass = classPool.makeClass(classText);
                JarFilee.allFiles.add(JavaFX.chosenPackageForRemove + "." + ctClass.getName() + ".class");
                System.out.println("JarFilee.allFiles: " + JarFilee.allFiles);
               // byte[] classToByte = ctClass.toBytecode();
                //ctClass.defrost();
               // JarFilee.classPool = classPool;
               // System.out.println("ClassPool: " + classPool);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
