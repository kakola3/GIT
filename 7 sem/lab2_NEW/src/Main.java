import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // enter how long is our linear equation
        System.out.print("Ile kryteriów: ");
        int numberOfCriteria = scan.nextInt();

        // enter x arguments values
        double[] coefficients = createDynamicDoubleArrayForParameters(numberOfCriteria, 1);
        StringBuilder stringBuilderForEquation = drawScreen(coefficients);

        // create an objective function for a linear optimization problem.
        LinearObjectiveFunction linearObjectiveFunction = createNewLinearObjectiveFunction(coefficients);

        // set restrictions for our linear equation
        System.out.print("Ilość ograniczeń: ");
        int numberOfRestrictions = scan.nextInt();
        ArrayList<LinearConstraint> restrictions = setRestrictions(numberOfCriteria, numberOfRestrictions, stringBuilderForEquation);

        // creating linear constraint set
        LinearConstraintSet linearConstraintSet = createNewLinearConstraintSet(restrictions);

        // create solution of our linear equation
        System.out.print("Funkcja minimalizująca czy maksymalizująca? Wpisz min lub max: ");
        String form = scan.next();
        PointValuePair equationSolution = getEquationSolution(linearObjectiveFunction, linearConstraintSet, form);

        // show solution on screen
        showSolutionOnScreen(equationSolution, numberOfCriteria);
    }

    public static double[] createDynamicDoubleArrayForParameters(int arraySize, int versionOfFunction){
        double[] dynamicDoubleArrayForParameters = new double[arraySize];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < arraySize; i++) {
            System.out.print(versionOfFunction == 1 ?
                    "Wprowadź wartość x" + (i+1) + " w równaniu liniowym: " :
                    "Wprowadź wartość x" + (i+1) + " w tym ograniczeniu: ");
            Double doubleValue = scan.nextDouble();
            dynamicDoubleArrayForParameters[i] = doubleValue;
        }
        return dynamicDoubleArrayForParameters;
    }

    public static LinearObjectiveFunction createNewLinearObjectiveFunction(double[] coefficientsArray){
        return new LinearObjectiveFunction(coefficientsArray, 0);
    }

    public static LinearConstraint setRestriction(int numberOfCriteria){
        Relationship relationship = setRestrictionRelationship();
        Double constantTerm = setConstantTerm();
        double[] restrictionArrayForCoefficients = createDynamicDoubleArrayForParameters(numberOfCriteria, 2);

        return new LinearConstraint(restrictionArrayForCoefficients, relationship, constantTerm);
    }

    public static ArrayList<LinearConstraint> setRestrictions(int numberOfCriteria, int numberOfRestrictions, StringBuilder stringBuilderForEquation){
        ArrayList<LinearConstraint> restrictionsArray = new ArrayList<>();

        for (int i = 0; i < numberOfRestrictions; i++) {
            LinearConstraint linearConstraint = setRestriction(numberOfCriteria);
            restrictionsArray.add(linearConstraint);
            System.out.print("--------------------------------------------------------\n");
            System.out.print(stringBuilderForEquation);
            drawRestriction(restrictionsArray, numberOfCriteria);
        }

        return restrictionsArray;
    }

    public static Relationship setRestrictionRelationship(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadź znak ograniczenia <= lub = lub >=");
        String restriction = scan.next();
        return (restriction.equals("<=")) ? Relationship.LEQ : (restriction.equals(">=") ? Relationship.GEQ : Relationship.EQ);
    }

    public static double setConstantTerm(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadź wartość stałej w tym ograniczeniu");
        double constantTerm = scan.nextDouble();

        return constantTerm;
    }

    public static LinearConstraintSet createNewLinearConstraintSet(ArrayList<LinearConstraint> restrictions){
        return new LinearConstraintSet(restrictions);
    }

    public static PointValuePair getEquationSolution(LinearObjectiveFunction linearObjectiveFunction, LinearConstraintSet linearConstraintSet, String form){
        SimplexSolver simplexSolver = createNewSimplexSolver();

        GoalType goalType = (form.equals("max") ? GoalType.MAXIMIZE : GoalType.MINIMIZE);

        return simplexSolver.optimize(linearObjectiveFunction, linearConstraintSet, goalType);
    }

    public static SimplexSolver createNewSimplexSolver(){
        return new SimplexSolver();
    }

    public static void showSolutionOnScreen(PointValuePair equationSolution, int numberOfCriteria){
        for (int i = 0; i < numberOfCriteria; i++) {
            System.out.println("x" + (i+1) + " = " + equationSolution.getPoint()[i]);
        }
        System.out.println("+++++++++++++++");
        System.out.println("Solution value: " + equationSolution.getValue());
    }

    public static StringBuilder drawScreen(double[] coefficients){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            if(i < (coefficients.length-1)){
                System.out.print(coefficients[i] + "x" + (i+1) + " + ");
                stringBuilder.append(coefficients[i] + "x" + (i+1) + " + ");
            }
            else{
                System.out.print(coefficients[i] + "x" + (i+1) + "\n");
                stringBuilder.append(coefficients[i] + "x" + (i+1) + "\n");
            }
        }
        return stringBuilder;
    }

    public static void drawRestriction(ArrayList<LinearConstraint> restrictions, int numberOfCriteria){
        System.out.print("--------------------------------------------------------\n");
        for (LinearConstraint restriction :
                restrictions) {
            for (int i = 0; i < numberOfCriteria; i++) {
                if(i < (numberOfCriteria-1)){
                    System.out.print(restriction.getCoefficients().getEntry(i) + "x" + (i+1) + " + ");
                }
                else{
                    System.out.print(restriction.getCoefficients().getEntry(i) + "x" + (i+1) + " " +
                            restriction.getRelationship() + " " + restriction.getValue() + "\n");
                }
            }
        }
        System.out.print("--------------------------------------------------------\n");
    }
}
