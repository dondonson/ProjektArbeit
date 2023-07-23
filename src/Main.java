import inf.v3d.obj.Arrow;
import inf.v3d.obj.Polyline;
import inf.v3d.obj.Text;
import inf.v3d.view.*;
import inf.v3d.obj.Polyline;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Viewer v = new Viewer();
        double ak1=0;
        double ak2=0;
        //Menü
        System.out.println("Bitte geben Sie alle ihre Angaben in Metern ein");
        //Erstellung vom Balken
        Balken b1 = new Balken(Tastatur.liesDouble("Welche Größe hat der Balken ? "));
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
            int mengePunktkraefte = Tastatur.liesInt("Wie viele Kräfte sollen Berechnet werden ? ");
            PunktLast[] p = new PunktLast[mengePunktkraefte];
            for (int i = 0; i < p.length; i++) {
                p[i] = new PunktLast(Tastatur.liesDouble("Auf Welche länge des Balkens befindet sich die " + (i + 1) + " Punktlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "),b1,mengePunktkraefte);
                resultierendeKraft = resultierendeKraft + p[i].getKraft();
            }

            // dieBerechnuing der Resultierenden geht nicht, aber ist denke ich auch nicht nötig
            if (mengePunktkraefte == 1) {
                resultierendeOrt = p[0].getOrt();
            } else {
                resultierendeOrt = p[0].getOrt() + (p[1].getKraft() / resultierendeKraft) * (p[1].getOrt() - p[0].getOrt());
            }

            //Auflagerkräfte berechnen mit neuer Array
           
            for (int i = 0; i < p.length; i++) {
                ak1 = ak1 + (b1.laenge-p[i].getOrt())*p[i].getKraft();
                ak2 = ak2 + p[i].getOrt()*p[i].getKraft();
            }

            ak1 = ak1/ b1.laenge;
            ak2 = ak2/ b1.laenge;
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            Polyline drawquerkraftP = new Polyline();

            drawquerkraftP.addVertex(0,0,0);
            drawquerkraftP.addVertex(0,ak1,0);
            drawquerkraftP.addVertex(p[0].getOrt(),ak1,0);

            double qP[] = new double[p.length];
            double x = 0;
            for (int i = 0; i < mengePunktkraefte; i++){
                drawquerkraftP.addVertex(p[i].getOrt(),ak1+x,0);
                qP[i] = ak1 + x - p[i].getKraft();
                x = x - p[i].getKraft();
                drawquerkraftP.addVertex(p[i].getOrt(), qP[i],0);
            }

            drawquerkraftP.addVertex(b1.laenge, ak1+x, 0);
            drawquerkraftP.addVertex(b1.laenge, 0,0);

            drawquerkraftP.setLinewidth(5);
            drawquerkraftP.setVisible(true);
            drawquerkraftP.setColor("yellow");
            v.addObject3D(drawquerkraftP);

            //Visualisierung
            for (int i = 0;i<mengePunktkraefte;i++){
                p[i].zu3D(v);
            }

            Text tak1 = new Text("" + ak1);
            v.addObject3D(tak1);
            tak1.setColor("blue");
            tak1.setOrigin(0.1, -b1.laenge / 3, 0);
            Text tak2 = new Text("" + ak2);
            v.addObject3D(tak2);
            tak2.setColor("blue");
            tak2.setOrigin(b1.laenge + 0.1, -b1.laenge / 3, 0);
            tak1.setHeight(0.075 * b1.laenge);
            tak2.setHeight(0.075 * b1.laenge);

            //Momentverlauf
            Polyline line;
            line = new Polyline();
            line.setVisible(true);
            line.setColoringByData(true);
            if (mengePunktkraefte == 1) {

                line.setLinewidth(3);

                line.addVertex(0, 0, 0);
                line.addVertex(p[0].getOrt(), (p[0].getKraft() * p[0].getOrt() * (b1.laenge - p[0].getOrt())) / b1.laenge, 0);
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
            GleichLast g = new GleichLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Gleichlast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Gleichlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "),b1);
             //Auflagerkräfte
            ak1=g.berechneAuflagerkraft1();
            ak2=g.berechneAuflagerkraft2();
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
            if (g.getAnfangspunkt() == 0&&g.getEndpunkt() ==b1.laenge) {
                line.setLinewidth(10);
                for (int i = 0; i < 6; i++) {
                    line.addVertex((i / 10) * b1.laenge, (i / 5) * (g.getKraft() * Math.pow(b1.laenge, 2)) / 8, 0);
                }
                for (int i = 6; i < 11; i++) {
                    line.addVertex((i / 10) * b1.laenge, (10 - i / 5) * (g.getKraft() * Math.pow(b1.laenge, 2)) / 8, 0);
                }
                //Visualisierung
                g.zu3D(v);
            }
            double A = (g.getKraft()*g.berechnelange()/b1.laenge*(g.berechnelange()/2+(b1.laenge-g.getEndpunkt())));
            double B = g.getKraft()*g.berechnelange()-A;

            Polyline querkraftG = new Polyline();

            querkraftG.addVertex(0,0,0);
            querkraftG.addVertex(0, A, 0);
            querkraftG.addVertex(g.getAnfangspunkt(),A,0);
            querkraftG.addVertex(g.getEndpunkt(), -B,0);
            querkraftG.addVertex(b1.laenge, -B, 0);
            querkraftG.addVertex(b1.laenge, 0,0);

            querkraftG.setLinewidth(5);
            querkraftG.setVisible(true);

            querkraftG.setColoringByData(true);
            v.addObject3D(querkraftG);

            //Visualisierung
            g.zu3D(v);

         } else if (n == 3) {
            System.out.println("Wird die Dreieckslast Größer oder Kleiner von links nach rechts? ");
            System.out.println("Größer = 1");
            System.out.println("Kleiner = 2");
            DreieckLast d1 = new DreieckLast(Tastatur.liesInt(),Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Dreiecklast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Dreiecklast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last ? "),b1);
            //Auflagerkräfte
            ak1 = d1.berechneAuflagerkraft1();
            ak2 = d1.berechneAuflagerkraft2();
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);
            //Visualiesierung
            d1.zu3D(v);
        }
        System.out.println("Wollen sie die Ergebnisse speichern?");
        System.out.println("Ja = 1");
        System.out.println("Nein = 2");
        int t = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
        if (t != 1 && t != 2 ) {
            do {
                System.out.println("Ungültige Option! Bitte gib eine gültige Option ein.");
                t = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
            } while (t != 1 && t != 2 );
        }
        if (t==1) {
            try {
                FileWriter fw = new FileWriter("Balken.txt");
                PrintWriter out = new PrintWriter(fw);
                out.println("Balken der Lange " + b1.laenge);
                out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);
                out.close();
                fw.close();
            } catch (Exception e) {
                System.out.println("Fehler beim Schreiben!");
            }
        }
        v.setVisible(true);
    }
}}
