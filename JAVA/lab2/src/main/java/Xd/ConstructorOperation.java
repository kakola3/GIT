package Xd;

import java.util.ArrayList;

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
}
