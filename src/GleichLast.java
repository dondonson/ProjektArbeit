import inf.v3d.obj.Arrow;
import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GleichLast {
    //Attribute
    private double anfangspunkt;
    private double endpunkt;
    private double kraft;
    private Balken balken;

    //Konstruktor
    //
    public GleichLast(double gla,double gle, double k,Balken b){
        anfangspunkt=gla;
        endpunkt=gle;
        kraft =k;
        balken=b;
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

    public double berechneAuflagerkraft1(){
        double ak1= berechneResultierendeStandort() / balken.laenge * berechneResultierende();
        return ak1;
    }
    public double berechneAuflagerkraft2(){
        double ak2= berechneResultierende() - berechneAuflagerkraft1();
        return ak2;
    }
    //Visualisierung

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

    public Balken getBalken() {
        return balken;
    }

    public void setBalken(Balken balken) {
        this.balken = balken;
    }
    public static double runden(double wert, int stellen){
        if (stellen <0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(wert);
        bd = bd.setScale(stellen, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void zu3D(Viewer v) {
        Cylinder c1 = new Cylinder(anfangspunkt, balken.laenge / 3, 0, endpunkt, balken.laenge / 3, 0);
        Arrow a1 = new Arrow(anfangspunkt, balken.laenge / 3, 0, anfangspunkt, 0.01 * balken.laenge, 0);
        Arrow a2 = new Arrow(endpunkt, balken.laenge / 3, 0, endpunkt, 0.01 * balken.laenge, 0);
        Arrow a3 = new Arrow(berechneResultierendeStandort(), balken.laenge / 3, 0, berechneResultierendeStandort(), 0.01 * balken.laenge, 0);
        Text t1 = new Text("" + kraft);
        v.addObject3D(c1);
        v.addObject3D(a1);
        v.addObject3D(a2);
        v.addObject3D(a3);
        v.addObject3D(t1);
        c1.setColor("red");
        a1.setColor("red");
        a2.setColor("red");
        a3.setColor("red");
        t1.setColor("red");
        c1.setRadius(0.01 * balken.laenge);
        a1.setRadius(0.01 * balken.laenge);
        a2.setRadius(0.01 * balken.laenge);
        a3.setRadius(0.01 * balken.laenge);
        t1.setOrigin(endpunkt + 0.1, balken.laenge / 3, 0);
        t1.setHeight(0.075 * balken.laenge);
        Text tak1 = new Text("" + runden(berechneAuflagerkraft1(),3));
        v.addObject3D(tak1);
        tak1.setColor("blue");
        tak1.setOrigin(0.1, -balken.laenge / 3, 0);
        Text tak2 = new Text("" + runden(berechneAuflagerkraft2(),3));
        v.addObject3D(tak2);
        tak2.setColor("blue");
        tak2.setOrigin(balken.laenge + 0.1, -balken.laenge / 3, 0);
        tak1.setHeight(0.075 * balken.laenge);
        tak2.setHeight(0.075 * balken.laenge);



    }
}
