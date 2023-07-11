import inf.v3d.obj.*;
import inf.v3d.view.*;


public class PunktLast {
    //Attribute
    private double ort;
    private double kraft;

    //Konstruktor
    public PunktLast(double o,double k){
        ort=o;
        kraft=k;
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
    //Visualiesierug der Last
    public void zu3D(Viewer v){
        Cylinder c1 = new Cylinder(ort,0.3,0,ort,4,0);
        Cylinder c2 = new Cylinder(ort-1,1.4,0,ort+0.1,0.4,0);
        Cylinder c3 = new Cylinder(ort+1,1.4,0,ort-0.1,0.4,0);
        c1.setColor("red");
        c2.setColor("red");
        c3.setColor("red");
        v.addObject3D(c1);
        v.addObject3D(c2);
        v.addObject3D(c3);
        c1.setRadius(.2);
        c2.setRadius(.2);
        c3.setRadius(.2);
        Text t1= new Text(""+kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(ort+1,4,0);
    }
}
