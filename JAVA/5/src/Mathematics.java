public class Mathematics
{
    public static void main(String[] args)
    {
        Mathematics m = new Mathematics();
        m.classname();
        System.out.println(m.kwadrat(12));
        System.out.println(m.kwadrat(22));
        System.out.println(m.kwadrat(142));
        System.out.println(m.kwadrat(14.2));
        System.out.println(m.kwadrat(1.42));
        System.out.println(m.kwadrat(17.9));
    }

    public void classname()
    {
        System.out.println("Mathematics");
    }

    public int kwadrat(int a)
    {
        return a*a;
    }

    public double kwadrat(double a)
    {
        return a*a;
    }
}
