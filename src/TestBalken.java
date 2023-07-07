public class TestBalken {
    public static void main(String[] args) {

        System.out.println("Bitte geben Sie alle ihre Angaben in cm ein");
        Balken b1 = new Balken(Tastatur.liesDouble("Welche Größe hat der Balken ? "));
        System.out.println("Welche Art von Last wirkt auf dem Balken ?");
        System.out.println("Punktlast = 1");
        System.out.println("Gleichlasten = 2");
        System.out.println("Dreiecklasten = 3");
        System.out.println("Keine = 4");

        int n=Tastatur.liesInt("Bitte wählen sie die Entschprechende Zahl aus ");
        if(n==1){
            PunktLast p1 = new PunktLast(Tastatur.liesDouble("Auf Welche länge des Balkens befindet sich die Punktlast ? "),Tastatur.liesDouble("Wie stark ist die Kraft der Last ? " ));
            double ak1= p1.getOrt()/ b1.laenge* p1.getKraft();
            double ak2= p1.getKraft()-ak1;
            System.out.println("Auflagerkraft 1 ist "+ak1+" und Auflagerkraft 2 ist "+ak2);
        }
        else if(n==2){
            GleichLast g1 = new GleichLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Gleichlast ? "),Tastatur.liesDouble("Auf Welche länge des Balkens endet die Gleichlast ? "),Tastatur.liesDouble("Wie stark ist die Kraft der Last" ));
            double ak1= g1.BerechneResultierendeStandort()/ b1.laenge*g1.BerechneResultierende();
            double ak2= g1.BerechneResultierende()-ak1;
            System.out.println("Auflagerkraft 1 ist "+ak1+" und Auflagerkraft 2 ist "+ak2);
        }
        else if(n==3){
            DreieckLast d1 = new DreieckLast(Tastatur.liesDouble("Auf Welche länge des Balkens beginnt die Dreiecklast ? "),Tastatur.liesDouble("Auf Welche länge des Balkens endet die Gleichlast ? "),Tastatur.liesDouble("Wie stark ist die Kraft der Last" ),Tastatur.liesString("Wird das Dreieck größer ?"));
            double ak1= d1.BerechneResultierendeStandort()/ b1.laenge*d1.BerechneResultierende();
            double ak2= d1.BerechneResultierende()-ak1;
            System.out.println("Auflagerkraft 1 ist "+ak1+" und Auflagerkraft 2 ist "+ak2);
        }
        else{
            return;
        }
    }

}
