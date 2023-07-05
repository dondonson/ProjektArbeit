public class DreieckLast {
    //Attribute
    double anfangspunkt;
    double endpunkt;
    double kraft;
    String ausrichtung;

    //Konstruktor
    //
    public DreieckLast(double dla,double dle, double k, String a){
        anfangspunkt=dla;
        endpunkt=dle;
        kraft=k;
        ausrichtung=a;
    }
    //Methoden
    public double BerechneResultierendeStandort(){
        if(ausrichtung=="ja"||ausrichtung=="Ja"){
            return (anfangspunkt + endpunkt) * 2 / 3;
        } else if(ausrichtung =="nein"||ausrichtung=="Nein") {
            return (anfangspunkt + endpunkt) * 1 / 3;
        }
        return BerechneResultierendeStandort();
    }
    public double Berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double BerechneResultierende(){
        return Berechnelange()*0.5*kraft;
    }
}
