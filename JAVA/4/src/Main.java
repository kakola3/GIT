import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        if(5<10)
        {
            System.out.println("5<10");
        }
        int a = 10;
        while(a>0)
        {
            if(a!=5)
            {
                System.out.println("a != 5");
            }
            if(a==5)
            {
                System.out.println("a = 5");
            }
            a--;
        }

        int b = 7;
        switch(b)
        {
            case 1:
                System.out.println("b = 1");
                break;
            case 2:
                System.out.println("b = 2");
                break;
            case 3:
                System.out.println("b = 3");
                break;
            case 4:
                System.out.println("b = 4");
                break;
            case 5:
                System.out.println("b = 5");
                break;
            default:
                System.out.println("DEFAULT");
        }

        for(int i=0; i<6; i++)
        {
            if(i<4)
            {
                System.out.println(i);
            }
            else if(i == 4)
            {
                System.out.println("jestem rowny 4 wiec opuszczam");
                break;
            }
        }

        for(int j=0; j<10; j++)
        {
            if(j%2 == 0) continue;
            System.out.print(j+" ");
        }

        ////////////////////////////Wczytywanie z klawiatury\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        int aa, bb, cc;
        Scanner scanner = new Scanner(System.in);
        System.out.print("a=");
        aa = scanner.nextInt();
        System.out.print("b=");
        bb = scanner.nextInt();
        System.out.print("c=");
        cc = scanner.nextInt();


    }
}
