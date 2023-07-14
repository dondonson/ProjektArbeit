   public double berechneAuflagerkraft1(){
        double ak1= BerechneResultierendeStandort() / balken.laenge * berechneResultierende();
        return ak1;
    }
    public double berechneAuflagerkraft2(){
        double ak2= berechneResultierende() - berechneAuflagerkraft1();
        return ak2;
    }
    //Visualisierung


   public void zu3D(Viewer v){
         Cylinder c1;
         Arrow a1;

    if (ausrichtung) {
         c1 = new Cylinder(anfangspunkt, 0, 0, endpunkt, balken.laenge/3, 0);
         a1 = new Arrow(endpunkt, balken.laenge/3, 0, endpunkt, 0.01* balken.laenge, 0);

    } else {
         c1 = new Cylinder(anfangspunkt, balken.laenge/3, 0, endpunkt, 0, 0);
         a1 = new Arrow(anfangspunkt, balken.laenge/3, 0, anfangspunkt, 0.01* balken.laenge, 0);
    }
       v.addObject3D(a1);
       a1.setColor("red");
       a1.setRadius(0.01* balken.laenge);
       v.addObject3D(c1);
       c1.setColor("red");
       c1.setRadius(0.01* balken.laenge);
       Arrow a2 = new Arrow(berechnelange() / 2 + anfangspunkt, balken.laenge/6, 0, berechnelange() / 2 + anfangspunkt, 0.01* balken.laenge, 0);
            v.addObject3D(a2);
            a2.setColor("red");
            a2.setRadius(0.01* balken.laenge);
            Text t1 = new Text("" + kraft);
            v.addObject3D(t1);
            t1.setColor("red");
            t1.setOrigin(endpunkt + 0.1, balken.laenge/3, 0);
            t1.setHeight(0.075 * balken.laenge);
       Text tak1 = new Text("" + berechneAuflagerkraft1());
       v.addObject3D(tak1);
       tak1.setColor("blue");
       tak1.setOrigin(0.1, -balken.laenge / 3, 0);
       Text tak2 = new Text("" + berechneAuflagerkraft2());
       v.addObject3D(tak2);
       tak2.setColor("blue");
       tak2.setOrigin(balken.laenge + 0.1, -balken.laenge / 3, 0);
       tak1.setHeight(0.075 * balken.laenge);
       tak2.setHeight(0.075 * balken.laenge);
            }

}
