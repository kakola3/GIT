import java.sql.SQLOutput;

public class Main
{
    public static void main(String[] args)
    {
        int tab[] = new int[7];
        tab[0] = 5;
        tab[1] = 2;
        tab[2] = 9;
        tab[3] = 6;
        tab[4] = 3;
        for(int i=0; i<tab.length; i++)
        {
            System.out.println("Wartosc indeksu "+i+" jest rowna "+tab[i]);
        }

        for(int x : tab)
        {
            System.out.println(x);
        }

        int n=1;
        while(n<10)
        {
            System.out.print(n);
            n++;
        }
        System.out.print("\n");
        char tab_2[][] = {{'a','b','c'},{'4','5','6','7'}};
        for(int i=0; i<tab_2.length; i++)
        {
            for(int j=0; j<tab_2[i].length; j++)
            {
                System.out.print(tab_2[i][j]);
            }
        }
        System.out.println("\n\n");
        for(int a=1; a<4; a++)
        {
            for(int b=2; b<5; b++)
            {
                System.out.println(a+""+b);
            }
        }
        String napis="";
        for(int q=1; q<10; q++)
        {
            napis+="*";
            System.out.println(napis);
        }
        int tab_3[][] = new int [3][];
                tab_3[0] = new int [5];
                tab_3[1] = new int [3];
                tab_3[2] = new int [7];
        for(int g=0; g<tab_3.length; g++)
        {
            for(int h=0; h<tab_3[g].length; h++)
            {
                tab_3[g][h] = 1;
                System.out.print(tab_3[g][h]);
            }
            System.out.print("\n");
        }
        char a = 'a';
        while(a<='z')
        {
            System.out.println(a);
            a++;
        }
    }
}
