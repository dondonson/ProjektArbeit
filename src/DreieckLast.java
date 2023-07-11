import inf.v3d.obj.Cylinder;
import inf.v3d.obj.Text;
import inf.v3d.view.Viewer;

public class DreieckLast {
    //Attribute
    double anfangspunkt;
    double endpunkt;
    double kraft;
    String ausrichtung;

    //Konstruktor
    //
    public DreieckLast(double dla,double dle, double k, String a){
        anfangspunkt=dla;
        endpunkt=dle;
        kraft=k;
        ausrichtung=a;
    }
    //Methoden
    public double BerechneResultierendeStandort(){
        if(ausrichtung=="ja"||ausrichtung=="Ja"){
            return (anfangspunkt + endpunkt) * 2 / 3;
        } else if(ausrichtung =="nein"||ausrichtung=="Nein") {
            return (anfangspunkt + endpunkt) * 1 / 3;
        }
        return BerechneResultierendeStandort();
    }
    public double Berechnelange(){
        return endpunkt-anfangspunkt;
    }
    public double BerechneResultierende(){
        return Berechnelange()*0.5*kraft;
    }
    //Visualisierung
    //noch nocht ganz ausgereift
    public void zu3D(Viewer v){
        Cylinder c1 = new Cylinder(0,0.3,0,0,4,0);
        Cylinder c2 = new Cylinder(-1,1.4,0,0.1,0.4,0);
        Cylinder c3 = new Cylinder(1,1.4,0,0.1,0.4,0);
        Cylinder c4 = new Cylinder(laenge,0.3,0,laenge,4,0);
        Cylinder c5 = new Cylinder(laenge-1,1.4,0,laenge+0.1,0.4,0);
        Cylinder c6 = new Cylinder(laenge+1,1.4,0,laenge-0.1,0.4,0);
        Cylinder c7 = new Cylinder(BerechneResultierendeStandort(),0.3,0,BerechneResultierendeStandort(),4,0);
        Cylinder c8 = new Cylinder(BerechneResultierendeStandort()-1,1.4,0,BerechneResultierendeStandort()+0.1,0.4,0);
        Cylinder c9 = new Cylinder(BerechneResultierendeStandort()+1,1.4,0,BerechneResultierendeStandort()-0.1,0.4,0);
        Cylinder c10 = new Cylinder(0,0,0,laenge,4,0);
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
        t1.setOrigin(laenge+1,4,0);
    }

}
