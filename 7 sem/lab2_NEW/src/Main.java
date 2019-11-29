import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SimplexAlgorithm simplexAlgorithm = new SimplexAlgorithm();

        // enter how long is our linear equation
        System.out.print("Ile kryteriów: ");
        int numberOfCriteria = scan.nextInt();

        // enter x arguments values
        double[] coefficients = simplexAlgorithm.createDynamicDoubleArrayForParameters(numberOfCriteria, 1);
        System.out.print("Funkcja minimalizująca czy maksymalizująca? Wpisz min lub max: ");
        String form = scan.next();
        StringBuilder stringBuilderForEquation = simplexAlgorithm.drawScreen(coefficients, form);

        // create an objective function for a linear optimization problem.
        LinearObjectiveFunction linearObjectiveFunction = simplexAlgorithm.createNewLinearObjectiveFunction(coefficients);

        // set restrictions for our linear equation
        System.out.print("Ilość ograniczeń: ");
        int numberOfRestrictions = scan.nextInt();
        ArrayList<LinearConstraint> restrictions = simplexAlgorithm.setRestrictions(numberOfCriteria, numberOfRestrictions, stringBuilderForEquation);

        // creating linear constraint set
        LinearConstraintSet linearConstraintSet = simplexAlgorithm.createNewLinearConstraintSet(restrictions);

        // create solution of our linear equation
        PointValuePair equationSolution = simplexAlgorithm.getEquationSolution(linearObjectiveFunction, linearConstraintSet, form);

        // show solution on screen
        simplexAlgorithm.showSolutionOnScreen(equationSolution, numberOfCriteria);
    }


}
