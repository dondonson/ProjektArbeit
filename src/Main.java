public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

         momentverlaufG.addVertex(g.getAnfangspunkt(), -1*Aa,0);
  for (double i = 0; i <= (g.berechnelange()*(1/2));i = i+0.1){
                momentverlaufG.addVertex(i+g.getAnfangspunkt(), -1*(ak1*(i+g.getAnfangspunkt()) -(i*g.getKraft()*(i/2+g.getAnfangspunkt()))),0);
            }
            for (double i = 0 ; i <= (g.berechnelange()*(1/2));i = i+0.1){
                momentverlaufG.addVertex(i+(g.berechnelange()*(1/2)+g.getAnfangspunkt()), -1*(ak2*(b.laenge-(i+g.berechnelange()*(1/2)+g.getAnfangspunkt())) -((g.getEndpunkt()-(i/2+g.berechnelange()*(1/2)+g.getAnfangspunkt()))*g.getKraft()*(b.laenge-(g.berechnelange()*(1/2)+g.getAnfangspunkt()+i/2)))),0);
            }
momentverlaufG.addVertex(g.getEndpunkt(), -1*Bb,0);

    -1*(ak1*i - (i*g.getKraft()*(i*0.5)))


          momentverlaufG.addVertex(0,0,0);

            for (double i = 0; i <= b.laenge/2;i = i+0.1){
                momentverlaufG.addVertex(i, -1*(ak1*i-i*g.getKraft()*i/2),0);
            }
            for (double i = 0 ; i <= b.laenge/2;i = i+0.1){
                momentverlaufG.addVertex(i+b.laenge/2, -1*(ak2*(b.laenge/2-i) -((b.laenge/2-i)/2)*(b.laenge/2-i)* g.getKraft()),0);
            }
           for (double i = 0; i <= g.berechnelange()/2;i = i+0.1){
                momentverlaufG.addVertex(i+Ap, -1*(ak1*(i+Ap)-i*g.getKraft()*(Ap+i/2)),0);
            }

            for (double i = 0 ; i <= g.berechnelange()/2;i = i+0.1){
                momentverlaufG.addVertex(i+A+g.berechnelange()/2, -1*(ak2*(b.laenge-Ap-g.berechnelange()/2-i) -((Ep-Ap-g.berechnelange()/2-i)/2*(Ep-Ap-g.berechnelange()/2)* g.getKraft())),0);
            }


      for (double i = 0; i <= g.berechnelange();i = i+0.1){
                momentverlaufG.addVertex(i+Ap, -1*(ak1*(i+Ap)-(i/2+Ap)*g.getKraft()*i),0);
            }
  double s1 = Ap/b.laenge;
            double s2 = (b.laenge-Ep)/b.laenge;
            double s3 = (g.berechnelange())/b.laenge;
            double gamma = (s1+s3)/2;
            double epsilon = 1- gamma;

 for (double i = g.getAnfangspunkt(); i <= A/ g.getKraft()-0.1;i = i+0.1){
                momentverlaufG.addVertex(i, -1*(ak1*i-(i- g.getAnfangspunkt())*g.getKraft()*((i-Ap)/2+Ap)),0);
            }
            momentverlaufG.addVertex(A/ g.getKraft(), -1*(1-s3/2)*gamma*epsilon*b.laenge* g.getKraft()*g.berechnelange(),0);
            for (double i = A/ g.getKraft() ; i <= g.getEndpunkt();i = i+0.1){
                momentverlaufG.addVertex(i, -1*(ak2*(b.laenge-i) -(b.laenge- g.getEndpunkt()+ (g.getEndpunkt()-i)/2)* (g.getEndpunkt()-i)* g.getKraft()),0);
            }


    
