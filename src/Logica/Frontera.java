package Logica;

import Recursos.NodoArbol;
import Recursos.Sucesor;

import java.util.PriorityQueue;
import java.util.Queue;

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

        public void insertarVarios(Queue<NodoArbol> listaNodos){
            while(!listaNodos.isEmpty()){
                fronteraPQ.add(listaNodos.poll());
            }
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


