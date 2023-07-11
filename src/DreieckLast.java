public class DreieckLast {
    //Attribute
    double leange;
    double kraft;
    boolean ausrichtung;

    //Konstruktor

    public DreieckLast(double l, double k, boolean a){
        leange=l;
        kraft=k;
        ausrichtung=a;
    }
    //Methoden
    public double BerechneResultierendeStandort(){
        if(ausrichtung = true){
            return (leange) * 2 / 3;
        } else {
            return (leange) * 1 / 3;
        }
    }

    public double BerechneResultierende(){
        return leange*0.5*kraft;
    }


}
