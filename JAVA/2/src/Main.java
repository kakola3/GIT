//jest to klasa main
public class Main
{
    byte b = 127;      //-128 do 127
    short s = 32767;    //-32768 do 32767
    int i = -241748648; //-2417483648 do 2417483647
    long l = 9222132435478l;
    float f = 3.44444444444f;
    double d = 3.908743546890786675;
    char c = 'a';
    String string = "To moze nam reprezentowac jakis tekst";

    public static void main (String[] args)
    {
        int wynik1, wynik2 = 7, wynik3;
        float wynik4;
        int l1 = 56;
        int l2 = 13;
        byte b1 = 127;
        wynik1 = l1 + l2;
        wynik2 = l1 - l2;
        wynik3 = l1 * l2;
        wynik4 = l1 / l2;
        int wynik5 = l1%l2;
        System.out.println("Wynik1 = "+wynik1);
        System.out.println("Wynik2 = "+wynik2);
        System.out.println("Wynik3 = "+wynik3);
        System.out.println("Wynik4 = "+wynik4);
        System.out.println("Wynik5 = "+wynik5);
        wynik5++;
        System.out.println("Wynik5.1 = "+wynik5);
        wynik5++;
        System.out.println("Wynik5.2 = "+wynik5);
        wynik5++;
        System.out.println("Wynik5.3 = "+wynik5);
        wynik1--;
        System.out.println("Wynik1.1 = "+wynik1);
        wynik1--;
        System.out.println("Wynik1.1 = "+wynik1);
        b1++;
        System.out.println("Wynik byte: "+b1);
    }
}
