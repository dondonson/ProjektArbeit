import inf.v3d.obj.Arrow;
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

        Text t1= new Text(""+kraft);

        v.addObject3D(t1);
        t1.setColor("red");
        t1.setOrigin(laenge+1,4,0);
    }

}
