public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


  for (double i = 0; i <= (g.berechnelange()*(1/2));i = i+0.1){
                momentverlaufG.addVertex(i+g.getAnfangspunkt(), -1*(ak1*(i+g.getAnfangspunkt()) -(i*g.getKraft()*(i/2+g.getAnfangspunkt()))),0);
            }
            for (double i = 0 ; i <= (g.berechnelange()*(1/2));i = i+0.1){
                momentverlaufG.addVertex(i+(g.berechnelange()*(1/2)+g.getAnfangspunkt()), -1*(ak2*(b.laenge-(i+g.berechnelange()*(1/2)+g.getAnfangspunkt())) -((g.getEndpunkt()-(i/2+g.berechnelange()*(1/2)+g.getAnfangspunkt()))*g.getKraft()*(b.laenge-(g.berechnelange()*(1/2)+g.getAnfangspunkt()+i/2)))),0);
            }
