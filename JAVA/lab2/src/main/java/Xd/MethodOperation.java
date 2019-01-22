package Xd;

import java.util.ArrayList;

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
            //JavaFX.methodsView.refresh();
        }
        return temporaryOnlyMethodArray;
    }

    public static void updateMethodsInClass(String choosenClass, String choosenMethod){

    }
}
