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
