import inf.v3d.obj.Arrow;
import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;

public class DreieckLast {
    //Attribute
    private double anfangspunkt;
    private double endpunkt;
    double kraft;
    boolean ausrichtung;

    //Konstruktor

    public DreieckLast(double ap,double ep, double k, boolean a){
        anfangspunkt=ap;
        endpunkt=ep;
        kraft=k;
        ausrichtung=a;
    }
    //Methoden
    public double berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double BerechneResultierendeStandort(){
        if(ausrichtung = true){
            return (berechnelange()) * 2 / 3;
        } else {
            return (berechnelange()) * 1 / 3;
        }
    }

    public double BerechneResultierende(){
        return berechnelange()*0.5*kraft;
    }
    //Visualisierung
    //noch nocht ganz ausgereift
    public void zu3D(Viewer v){
    if (ausrichtung=true){
        Cylinder c1 = new Cylinder(anfangspunkt,0,0,endpunkt,4,0);
        Arrow a1 = new Arrow(endpunkt,4,0,endpunkt,0.1,0);
        v.addObject3D(a1);
        a1.setColor("red");
        a1.setRadius(0.2);
        v.addObject3D(c1);
        c1.setColor("red");
        c1.setRadius(0.2);
    } else {
        Cylinder c1 = new Cylinder(anfangspunkt,4,0,endpunkt,0,0);
        Arrow a1 = new Arrow(anfangspunkt,4,0,anfangspunkt,0.1,0);
        v.addObject3D(a1);
        a1.setColor("red");
        a1.setRadius(0.2);
        v.addObject3D(c1);
        c1.setColor("red");
        c1.setRadius(0.2);
    }
        Arrow a2 = new Arrow(berechnelange()/2+anfangspunkt,2,0,berechnelange()/2+anfangspunkt,0.1,0);
        v.addObject3D(a2);
        a2.setColor("red");
        a2.setRadius(0.2);
        Text t1= new Text(""+kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(endpunkt+1,4,0);
    }

}
