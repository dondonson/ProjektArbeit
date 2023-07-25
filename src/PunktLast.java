//Gruppe 109 
//Cedric Padeken 108022252630
//Henrique Elia Novais 108022232164
//Leon Lukas Grügelsberg 108022248789

import inf.v3d.obj.*;
import inf.v3d.obj.Arrow;
import inf.v3d.view.*;


public class PunktLast {
    //Attribute
    private double ort;
    private double kraft;
    private Balken balken;

    //Konstruktor
    public PunktLast(double o,double k, Balken b){
        ort=o;
        kraft=k;
        balken=b;
    }

    //Getter und Setter
    public double getOrt() {
        return ort;
    }

    public double getKraft() {
        return kraft;
    }

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
