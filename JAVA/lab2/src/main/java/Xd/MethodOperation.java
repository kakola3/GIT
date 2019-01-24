package Xd;

import javafx.scene.control.TextInputDialog;
import javassist.*;

import java.util.ArrayList;
import java.util.Optional;

public class MethodOperation
{
    public static ArrayList<String> removeChosenMethod(String chosenMethod){ // FROM VIEW
        ArrayList<String> temporaryOnlyMethodArray  = JavaFX.methodsList;
        System.out.println("temporaryOnlyMethodArray: " + temporaryOnlyMethodArray);
        if(temporaryOnlyMethodArray.contains(chosenMethod)){
            System.out.println("Czy chcesz usunac metode " + chosenMethod + "?");
            temporaryOnlyMethodArray.remove(chosenMethod);
            System.out.println("temporaryOnlyMethodArray: " + temporaryOnlyMethodArray);
            JavaFX.methodsList = temporaryOnlyMethodArray;
        }
        return temporaryOnlyMethodArray;
    }

    public static void addingNewMethod(String classToAdd) throws NotFoundException, CannotCompileException {
        ArrayList<String> temporaryOnlyMethodArray  = JavaFX.methodsList;
        CtClass ctClass = JarFilee.classPool.get(classToAdd);
        System.out.println("JarFilee.classPool.get(classToAdd): " + JarFilee.classPool.get(classToAdd));
        TextInputDialog dialog = new TextInputDialog("public int newMethod(){}");
        dialog.setTitle("New Method");
        dialog.setHeaderText("Write some code as method declaration");
        dialog.setContentText("Method:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(methodText -> {
            try {
                ctClass.addMethod(CtMethod.make(result.get().trim(), ctClass));
                System.out.println("methodText: " + methodText);
                temporaryOnlyMethodArray.add(methodText);
                System.out.println("temporaryOnlyMethodArray: " + temporaryOnlyMethodArray);
                JavaFX.methodsList = temporaryOnlyMethodArray;
                System.out.println("temporaryOnlyMethodArray: " + temporaryOnlyMethodArray);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
    }

    public static void overrideMethod(String classToUpdate, String methodToUpdate) throws NotFoundException {
        CtClass ctClass = JarFilee.classPool.get(classToUpdate);
        CtMethod[] ctMethods = ctClass.getDeclaredMethods();
        for(CtMethod ctMethod: ctMethods) {
            if(methodToUpdate.contains(ctMethod.getName())){
                TextInputDialog dialog = new TextInputDialog("System.out.println(\"Override the method\");");
                dialog.setTitle("Updated Method");
                dialog.setHeaderText("Write some code as method update");
                dialog.setContentText("Method:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(methodText -> {
                    try {
                        ctMethod.setBody(methodText);
                        System.out.println("New method body: " + methodText);
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public static void addBeforeMethodBody(String classToUpdate, String methodToUpdate) throws NotFoundException {
        CtClass ctClass = JarFilee.classPool.get(classToUpdate);
        CtMethod[] ctMethods = ctClass.getDeclaredMethods();
        for(CtMethod ctMethod: ctMethods) {
            if(methodToUpdate.contains(ctMethod.getName())){
                TextInputDialog dialog = new TextInputDialog("System.out.println(\"Add before the method\");");
                dialog.setTitle("AddBefore Method");
                dialog.setHeaderText("Write some code as method update");
                dialog.setContentText("Method:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(methodText -> {
                    try {
                        ctMethod.insertBefore(methodText);
                        System.out.println("Inserted part of method body: " + methodText);
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public static void addAfterMethodBody(String classToUpdate, String methodToUpdate) throws NotFoundException {
        CtClass ctClass = JarFilee.classPool.get(classToUpdate);
        CtMethod[] ctMethods = ctClass.getDeclaredMethods();
        for(CtMethod ctMethod: ctMethods) {
            if(methodToUpdate.contains(ctMethod.getName())){
                TextInputDialog dialog = new TextInputDialog("System.out.println(\"Add after the method\");");
                dialog.setTitle("AddAfter Method");
                dialog.setHeaderText("Write some code as method update");
                dialog.setContentText("Method:");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(methodText -> {
                    try {
                        ctMethod.insertAfter(methodText);
                        System.out.println("Inserted part of method body: " + methodText);
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
