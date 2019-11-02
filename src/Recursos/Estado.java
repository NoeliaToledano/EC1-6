package Recursos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    public Queue<Sucesor> sucesores(){
        String ejes="BbDdLl";
        Queue<Sucesor> sucesores=new LinkedList<Sucesor>();

        for (int i=0;i<ejes.length();i++){
            for(int j=0;j<cubo.getSize();j++){
                String mov=(ejes.charAt(i)+""+j);
                Cube cuboCopia =cubo.clone();
                cuboCopia.movimientos(mov);
                if(Character.isUpperCase(ejes.charAt(i))){
                    sucesores.add(new Sucesor(mov,new Estado(cuboCopia),1));
                }else{
                    sucesores.add(new Sucesor(mov,new Estado(cuboCopia),1));
                }
            }
        }
        return sucesores;
    }
}
