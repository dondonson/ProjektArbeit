import inf.v3d.obj.Arrow;
import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;

public class GleichLast {
    //Attribute
    private double anfangspunkt;
    private double endpunkt;
    private double kraft;

    //Konstruktor
    //
    public GleichLast(double gla,double gle, double k){
        anfangspunkt=gla;
        endpunkt=gle;
        kraft =k;
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
    //Visualisierung
    public void zu3D(Viewer v){
        Cylinder c1 = new Cylinder(anfangspunkt,4,0,endpunkt,4,0);
        Arrow a1 = new Arrow(anfangspunkt,4,0,anfangspunkt,0.1,0);
        Arrow a2 = new Arrow(endpunkt,4,0,endpunkt,0.1,0);
        Arrow a3 = new Arrow(berechneResultierendeStandort(),4,0,berechneResultierendeStandort(),0.1,0);
        Text t1= new Text(""+kraft);
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
        c1.setRadius(0.2);
        a1.setRadius(0.2);
        a2.setRadius(0.2);
        a3.setRadius(0.2);
        t1.setOrigin(endpunkt+1,4,0);
    }
}
