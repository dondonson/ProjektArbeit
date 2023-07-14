import inf.v3d.obj.Arrow;
import inf.v3d.obj.Text;
import inf.v3d.view.*;
public class TestBalken {
    public static void main(String[] args) {


        //Menü
        System.out.println("Bitte geben Sie alle ihre Angaben in cm ein");
        //Erstellung vom Balken
        Balken b1 = new Balken(Tastatur.liesDouble("Welche Größe hat der Balken ? "));
        Viewer v = new Viewer();
        b1.zu3D(v);
        //Auswahl
        System.out.println("Welche Art von Last wirkt auf dem Balken ?");
        System.out.println("Punktlast = 1");
        System.out.println("Gleichlasten = 2");
        System.out.println("Dreiecklasten = 3");


        int n = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
        if (n != 1 && n != 2 && n != 3) {
            do {
                System.out.println("Ungültige Option! Bitte gib eine gültige Option ein.");
                n = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
            } while (n != 1 && n != 2 && n != 3);
        }

        //Punktlast
        if (n == 1) {

            //Variabeln für das Berechnen der Resultierenden
            double resultierendeKraft = 0;
            double resultierendeOrt = 0;

            //Array List, so dass mehrere Punktkräfte eingetragen werden können
            int mengePunktkraefte = Tastatur.liesInt("Sollen 1 oder 2 Kräfte wirken ? ");
            PunktLast[] p = new PunktLast[mengePunktkraefte];
            for (int i = 0; i < p.length; i++) {
                p[i] = new PunktLast(Tastatur.liesDouble("Auf Welche länge des Balkens befindet sich die " + (i + 1) + " Punktlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "));
                resultierendeKraft = resultierendeKraft + p[i].getKraft();
            }
            if (mengePunktkraefte == 1) {
                resultierendeOrt = p[0].getOrt();
            } else {
                resultierendeOrt = p[0].getOrt() + (p[1].getKraft() / resultierendeKraft) * (p[1].getOrt() - p[0].getOrt());
            }

            //Auflagerkräfte berechnen mit neuer Array
            double ak1 = resultierendeOrt / b1.laenge * resultierendeKraft;
            double ak2 = resultierendeKraft - ak1;
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            if ((mengePunktkraefte == 1) && (resultierendeOrt == (b1.laenge / 2))) {
                System.out.println("Die Querkraft beträgt bis zur hälfte des Balkens +" + resultierendeKraft / 2 + "und danach bis zum ende -" + resultierendeKraft / 2);
            } else if (mengePunktkraefte == 1 && resultierendeOrt != (b1.laenge / 2)) {
                System.out.println("Die Querkraft beträgt bis " + resultierendeOrt + " +" + (resultierendeKraft * (b1.laenge - resultierendeOrt) / b1.laenge) + " und danach bis zum ende des Balkens -" + ((resultierendeKraft * resultierendeOrt) / b1.laenge));
            } else {
                System.out.println("Die Querkraft beträgt von 0 bis " + p[0].getOrt() + " +" + p[0].getKraft() + " von " + p[0].getOrt() + " bis " + p[1].getOrt() + " 0 und von " + p[1].getOrt() + " bis zum ende des Balkens -" + p[1].getKraft());
            }
            //Visualisierung
            if (mengePunktkraefte == 1) {
                p[0].zu3D(v);
            } else {
                p[0].zu3D(v);
                p[1].zu3D(v);
            }
            Text tak1 = new Text("" + ak1);
            v.addObject3D(tak1);
            tak1.setColor("blue");
            tak1.setOrigin(1, -4, 0);
            Text tak2 = new Text("" + ak2);
            v.addObject3D(tak2);
            tak2.setColor("blue");
            tak2.setOrigin(b1.laenge + 1, -4, 0);
         //Momentverlauf
            Polyline line;
            line = new Polyline();
            line.setVisible(true);
            line.setColoringByData(true);
            if (mengePunktkraefte == 1) {

                line.setLinewidth(3);

                line.addVertex(0, 0, 0);
                line.addVertex(p[0].getOrt(), (p[1].getKraft() * p[0].getOrt() * (b1.laenge - p[0].getOrt())) / b1.laenge, 0);
                line.addVertex(b1.laenge, 0, 0);
            }else {
                line.setLinewidth(4);
                line.addVertex(0, 0, 0);
                line.addVertex(p[0].getOrt(), 0, 0);
                line.addVertex(p[1].getOrt(), 0, 0);
                line.addVertex(b1.laenge, 0, 0);
            }
        }

        //Gleichlast
        else if (n == 2) {
            GleichLast g = new GleichLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Gleichlast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Gleichlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "));
            double ak1 = g.berechneResultierendeStandort() / b1.laenge * g.berechneResultierende();
            double ak2 = g.berechneResultierende() - ak1;
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            if (g.berechnelange() == 0) {
                System.out.println("Die Querkraft Verläuft Linear durch die Mitte des Balkens und beträgt und startet bei +" + (g.getKraft() * b1.laenge) / 2 + " und endet bei -" + (g.getKraft() * b1.laenge) / 2);
            } else if (g.berechnelange() != 0 && (g.getAnfangspunkt() == 0)) {
                double A = (((g.getKraft() * Math.pow(g.berechnelange(), 2)) / (2 * b1.laenge)) - (g.getKraft() * g.berechnelange()));
                double B = ((g.getKraft() * Math.pow(g.berechnelange(), 2)) / (2 * b1.laenge));
                System.out.println("Die Querkraft fällt Linear von +" + A + " und bleibt dann bei " + B);
            } else {
                double A = (g.getKraft() * g.berechnelange()) / b1.laenge * (g.berechnelange() / 2 + (b1.laenge - g.getEndpunkt()));
                double B = g.getKraft() * g.berechnelange() - A;
                System.out.println("Die Querkraft beträgt bis " + g.getAnfangspunkt() + " " + A + "und fällt dann linear auf " + B + " am Punkt " + g.getEndpunkt() + " und bleibt stetig bis zum ende des Balkens");
            }
            //Momentverlauf
            Polyline line;
            line = new Polyline();
            line.setVisible(true);
            line.setColoringByData(true);
            if (g.getAnfangspunkt() == 0&&g.getEndpunkt() ==b1.laenge){
                line.setLinewidth(10);
                for(int i= 0; i<6;i++) {
                    line.addVertex((i/10)*b1.laenge, (i/5)*(g.getKraft()*Math.pow(b1.laenge,2))/8, 0);
                }
                for(int i= 6; i<11;i++)
                {
                    line.addVertex((i/10)*b1.laenge, (10-i/5)*(g.getKraft()*Math.pow(b1.laenge,2))/8, 0);
                }

            
            
            //Visualisierung
            g.zu3D(v);
            Text tak1 = new Text("" + ak1);
            v.addObject3D(tak1);
            tak1.setColor("blue");
            tak1.setOrigin(1, -4, 0);
            Text tak2 = new Text("" + ak2);
            v.addObject3D(tak2);
            tak2.setColor("blue");
            tak2.setOrigin(b1.laenge + 1, -4, 0);

        } else if (n == 3) {
            DreieckLast d1 = new DreieckLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Dreiecklast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Dreiecklast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "), false);
            //Resultierende funktioniert nicht mehr
            double ak1 = d1.BerechneResultierendeStandort() / b1.laenge * d1.BerechneResultierende();
            double ak2 = d1.BerechneResultierende() - ak1;
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            d1.zu3D(v);
            Text tak1 = new Text("" + ak1);
            v.addObject3D(tak1);
            tak1.setColor("blue");
            tak1.setOrigin(1, -4, 0);
            Text tak2 = new Text("" + ak2);
            v.addObject3D(tak2);
            tak2.setColor("blue");
            tak2.setOrigin(b1.laenge + 1, -4, 0);
        }
        Arrow aak1 = new Arrow(0, -4, 0, 0, -0.1, 0);
        Arrow aak2 = new Arrow(b1.laenge, -4, 0, b1.laenge, -0.1, 0);
        aak1.setColor("blue");
        aak2.setColor("blue");
        v.addObject3D(aak1);
        v.addObject3D(aak2);
        aak1.setRadius(0.2);
        aak2.setRadius(0.2);
           v.setVisible(true);

    }
}
