package Xd;

import java.util.ArrayList;

public class ClassOperation
{
    public static void removeChosenClass(String chosenClass){
        ArrayList <String> temporaryOnlyClassesArray  = JarFilee.onlyClassesArray;
        System.out.println("temporaryOnlyClassesArray: " + temporaryOnlyClassesArray);
        if(temporaryOnlyClassesArray.contains(chosenClass)){
            System.out.println("Czy chcesz usunac klase " + chosenClass + "?");
            temporaryOnlyClassesArray.remove(chosenClass);
            System.out.println("temporaryOnlyClassesArray: " + temporaryOnlyClassesArray);
            JarFilee.onlyClassesArray = temporaryOnlyClassesArray;
        }
    }
}
