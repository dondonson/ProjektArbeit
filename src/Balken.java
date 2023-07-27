//Gruppe 109 
//Cedric Padeken 108022252630
//Henrique Elia Novais 108022232164
//Leon Lukas Grügelsberg 108022248789

import inf.v3d.obj.*;
import inf.v3d.view.*;
public class Balken {
    //Attribute
    double laenge;

    //Konstruktor
    //bl=Balken Laenge
    public Balken(double bl){
        laenge=bl;
    }
    //Visualiesierung des Balken 
    //Alle Werte sind an die Lange des Balken gebunden damit die Visualiesierung immer ungefähr gleich aussieht
    public void zu3D(Viewer v){
        //Balken und Pfeile für die Auflagerkräfte
        Cylinder c= new Cylinder(0,0,0,laenge,0,0);
        c.setRadius(.02*laenge);
        v.addObject3D(c);
        c.setColor(153,153,153);
        Arrow aak1 = new Arrow(0, -laenge / 3, 0, 0, -0.01* laenge, 0);
        Arrow aak2 = new Arrow(laenge, -laenge / 3, 0, laenge, -0.01* laenge, 0);
        aak1.setColor("blue");
        aak2.setColor("blue");
        v.addObject3D(aak1);
        v.addObject3D(aak2);
        aak1.setRadius(0.01*laenge);
        aak2.setRadius(0.01*laenge);

        //Legende
        Text legendeTitel = new Text("Legende:");
        legendeTitel.setHeight(0.5);
        legendeTitel.setColor("black");
        legendeTitel.setOrigin(-10,6,0);
        v.addObject3D(legendeTitel);

        Text legendeInfo = new Text("Alle Angaben in kN");
        legendeInfo.setHeight(0.5);
        legendeInfo.setColor("black");
        legendeInfo.setOrigin(-10,5,0);
        v.addObject3D(legendeInfo);

        Text legendeKraft = new Text("Wirkende Kreafte");
        legendeKraft.setHeight(0.5);
        legendeKraft.setColor("red");
        legendeKraft.setOrigin(-10,4,0);
        v.addObject3D(legendeKraft);

        Text legendeQuerkraft = new Text("Querkraftverlauf");
        legendeQuerkraft.setHeight(0.5);
        legendeQuerkraft.setColor("yellow");
        legendeQuerkraft.setOrigin(-10,3,0);
        v.addObject3D(legendeQuerkraft);

        Text legendeMoment = new Text("Momentenverlauf");
        legendeMoment.setHeight(0.5);
        legendeMoment.setColor("green");
        legendeMoment.setOrigin(-10,2,0);
        v.addObject3D(legendeMoment);
    }
    public static double doubleRunden(double wert, int kommastelle){
        double x = Math.pow(10,kommastelle);
        return Math.rint(wert*x)/x;
    }
}

