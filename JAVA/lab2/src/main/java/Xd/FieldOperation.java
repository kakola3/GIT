package Xd;

import java.util.ArrayList;

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
}
