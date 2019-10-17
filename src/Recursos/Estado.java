package Recursos;

import java.util.ArrayList;

public class Estado {
    private Cube cubo;

    public Estado(Cube cubo) {
        this.cubo = cubo;
    }

    public Cube getCubo() {
        return cubo;
    }

    public void setCubo(Cube cubo) {
        this.cubo = cubo;
    }
    public boolean objetivo(){
        return cubo.objetivo();
    }

    public ArrayList<Sucesor> sucesores(){
        String ejes="BbDdLl";
        ArrayList<Sucesor> sucesores=new ArrayList<Sucesor>();

        for (int i=0;i<ejes.length();i++){
            for(int j=0;j<cubo.getSize();j++){
                String mov=(ejes.charAt(i)+""+j);
                Cube cuboCopia =cubo.clone();
                cuboCopia.movimientos(mov);
                if(Character.isUpperCase(ejes.charAt(i))){
                    sucesores.add(new Sucesor(mov,cuboCopia,1));
                }else{
                    sucesores.add(new Sucesor(mov,cuboCopia,2));
                }
            }
        }
        return sucesores;
    }
}
