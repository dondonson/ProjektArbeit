public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    } 
    //Punktlast
            //Momentverlauf
            Polyline momentverlaufP = new Polyline();
            momentverlaufP.setVisible(true);
            momentverlaufP.setLinewidth(5);
            if (mengePunktkraefte == 1) {

                momentverlaufP.addVertex(0, 0, 0);
                momentverlaufP.addVertex(p[0].getOrt(), (-1*p[0].getKraft() * p[0].getOrt() * (b.laenge - p[0].getOrt())) / b.laenge, 0);
                momentverlaufP.addVertex(b.laenge, 0, 0);
            }else{
                momentverlaufP.addVertex(0, 0, 0);
                momentverlaufP.addVertex(p[0].getOrt(), -1*p[0].getKraft()*p[0].getOrt(), 0);
                momentverlaufP.addVertex(p[1].getOrt(), -1*p[1].getKraft()*(b.laenge-p[1].getOrt()), 0);
                momentverlaufP.addVertex(b.laenge, 0, 0);
            }
            momentverlaufP.setVisible(true);
            momentverlaufP.setColor("green");
            v.addObject3D(momentverlaufP);
           


            //Querkraft
            //Momentverlauf
            Polyline momentverlaufG = new Polyline();
            momentverlaufG.setVisible(true);
            momentverlaufG.setLinewidth(5);

            double Aa = A*g.getAnfangspunkt();
            double Bb = B*(b.laenge-g.getEndpunkt());
            momentverlaufG.addVertex(0,0,0);
            momentverlaufG.addVertex(g.getAnfangspunkt(), -1*Aa,0);
            for (double i = g.getAnfangspunkt(); i <= (g.berechnelange()*(1/2)+g.getAnfangspunkt())+0.1;i = i+0.1){
                momentverlaufG.addVertex(i, -1*ak1*i -((i-g.getAnfangspunkt())/2)*g.getKraft(),0);
            }
            for (double i = g.berechnelange()*(1/2)+g.getAnfangspunkt(); i <= g.getEndpunkt()-(g.berechnelange()*(1/2)+g.getAnfangspunkt())+0.1;i = i+0.1){
                momentverlaufG.addVertex(i, -1*ak2*(b.laenge-i) -(((g.getEndpunkt()-i)/2)+(b.laenge- g.getEndpunkt()))*g.getKraft(),0);
            }
            momentverlaufG.addVertex(g.getEndpunkt(), -1*Bb,0);
            momentverlaufG.addVertex(b.laenge,0,0);
            momentverlaufG.setColor("green");
            v.addObject3D(momentverlaufG);
