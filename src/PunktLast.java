import inf.v3d.obj.*;
import inf.v3d.obj.Arrow;
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
        Arrow a1 = new Arrow(ort,4,0,ort,0.1,0);
        a1.setColor("red");
        v.addObject3D(a1);
        a1.setRadius(0.2);
        Text t1= new Text(""+kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(ort+1,4,0);
    }
}
