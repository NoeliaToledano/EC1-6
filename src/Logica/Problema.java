package Logica;

import Recursos.*;

import java.util.*;

public class Problema {
    private Frontera frontera;
    private NodoArbol nodoInicial;
    private boolean solucion;
    private HashMap<String, Float> nodosExpan;

    public Problema(Cube cuboInicial) {
        this.frontera = Frontera.getFrontera();
        this.nodoInicial= new NodoArbol(0,new Estado(cuboInicial),(float)0,"",0,(float)0,null);
        this.solucion=false;
        this.frontera.insertar(nodoInicial);
        this.nodosExpan= new HashMap<String, Float>();
        nodosExpan.put(cuboInicial.toMD5(),(float)0);
    }

    public boolean solucion(){
        return true;
    }

    public void busqueda(String estrategia,int profMax){
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

    public Queue<NodoArbol> crearListaNodos(Queue<Sucesor> sucesores,NodoArbol nodoActual,int profMax,String estrategia){
        Queue<NodoArbol> listaNodos = new LinkedList<NodoArbol>();
        int idActual = nodoActual.getId();
        int profActual= nodoActual.getD();
        profActual++;
        float f;
        if(profActual<profMax) { //si no he llegado a la profMax creo la lista de nodoArbol
            if (estrategia.equals("Anchura") || estrategia.equals("Breadth")) {
                Sucesor sucesorActu;
                while ((sucesorActu = sucesores.poll()) != null) {
                    f=(float)profActual;
                    if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                        if(f<nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())) //si la f es menor lo inserto
                            listaNodos.add(new NodoArbol(idActual++, sucesorActu.getEstado(), (sucesorActu.getCoste() + nodoActual.getCosto_camino()), sucesorActu.getMovimiento(), profActual, f, nodoActual));
                    }else{ //sino  se he expandido antes lo inserto directamente
                        listaNodos.add(new NodoArbol(idActual++, sucesorActu.getEstado(), (sucesorActu.getCoste() + nodoActual.getCosto_camino()), sucesorActu.getMovimiento(), profActual, f, nodoActual));
                    }

                }
            }
        }
        return  listaNodos;
    }
    public void CrearSolucion(NodoArbol nodoObjetivo){

        System.out.println(nodoObjetivo+" Accion: "+nodoObjetivo.getAccion());
        NodoArbol nodo=nodoObjetivo.getPadre();
        while(!nodo.EsPadre()){
            System.out.println(nodo+" Accion: "+nodo.getAccion());
            nodo=nodo.getPadre();
        }
    }
}
