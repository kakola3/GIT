package Xd;

import javafx.scene.control.TextInputDialog;
import javassist.*;

import java.util.ArrayList;
import java.util.Optional;

public class ConstructorOperation
{
    public static ArrayList<String> removeChosenConstructor(String chosenConstructor){ // FROM VIEW
        ArrayList<String> temporaryOnlyConstructordArray  = JavaFX.constructorsList;
        System.out.println("temporaryOnlyConstructordArray: " + temporaryOnlyConstructordArray);
        if(temporaryOnlyConstructordArray.contains(chosenConstructor)){
            System.out.println("Czy chcesz usunac konstruktor " + chosenConstructor + "?");
            temporaryOnlyConstructordArray.remove(chosenConstructor);
            System.out.println("temporaryOnlyConstructordArray: " + temporaryOnlyConstructordArray);
            JavaFX.constructorsList = temporaryOnlyConstructordArray;
        }
        return temporaryOnlyConstructordArray;
    }

    public static void addingNewConstructor(String classToAdd) throws NotFoundException, CannotCompileException {
        ArrayList<String> temporaryOnlyConstructorArray  = JavaFX.constructorsList;
        CtClass ctClass = JarFilee.classPool.get(classToAdd);
        System.out.println("JarFilee.classPool.get(classToAdd): " + JarFilee.classPool.get(classToAdd));
        TextInputDialog dialog = new TextInputDialog("public newConstructor(){public int field1;private String field2};");
        dialog.setTitle("New Constructor");
        dialog.setHeaderText("Write some code as constructor declaration");
        dialog.setContentText("Constructor:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(constructorText -> {
            try {
                CtConstructor cons = new CtConstructor(null, ctClass);
                cons.setBody(constructorText);  // result.get().trim()
                ctClass.addConstructor(cons);
                System.out.println("constructorText: " + constructorText);
                temporaryOnlyConstructorArray.add(constructorText);
                System.out.println("temporaryOnlyConstructorArray: " + temporaryOnlyConstructorArray);
                JavaFX.constructorsList = temporaryOnlyConstructorArray;
                System.out.println("temporaryOnlyConstructorArray: " + temporaryOnlyConstructorArray);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
    }
}
