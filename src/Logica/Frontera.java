package Logica;

import Recursos.Sucesor;

import java.util.PriorityQueue;

public class Frontera {

        private static PriorityQueue<Sucesor> fronteraPQ;
        private static Frontera miFrontera;

        public static Frontera getFrontera(){
            if(miFrontera==null){
                miFrontera=new Frontera();
            }
            return miFrontera;
        }
        private Frontera(){
            fronteraPQ=new PriorityQueue<Sucesor>(new FronteraComparator());
        }

        public void InsertarVarios(){

        }

        public void Insertar(Sucesor sucesor){
            fronteraPQ.add(sucesor);
        }

        public Sucesor Retirar(){

            return fronteraPQ.poll();

        }

        public boolean EsVacia(){
            boolean vacia=false;
            if (fronteraPQ.isEmpty()){
                vacia=true;
            }
            return vacia;
        }
}


