import inf.v3d.obj.*;
import inf.v3d.view.*;
public class Balken {
    //Attribute
    double laenge;

    //Konstruktor
    //bl=Balken Lange
    public Balken(double bl){
    laenge=bl;
    }
    public void zu3D(Viewer v){
        Cylinder c= new Cylinder(0,0,0,laenge,0,0);
        c.setRadius(.2);
        v.addObject3D(c);
        c.setColor(153,153,153);
    }
}
