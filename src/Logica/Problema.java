package Logica;

import Recursos.*;
import java.util.*;

public class Problema {
    private Frontera frontera;
    private NodoArbol nodoInicial;
    private boolean solucion;
    private HashMap<String, Double> nodosExpan;

    public Problema(Cube cuboInicial, int estrategia) {
        this.frontera = Frontera.getFrontera();
        Estado estado = new Estado(cuboInicial);
        this.nodoInicial= new NodoArbol(estado, estrategia);
        this.solucion=false;
        this.frontera.insertar(nodoInicial);
        this.nodosExpan= new HashMap<String, Double>();
        //nodosExpan.put(cuboInicial.toMD5(),(float)0);

    }

    public void busqueda(int estrategia,int profMax){
        NodoArbol nodoActual=null;
        Queue<Sucesor> sucesores;
        Queue<NodoArbol> listaNodos;
        while(!solucion && !frontera.esVacia()){
            nodoActual = frontera.retirar();
             if(nodoActual.getEstado().objetivo()){
                solucion=true;
            }
            else{
                sucesores=nodoActual.getEstado().sucesores();
                listaNodos=crearListaNodos(sucesores,nodoActual,profMax,estrategia);
                frontera.insertarVarios(listaNodos);
            }
        }
        if(solucion){
            CrearSolucion(nodoActual);
        }
        else{
            System.out.println("Solucion no encontrada");
        }
    }

    public Queue<NodoArbol> crearListaNodos(Queue<Sucesor> sucesores,NodoArbol nodoActual,int profMax,int estrategia){
        Queue<NodoArbol> listaNodos = new LinkedList<NodoArbol>();
        int profActual= nodoActual.getD();
        if(profActual<profMax) { //si no he llegado a la profMax creo la lista de nodoArbol
            double f = 0;
            profActual++;
            Sucesor sucesorActu;

            while (!sucesores.isEmpty()) {
                sucesorActu=sucesores.poll();
                NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), sucesorActu.getMovimiento(), profActual, sucesorActu.getCoste(), nodoActual, estrategia);

                if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                    if (estrategia==2){
                        if(f>nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())){//si la f es mayor lo inserto
                            listaNodos.add(posibleNodoArbol);
                            nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);}
                    }else{
                        if(f<nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())){//si la f es menor lo inserto
                            listaNodos.add(posibleNodoArbol);
                            nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);}
                    }

                }else{ //sino  se he expandido antes lo inserto directamente
                    listaNodos.add(posibleNodoArbol);
                    nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
                }

            }
        }
        return  listaNodos;
    }


    public void CrearSolucion(NodoArbol nodoObjetivo){


        System.out.println("["+nodoObjetivo.getId()+"](["+nodoObjetivo.getAccion()+"]"+nodoObjetivo.getEstado().getCubo().toMD5()+",c="+nodoObjetivo.getCosto_camino()+",p="+nodoObjetivo.getD() +",h="+ (double)Math.round(nodoObjetivo.getH() * 100d) / 100d  + ",f="+ (double)Math.round(nodoObjetivo.getF() * 100d) / 100d+")");

        if (!nodoObjetivo.EsPadre()) {
            NodoArbol nodo=nodoObjetivo.getPadre();
            while (!nodo.EsPadre()) {
                System.out.println("[" + nodo.getId() + "]([" + nodo.getAccion() + "]" + nodo.getEstado().getCubo().toMD5() + ",c=" + nodo.getCosto_camino() + ",p=" + nodo.getD() +",h="+ (double)Math.round(nodo.getH() * 100d) / 100d  + ",f=" + (double)Math.round(nodo.getF() * 100d) / 100d + ")");
                nodo = nodo.getPadre();
            }
            System.out.println("[" + nodo.getId() + "]([" + nodo.getAccion() + "]" + nodo.getEstado().getCubo().toMD5() + ",c=" + nodo.getCosto_camino() + ",p=" + nodo.getD() +",h="+ (double)Math.round(nodo.getH() * 100d) / 100d + ",f=" + (double)Math.round(nodo.getF() * 100d) / 100d + ")");

        }

        }
       
}

