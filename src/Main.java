//Gruppe 109 
//Cedric Padeken 108022252630
//Henrique Elia Novais 108022232164
//Leon Lukas Grügelsberg 108022248789

import inf.v3d.obj.Polyline;
import inf.v3d.obj.Text;
import inf.v3d.view.*;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Viewer v = new Viewer();

        //Werte zum speichern
        double ak1=0;
        double ak2=0;
        double extremaortQ1 =0;
        double extremaortQ2 =0;
        double extramaM=0;
        double nullstelle=0;

        //Menü
        System.out.println("Bitte geben Sie alle ihre Angaben in Metern ein");
        //Erstellung vom Balken
        Balken b = new Balken(Tastatur.liesDouble("Welche Größe hat der Balken ? "));
        b.zu3D(v);
        //Auswahl
        System.out.println("Welche Art von Last wirkt auf dem Balken ?");
        System.out.println("Punktleaste = 1");
        System.out.println("Gleichlast = 2");
        System.out.println("Dreieckslast = 3");

        int n = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
        System.out.println();

        //Kontrolle zur Eingabe
        while (n != 1 && n != 2 && n != 3) {
                System.out.println("Ungültige Option! Bitte gib eine gültige Option ein.");
                n = Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
                System.out.println();
            }

        //Punktlast
        if (n == 1) {

            //Variabeln für das Berechnen der Resultierenden
            double resultierendeKraft = 0;

            //Array für das mehrere Punktkräfte
            int mengePunktkraefte = Tastatur.liesInt("Wie viele Kräfte sollen Berechnet werden ? ");
            System.out.println("Bitte geben sie die Kräfte von links nach rechts an.");
            PunktLast[] p = new PunktLast[mengePunktkraefte];
            for (int i = 0; i < p.length; i++) {
                p[i] = new PunktLast(Tastatur.liesDouble("Auf Welche länge des Balkens befindet sich die " + (i + 1) + " Punktlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last in kN ? "), b);
                resultierendeKraft = resultierendeKraft + p[i].getKraft();
            }

            //Auflagerkräfte berechnen mit neuer Array
            for (int i = 0; i < p.length; i++) {
                ak1 = ak1 + (b.laenge-p[i].getOrt())*p[i].getKraft();
                ak2 = ak2 + p[i].getOrt()*p[i].getKraft();
            }
            ak1 = ak1/ b.laenge;
            ak2 = ak2/ b.laenge;
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            Polyline drawquerkraftP = new Polyline();
            drawquerkraftP.setLinewidth(5);
            drawquerkraftP.setVisible(true);
            drawquerkraftP.setColor("yellow");

            drawquerkraftP.addVertex(0,0,0);
            drawquerkraftP.addVertex(0,ak1,0);
            drawquerkraftP.addVertex(p[0].getOrt(),ak1,0);
            double[] qP = new double[p.length];
            double x = 0;
            for (int i = 0; i < mengePunktkraefte; i++){
                drawquerkraftP.addVertex(p[i].getOrt(),ak1+x,0);
                qP[i] = ak1 + x - p[i].getKraft();
                x = x - p[i].getKraft();
                drawquerkraftP.addVertex(p[i].getOrt(), qP[i],0);
            }
            drawquerkraftP.addVertex(b.laenge, ak1+x, 0);
            drawquerkraftP.addVertex(b.laenge, 0,0);
            v.addObject3D(drawquerkraftP);

            //Momentverlauf
            Polyline drawmomentP = new Polyline();
            drawmomentP.setLinewidth(5);
            drawmomentP.setVisible(true);
            drawmomentP.setColor("green");

            drawmomentP.addVertex(0,0,0);
            drawmomentP.addVertex(p[0].getOrt(),ak1*p[0].getOrt(),0);
            double lvm = 0; //lvm = leitvariable momentenverlauf
            for (int i = 0; i < p.length; i++){
                if (i<p.length-1){
                    drawmomentP.addVertex(p[i].getOrt(), ak1*p[i].getOrt()+lvm, 0);
                    lvm = lvm-p[i].getKraft()*(p[i+1].getOrt()-p[i].getOrt());
                } else {
                    drawmomentP.addVertex(p[i].getOrt(), ak1*p[i].getOrt()+lvm, 0);
                    lvm = lvm-p[i].getKraft()*(b.laenge-p[i].getOrt());
                }
            }
            drawmomentP.addVertex(b.laenge, 0,0);

            v.addObject3D(drawmomentP);

            //Visualisierung
            for (int i = 0;i<mengePunktkraefte;i++){
                p[i].zu3D(v);
            }

            Text tak1 = new Text(" " + ak1);
            v.addObject3D(tak1);
            tak1.setColor("blue");
            tak1.setOrigin(0.1, -b.laenge / 3, 0);
            Text tak2 = new Text(" " + ak2);
            v.addObject3D(tak2);
            tak2.setColor("blue");
            tak2.setOrigin(b.laenge + 0.1, -b.laenge / 3, 0);
            tak1.setHeight(0.075 * b.laenge);
            tak2.setHeight(0.075 * b.laenge);

        }

        //Gleichlast
        else if (n == 2) {
            GleichLast g = new GleichLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Gleichlast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Gleichlast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last in kN ? "), b);
             //Auflagerkräfte
            ak1=g.berechneAuflagerkraft1();
            ak2=g.berechneAuflagerkraft2();
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            //Darstellung
            double A = (g.getKraft()*g.berechneLeange()/ b.laenge*(g.berechneLeange()/2+(b.laenge-g.getEndpunkt())));
            double B = g.getKraft()*g.berechneLeange()-A;

            Polyline querkraftG = new Polyline();
            querkraftG.setVisible(true);
            querkraftG.setLinewidth(5);
            querkraftG.setColor("yellow");

            querkraftG.addVertex(0,0,0);
            querkraftG.addVertex(0, A, 0);
            querkraftG.addVertex(g.getAnfangspunkt(),A,0);
            querkraftG.addVertex(g.getEndpunkt(), -B,0);
            querkraftG.addVertex(b.laenge, -B, 0);
            querkraftG.addVertex(b.laenge, 0,0);

            v.addObject3D(querkraftG);

            //Momentverlauf
            Polyline momentverlaufG = new Polyline();
            momentverlaufG.setVisible(true);
            momentverlaufG.setLinewidth(5);
            momentverlaufG.setColor("green");

            double Aa = ak1*g.getAnfangspunkt();
            double Bb = ak2*(b.laenge-g.getEndpunkt());

            momentverlaufG.addVertex(0,0,0);
            momentverlaufG.addVertex(g.getAnfangspunkt(), -1*Aa,0);
            for (double i = 0; i <= g.berechneLeange(); i = i+0.1){
                momentverlaufG.addVertex(i + g.getAnfangspunkt(), -1*(ak1*(i+g.getAnfangspunkt()) - (i*g.getKraft()*(i/2))),0);
            }
            momentverlaufG.addVertex(g.getEndpunkt(), -1*Bb,0);
            momentverlaufG.addVertex(b.laenge,0,0);

            v.addObject3D(momentverlaufG);

            //Variabeln für das Speichern
            extremaortQ1 = extremaortQ1 + g.getAnfangspunkt();
            extremaortQ2 = extremaortQ2 + g.getEndpunkt();
            nullstelle = A/g.getKraft();
            extramaM = extramaM + (g.getKraft()*Math.pow(g.berechneLeange(), 2)/8);

            //Visualisierung
                g.zu3D(v);

         //DreieckLast
         } else {
            System.out.println("Wird die Dreieckslast Größer oder Kleiner von links nach rechts? ");
            System.out.println("Größer = 1");
            System.out.println("Kleiner = 2");
            int ausrichtung = Tastatur.liesInt();

            //Kontrolle zur Eingabe
            while (ausrichtung != 1 && ausrichtung != 2) {
                System.out.println("Ungültige Option! Bitte gib eine gültige Option ein.");
                ausrichtung = Tastatur.liesInt("größer (1) oder kleiner (2): ");
            }

            DreieckLast d = new DreieckLast(ausrichtung, Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Dreiecklast ? "), Tastatur.liesDouble("Auf Welche länge des Balkens endet die Dreiecklast ? "), Tastatur.liesDouble("Wie stark ist die Kraft der Last in kN ? "), b);

            //Auflagerkräfte
            ak1 = d.berechneAuflagerkraft1();
            ak2 = d.berechneAuflagerkraft2();
            System.out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);

            //Querkraft
            Polyline drawquerkraftD = new Polyline();
            drawquerkraftD.setLinewidth(5);
            drawquerkraftD.setVisible(true);
            drawquerkraftD.setColor("yellow");

            double A = (d.getKraft() / d.berechneLeange()) / 2;

            //steigende Kraft
            if (d.getAusrichtung() == 1) {
                drawquerkraftD.addVertex(0, 0, 0);
                drawquerkraftD.addVertex(0, ak1, 0);
                drawquerkraftD.addVertex(d.getAnfangspunkt(), ak1, 0);
                for (double i = 0; i <= d.berechneLeange(); i = i + 0.1) {
                    i = Balken.doubleRunden(i, 1);
                    drawquerkraftD.addVertex(d.getAnfangspunkt() + i, ak1 - A * Math.pow(i, 2), 0);
                }
                drawquerkraftD.addVertex(b.laenge, ak1 - A * Math.pow(d.berechneLeange(), 2), 0);
                drawquerkraftD.addVertex(b.laenge, 0, 0);
            //fallende Kraft
            } else {
                drawquerkraftD.addVertex(b.laenge, 0, 0);
                drawquerkraftD.addVertex(b.laenge, -ak2, 0);
                drawquerkraftD.addVertex(d.getEndpunkt(), -ak2, 0);
                for (double i = 0; i <= d.berechneLeange(); i = i + 0.1) {
                    i = Balken.doubleRunden(i, 1);
                    drawquerkraftD.addVertex(d.getEndpunkt() - i, -ak2 + A * Math.pow(i, 2), 0);
                }
                drawquerkraftD.addVertex(0, -ak2 + A * Math.pow(d.berechneLeange(), 2), 0);
                drawquerkraftD.addVertex(0, 0, 0);
            }

            v.addObject3D(drawquerkraftD);

            //Momentverlauf
            Polyline momentverlaufD = new Polyline();
            momentverlaufD.setLinewidth(5);
            momentverlaufD.setColor("green");
            momentverlaufD.setVisible(true);

            //steigende Kraft
            if (d.getAusrichtung() == 1){
                momentverlaufD.addVertex(0, 0, 0);
                momentverlaufD.addVertex(d.getAnfangspunkt(), -1 * (ak1 * d.getAnfangspunkt()), 0);
                for (double i = 0; i <= b.laenge - d.getAnfangspunkt(); i = i + 0.1) {
                    double C = i + d.getAnfangspunkt();
                    i = Balken.doubleRunden(i, 1);
                    if (i <= d.berechneLeange()) {
                        momentverlaufD.addVertex(C, (ak1 * C - (d.getKraft()*Math.pow(i,3))/(6*d.berechneLeange())) * -1, 0);
                    } else {
                        momentverlaufD.addVertex(C, -1 * (ak1 * C - d.berechneResultierende()*(C-d.berechneResultierendeStandort())), 0);
                    }
                }
                momentverlaufD.addVertex(b.laenge, 0, 0);

            //fallende Kraft
            } else {
                momentverlaufD.addVertex(b.laenge,0,0);
                momentverlaufD.addVertex(d.getEndpunkt(), -1 * (ak2* (b.laenge-d.getEndpunkt())),0);
                for (double i = 0; i <= d.getEndpunkt(); i = i + 0.1) {
                    double C = d.getEndpunkt() - i;
                    i = Balken.doubleRunden(i, 1);
                    if (i <= d.berechneLeange()) {
                        momentverlaufD.addVertex(C, (ak2 * (b.laenge-C) - (d.getKraft()*Math.pow(i,3))/(6*d.berechneLeange())) * -1, 0);
                    } else {
                        momentverlaufD.addVertex(C, -1 * (ak2 * (b.laenge-C) - (d.berechneResultierende() * (d.berechneResultierendeStandort() - C))), 0);
                    }
                }
                momentverlaufD.addVertex(0, 0, 0);
            }
            v.addObject3D(momentverlaufD);

            //Variabeln für das Speichern
            extremaortQ1 = extremaortQ1 + d.getAnfangspunkt();
            extremaortQ2 = extremaortQ2 + d.getEndpunkt();
            nullstelle = d.berechneLeange()/Math.sqrt(3)+d.getAnfangspunkt();
            extramaM = extramaM + (d.getKraft()*Math.pow(d.berechneLeange(), 2)/(9*Math.sqrt(3)));

            //Visualiesierung
            d.zu3D(v);
        }

        System.out.println();
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
                out.println("Balken der Lange " + b.laenge);
                out.println("Auflagerkraft 1 ist " + ak1 + " und Auflagerkraft 2 ist " + ak2);
                if (n == 2){
                    out.println("Extrema des Querkraftverlaufs:");
                    out.println("Für x von 0 bis "+ extremaortQ1 + " beträgt die Querkraft stetig " + ak1 + "kN");
                    out.println("Für x von "+extremaortQ1+ " bis "+ extremaortQ2 +" fällt sie linear auf -"+ ak2+ "kN");
                    out.println("Für x von "+ extremaortQ2 + " bis "+ b.laenge + " beträgt sie stetig -" +ak2+ "kN");
                    out.println("Nullstelle bei x = "+ Balken.doubleRunden(nullstelle,3));
                    out.println();
                    out.println("Extrema des Momentenverlaufs bei x = " +Balken.doubleRunden(nullstelle,3)+ " mit einer Kraft von "+ Balken.doubleRunden(extramaM,3) + "kN");
                } else if (n==3){
                    out.println("Extrema des Querkraftverlaufs:");
                    out.println("Für x von 0 bis "+ extremaortQ1 + " beträgt die Querkraft stetig -" + ak1 + "kN");
                    out.println("Für x von "+extremaortQ1+ " bis "+ extremaortQ2 +" fällt sie in form einer quadratischen funktion auf -"+ ak2+ "kN");
                    out.println("Für x von "+ extremaortQ2 + " bis "+ b.laenge + " beträgt sie stetig " +ak2+ "kN");
                    out.println("Nullstelle bei x = "+ Balken.doubleRunden(nullstelle,3));
                    out.println();
                    out.println("Extrema des Momentenverlaufs bei x = " +Balken.doubleRunden(nullstelle,3)+ " mit einer Kraft von "+ Balken.doubleRunden(extramaM,3) + "kN");
                }
                out.close();
                fw.close();
            } catch (Exception e) {
                System.out.println("Fehler beim Schreiben!");
            }
        }
        v.setVisible(true);
    }
}
