public class GleichLast {
    //Attribute
    private double anfangspunkt;
    private double endpunkt;
    private double kraft;

    //Konstruktor
    //
    public GleichLast(double gla,double gle, double k){
        anfangspunkt=gla;
        endpunkt=gle;
        kraft =k;
    }
    //Methoden
    public double berechneResultierendeStandort(){
        return (anfangspunkt+endpunkt)/2;
    }
    public double berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double berechneResultierende(){
        return berechnelange()*kraft;
    }

    public double getAnfangspunkt() {
        return anfangspunkt;
    }

    public void setAnfangspunkt(double anfangspunkt) {
        this.anfangspunkt = anfangspunkt;
    }

    public double getEndpunkt() {
        return endpunkt;
    }

    public void setEndpunkt(double endpunkt) {
        this.endpunkt = endpunkt;
    }

    public double getKraft() {
        return kraft;
    }

    public void setKraft(double kraft) {
        this.kraft = kraft;
    }
}
