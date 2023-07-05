public class GleichLast {
    //Attribute
    double anfangspunkt;
    double endpunkt;
    double kraft;

    //Konstruktor
    //
    public GleichLast(double gla,double gle, double k){
        anfangspunkt=gla;
        endpunkt=gle;
        kraft =k;
    }
    //Methoden
    public double BerechneResultierendeStandort(){
        return (anfangspunkt+endpunkt)/2;
    }
    public double Berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double BerechneResultierende(){
        return Berechnelange()*kraft;
    }
}
