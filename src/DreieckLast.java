//Gruppe 109 
//Cedric Padeken 108022252630
//Henrique Elia Novais 108022232164
//Leon Lukas Grügelsberg 108022248789

import inf.v3d.obj.Arrow;
import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DreieckLast {
    public double getKraft() {
        return kraft;
    }

    public void setKraft(double kraft) {
        this.kraft = kraft;
    }

    public double getAnfangspunkt() {
        return anfangspunkt;
    }

    public double getEndpunkt() {
        return endpunkt;
    }

    public void setEndpunkt(double endpunkt) {
        this.endpunkt = endpunkt;
    }

    public void setAnfangspunkt(double anfangspunkt) {
        this.anfangspunkt = anfangspunkt;
    }

    //Attribute
    private int ausrichtung;
    private double anfangspunkt;
    private double endpunkt;
    double kraft;
    private Balken balken;

    //Konstruktor

    public DreieckLast(int a,double ap,double ep, double k,Balken b){
        ausrichtung=a;
        anfangspunkt=ap;
        endpunkt=ep;
        kraft=k;
        balken=b;
    }
    //Methoden
    public double berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double BerechneResultierendeStandort(){
        if(ausrichtung == 1){
            return (berechnelange()) * 2 / 3;
        } else {
            return (berechnelange()) * 1 / 3;
        }
    }
    public double berechneResultierende(){
        return berechnelange()*0.5*kraft;
    }
    public double berechneAuflagerkraft1(){
        double ak1= BerechneResultierendeStandort() / balken.laenge * berechneResultierende();
        return ak1;
    }
    public double berechneAuflagerkraft2(){
        double ak2= berechneResultierende() - berechneAuflagerkraft1();
        return ak2;
    }
    //Die Methode Runden ist für die Visualiesierung Wichtig da sonst die Werte der Kräfte teilweise zu viele Nachkommastellen haben wodurch sie zu viel Platz einnehmen
    public static double runden(double wert, int stellen){
        if (stellen <0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(wert);
        bd = bd.setScale(stellen, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //Visualisierung
    //C1 ist der Cyclinder der die Pfeile zusammen hält
    //Verschiedene Farben um unterschiedliche Kräfte zu Visualiesieren 
    public void zu3D(Viewer v){
        Cylinder c1;
        Arrow a1;

        if (ausrichtung==1) {
            c1 = new Cylinder(anfangspunkt, 0, 0, endpunkt, balken.laenge/3, 0);
            a1 = new Arrow(endpunkt, balken.laenge/3, 0, endpunkt, 0.01* balken.laenge, 0);

        } else {
            c1 = new Cylinder(anfangspunkt, balken.laenge/3, 0, endpunkt, 0, 0);
            a1 = new Arrow(anfangspunkt, balken.laenge/3, 0, anfangspunkt, 0.01* balken.laenge, 0);
        }
        v.addObject3D(a1);
        a1.setColor("red");
        a1.setRadius(0.01* balken.laenge);
        v.addObject3D(c1);
        c1.setColor("red");
        c1.setRadius(0.01* balken.laenge);
        Arrow a2 = new Arrow(berechnelange() / 2 + anfangspunkt, balken.laenge/6, 0, berechnelange() / 2 + anfangspunkt, 0.01* balken.laenge, 0);
        v.addObject3D(a2);
        a2.setColor("red");
        a2.setRadius(0.01* balken.laenge);
        Text t1 = new Text("" + kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(endpunkt + 0.1, balken.laenge/3, 0);
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
