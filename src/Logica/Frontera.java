package Logica;

import Recursos.NodoArbol;
import Recursos.Sucesor;

import java.util.PriorityQueue;

public class Frontera {

        private static PriorityQueue<NodoArbol> fronteraPQ;
        private static Frontera miFrontera;

        public static Frontera getFrontera(){
            if(miFrontera==null){
                miFrontera=new Frontera();
            }
            return miFrontera;
        }
        private Frontera(){
            fronteraPQ=new PriorityQueue<NodoArbol>();
        }

        public void insertarVarios(){

        }

        public void insertar(NodoArbol nodo){
            fronteraPQ.add(nodo);
        }

        public NodoArbol retirar(){

            return fronteraPQ.poll();

        }

        public boolean esVacia(){
            return fronteraPQ.isEmpty();
        }
}


