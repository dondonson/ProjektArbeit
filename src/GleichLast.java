//Gruppe 109 
//Cedric Padeken 108022252630
//Henrique Elia Novais 108022232164
//Leon Lukas Grügelsberg 108022248789

import inf.v3d.obj.Arrow;
import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;

public class GleichLast {
    //Attribute
    private double anfangspunkt;
    private double endpunkt;
    private double kraft;
    private Balken balken;

    //Konstruktor
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
    public double berechneLeange(){
        return endpunkt-anfangspunkt;
    }
    public double berechneResultierende(){
        return berechneLeange()*kraft;
    }
    public double berechneAuflagerkraft1(){
        double ak1= berechneResultierende() - berechneAuflagerkraft2();
        return ak1;
    }
    public double berechneAuflagerkraft2(){
        double ak2= berechneResultierendeStandort() / balken.laenge * berechneResultierende();
        return ak2;
    }

    //Visualisierung
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
        Text tak1 = new Text("" + Balken.doubleRunden(berechneAuflagerkraft1(),3));
        v.addObject3D(tak1);
        tak1.setColor("blue");
        tak1.setOrigin(0.1, -balken.laenge / 3, 0);
        Text tak2 = new Text("" + Balken.doubleRunden(berechneAuflagerkraft2(),3));
        v.addObject3D(tak2);
        tak2.setColor("blue");
        tak2.setOrigin(balken.laenge + 0.1, -balken.laenge / 3, 0);
        tak1.setHeight(0.075 * balken.laenge);
        tak2.setHeight(0.075 * balken.laenge);
    }
    public double getAnfangspunkt() {
        return anfangspunkt;
    }
    public double getEndpunkt() {
        return endpunkt;
    }
    public double getKraft() {
        return kraft;
    }
}
