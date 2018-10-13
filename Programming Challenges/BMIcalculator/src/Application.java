import java.util.Scanner;

class BMIcalculator
{
    private double heightCentimeters;
    private double weightKilograms;
    private double weightPounds;
    private double heightInches;
    private int choise;

    void BMIcalculator(double heightCentimeters, double weightKilograms, double weightPounds, double heightInches){
        this.heightCentimeters = heightCentimeters;
        this.weightKilograms = weightKilograms;
        this.weightPounds = weightPounds;
        this.heightInches = heightInches;
    }

    public void WhichOption(){
        Scanner whichDataTypes = new Scanner(System.in);
        System.out.println("Please enter which types of data you prefer.");
        System.out.println("1. Centimeters/Kilograms");
        System.out.println("2. Inches/Pounds");
        choise = whichDataTypes.nextInt();
    }

    public void displayCalculateValues(){

    }

    public void MainCalculating(){
        Scanner value = new Scanner(System.in);
        if(this.choise == 1){
            System.out.print("Please enter your height in Centimeters: ");
            double centimeters = value.nextDouble();
            setHeightCentimeters(centimeters);
            System.out.print("Please enter your weight in Kilograms: ");
            double kilograms = value.nextDouble();
            setWeightKilograms(kilograms);
        }
        else if(this.choise == 2){
            System.out.print("Please enter your height in Inches: ");
            double inches = value.nextDouble();
            setHeightInches(inches);
            System.out.print("Please enter your weight in Pounds: ");
            double pounds = value.nextDouble();
            setWeightPounds(pounds);
        }
        else System.out.println("I have no idea what's now!?");
    }

    public void setHeightCentimeters(double heightCentimeters) {
        this.heightCentimeters = heightCentimeters;
    }

    public void setWeightKilograms(double weightKilograms) {
        this.weightKilograms = weightKilograms;
    }

    public void setWeightPounds(double weightPounds) {
        this.weightPounds = weightPounds;
    }

    public void setHeightInches(double heightInches) {
        this.heightInches = heightInches;
    }
}

public class Application
{
    public static void main(String[] args){

    }
}
