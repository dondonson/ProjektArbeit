import inf.v3d.obj.*;
import inf.v3d.obj.Arrow;
import inf.v3d.view.*;


public class PunktLast {
    //Attribute
    private double ort;
    private double kraft;
    private Balken balken;
    private int menge;

    //Konstruktor
    public PunktLast(double o,double k, Balken b,int m){
        ort=o;
        kraft=k;
        balken=b;
        menge=m;
    }

    //Getter und Setter
    public double getOrt() {
        return ort;
    }

    public void setOrt(double ort) {
        this.ort = ort;
    }

    public double getKraft() {
        return kraft;
    }

    public void setKraft(double kraft) {
        this.kraft = kraft;
    }
    /*Work in Progress
    public double berechneResultierendeOrt(){
        double resultierendeOrt;
        if (menge == 1) {
            resultierendeOrt = ort;
        } else {
            resultierendeOrt = p[0].getOrt() + (p[1].getKraft() / resultierendeKraft) * (p[1].getOrt() - p[0].getOrt());
        }
        return resultierendeOrt;
    }*/

    //Visualiesierug der Last
    public void zu3D(Viewer v){
        Arrow a1 = new Arrow(ort, balken.laenge / 3, 0, ort, 0.01* balken.laenge, 0);
        a1.setColor("red");
        v.addObject3D(a1);
        a1.setRadius(0.01 * balken.laenge);
        Text t1 = new Text("" + kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(ort + 0.1, balken.laenge / 3, 0);
        t1.setHeight(0.075 * balken.laenge);
    }
}
