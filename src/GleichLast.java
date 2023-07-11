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
        Cylinder c1 = new Cylinder(anfangspunkt,0.3,0,anfangspunkt,4,0);
        Cylinder c2 = new Cylinder(anfangspunkt-1,1.4,0,anfangspunkt+0.1,0.4,0);
        Cylinder c3 = new Cylinder(anfangspunkt+1,1.4,0,anfangspunkt-0.1,0.4,0);
        Cylinder c4 = new Cylinder(endpunkt,0.3,0,endpunkt,4,0);
        Cylinder c5 = new Cylinder(endpunkt-1,1.4,0,endpunkt+0.1,0.4,0);
        Cylinder c6 = new Cylinder(endpunkt+1,1.4,0,endpunkt-0.1,0.4,0);
        Cylinder c7 = new Cylinder(berechneResultierendeStandort(),0.3,0,berechneResultierendeStandort(),4,0);
        Cylinder c8 = new Cylinder(berechneResultierendeStandort()-1,1.4,0,berechneResultierendeStandort()+0.1,0.4,0);
        Cylinder c9 = new Cylinder(berechneResultierendeStandort()+1,1.4,0,berechneResultierendeStandort()-0.1,0.4,0);
        Cylinder c10 = new Cylinder(anfangspunkt,4,0,endpunkt,4,0);
        c1.setColor("red");
        c2.setColor("red");
        c3.setColor("red");
        c4.setColor("red");
        c5.setColor("red");
        c6.setColor("red");
        c7.setColor("red");
        c8.setColor("red");
        c9.setColor("red");
        c10.setColor("red");
        v.addObject3D(c1);
        v.addObject3D(c2);
        v.addObject3D(c3);
        v.addObject3D(c4);
        v.addObject3D(c5);
        v.addObject3D(c6);
        v.addObject3D(c7);
        v.addObject3D(c8);
        v.addObject3D(c9);
        v.addObject3D(c10);
        c1.setRadius(.2);
        c2.setRadius(.2);
        c3.setRadius(.2);
        c4.setRadius(.2);
        c5.setRadius(.2);
        c6.setRadius(.2);
        c7.setRadius(.2);
        c8.setRadius(.2);
        c9.setRadius(.2);
        c10.setRadius(.2);
        Text t1= new Text(""+kraft);
        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(endpunkt+1,4,0);
    }
}
