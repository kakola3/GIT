import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // enter how long is our linear equation
        System.out.print("How many criteria: ");
        int numberOfCriteria = scan.nextInt();

        // enter x arguments values
        double[] coefficients = createDynamicDoubleArrayForParameters(numberOfCriteria, 1);

        // create an objective function for a linear optimization problem.
        LinearObjectiveFunction linearObjectiveFunction = createNewLinearObjectiveFunction(coefficients);
        System.out.println("linearObjectiveFunction: " + linearObjectiveFunction.getCoefficients());

        // set restrictions for our linear equation
        System.out.print("How many restrictions: ");
        int numberOfRestrictions = scan.nextInt();
        ArrayList<LinearConstraint> restrictions = setRestrictions(numberOfCriteria, numberOfRestrictions);

        // creating linear constraint set
        LinearConstraintSet linearConstraintSet = createNewLinearConstraintSet(restrictions);

        // create solution of our linear equation
        System.out.print("Standard Min or Standard Max form (enter max or min): ");
        String form = scan.next();
        PointValuePair equationSolution = getEquationSolution(linearObjectiveFunction, linearConstraintSet, form);

        // show solution on screen
        showSolutionOnScreen(equationSolution, numberOfCriteria);
    }

    public static double[] createDynamicDoubleArrayForParameters(int arraySize, int versionOfFunction){
        double[] dynamicDoubleArrayForParameters = new double[arraySize];
        Scanner scan = new Scanner(System.in);
        System.out.println("dynamicDoubleArrayForParameters is empty: |" + Arrays.toString(dynamicDoubleArrayForParameters) + "|");
        for (int i = 0; i < arraySize; i++) {
            System.out.print(versionOfFunction == 1 ?
                    "Please enter value of x" + (i+1) + " in linear objective function: " :
                    "Please enter value of x" + (i+1) + " in restriction: ");
            Double doubleValue = scan.nextDouble();
            dynamicDoubleArrayForParameters[i] = doubleValue;
        }
        System.out.println("dynamicDoubleArrayForParameters is full: |" + Arrays.toString(dynamicDoubleArrayForParameters) + "|");
        return dynamicDoubleArrayForParameters;
    }

    public static LinearObjectiveFunction createNewLinearObjectiveFunction(double[] coefficientsArray){
        return new LinearObjectiveFunction(coefficientsArray, 0);
    }

    public static ArrayList<LinearConstraint> setRestrictions(int numberOfCriteria, int numberOfRestrictions){
        ArrayList<LinearConstraint> restrictionsArray = new ArrayList<>();

        for (int i = 0; i < numberOfRestrictions; i++) {
            Relationship relationship = setRestrictionRelationship();
            Double constantTerm = setConstantTerm();
            double[] restrictionArrayForCoefficients = createDynamicDoubleArrayForParameters(numberOfCriteria, 2);
            restrictionsArray.add(new LinearConstraint(restrictionArrayForCoefficients, relationship, constantTerm));
        }

        return restrictionsArray;
    }

    public static Relationship setRestrictionRelationship(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter restriction sign like <= or = or >=");
        String restriction = scan.next();
        return (restriction.equals("<=")) ? Relationship.LEQ : (restriction.equals(">=") ? Relationship.GEQ : Relationship.EQ);
    }

    public static double setConstantTerm(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter constant term for this restriction");
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
}
