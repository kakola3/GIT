import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Scanner;

public class SimplexAlgorithm
{
    public double[] createDynamicDoubleArrayForParameters(int arraySize, int versionOfFunction){
        double[] dynamicDoubleArrayForParameters = new double[arraySize];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < arraySize; i++) {
            System.out.print(versionOfFunction == 1 ?
                    "Wprowadź wartość x" + (i+1) + " w funkcji celu równania liniowego: " :
                    "Wprowadź wartość x" + (i+1) + " w tym ograniczeniu: ");
            Double doubleValue = scan.nextDouble();
            dynamicDoubleArrayForParameters[i] = doubleValue;
        }
        return dynamicDoubleArrayForParameters;
    }

    public LinearObjectiveFunction createNewLinearObjectiveFunction(double[] coefficientsArray){
        return new LinearObjectiveFunction(coefficientsArray, 0);
    }

    private LinearConstraint setRestriction(int numberOfCriteria){
        double[] restrictionArrayForCoefficients = createDynamicDoubleArrayForParameters(numberOfCriteria, 2);
        Relationship relationship = setRestrictionRelationship();
        Double constantTerm = setConstantTerm();

        return new LinearConstraint(restrictionArrayForCoefficients, relationship, constantTerm);
    }

    public ArrayList<LinearConstraint> setRestrictions(int numberOfCriteria, int numberOfRestrictions, StringBuilder stringBuilderForEquation){
        ArrayList<LinearConstraint> restrictionsArray = new ArrayList<>();

        for (int i = 0; i < numberOfRestrictions; i++) {
            LinearConstraint linearConstraint = setRestriction(numberOfCriteria);
            restrictionsArray.add(linearConstraint);
            System.out.print("--------------------------------------------------------\n");
            System.out.print("Wzór utworzonej funkcji: " + stringBuilderForEquation);
            drawRestriction(restrictionsArray, numberOfCriteria);
        }

        return restrictionsArray;
    }

    private Relationship setRestrictionRelationship(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadź znak ograniczenia <= lub = lub >=");
        String restriction = scan.next();
        return (restriction.equals("<=")) ? Relationship.LEQ : (restriction.equals(">=") ? Relationship.GEQ : Relationship.EQ);
    }

    private double setConstantTerm(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadź wartość stałej w tym ograniczeniu");
        double constantTerm = scan.nextDouble();

        return constantTerm;
    }

    public LinearConstraintSet createNewLinearConstraintSet(ArrayList<LinearConstraint> restrictions){
        return new LinearConstraintSet(restrictions);
    }

    public PointValuePair getEquationSolution(LinearObjectiveFunction linearObjectiveFunction, LinearConstraintSet linearConstraintSet, String form){
        SimplexSolver simplexSolver = createNewSimplexSolver();

        GoalType goalType = (form.equals("max") ? GoalType.MAXIMIZE : GoalType.MINIMIZE);

        return simplexSolver.optimize(linearObjectiveFunction, linearConstraintSet, goalType);
    }

    private SimplexSolver createNewSimplexSolver(){
        return new SimplexSolver();
    }

    public void showSolutionOnScreen(PointValuePair equationSolution, int numberOfCriteria){
        for (int i = 0; i < numberOfCriteria; i++) {
            System.out.println("x" + (i+1) + " = " + equationSolution.getPoint()[i]);
        }
        System.out.println("+++++++++++++++");
        System.out.println("Rozwiązanie: " + equationSolution.getValue());
    }

    public StringBuilder drawScreen(double[] coefficients, String form){
        StringBuilder stringBuilder = new StringBuilder();
        System.out.print("Wzór utworzonej funkcji: ");
        for (int i = 0; i < coefficients.length; i++) {
            if(i < (coefficients.length-1)){
                System.out.print(coefficients[i] + "x" + (i+1) + " + ");
                stringBuilder.append(coefficients[i] + "x" + (i+1) + " + ");
            }
            else{
                System.out.print(coefficients[i] + "x" + (i+1) + " --> " + form +  "\n");
                stringBuilder.append(coefficients[i] + "x" + (i+1) + " --> " + form + "\n");
            }
        }
        return stringBuilder;
    }

    private void drawRestriction(ArrayList<LinearConstraint> restrictions, int numberOfCriteria){
        System.out.print("--------------------------------------------------------\n");
        System.out.print("Ograniczenia: \n");
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
