import java.io.*;

public class Tastatur
{
    public static String liesString()
    {
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        try { return reader.readLine(); } catch( IOException e ) { return ""; }
    }
    public static int liesInt()
    {
        try { return Integer.parseInt(liesString()); } catch( Exception e ) { return 0; }
    }
    public static double liesDouble()
    {
        try { return Double.parseDouble(liesString()); } catch( Exception e ) { return 0; }
    }
    public static int liesInt( String eingabeAufforderungsString )
    {
        System.out.print(eingabeAufforderungsString);
        return liesInt();
    }
    public static double liesDouble( String eingabeAufforderungsString )
    {
        System.out.print(eingabeAufforderungsString);
        return liesDouble();
    }
    public static String liesString( String eingabeAufforderungsString )
    {
        System.out.print(eingabeAufforderungsString);
        return liesString();
    }
}