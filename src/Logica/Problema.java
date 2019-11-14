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
        this.nodoInicial= new NodoArbol(new Estado(cuboInicial),(float)0,"",0,(float)0,null);
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
        int profActual= nodoActual.getD();

        if(estrategia.equals("Profundidad") || estrategia.equals("Depth")){

        }

        if(profActual<profMax) { //si no he llegado a la profMax creo la lista de nodoArbol

            switch (estrategia){

                case "Anchura" :
                    listaNodos = Anchura(listaNodos, profActual, sucesores, nodoActual);
                    break;
                case "Breadth" :
                    listaNodos = Anchura(listaNodos, profActual, sucesores, nodoActual);
                    break;
                case "Profundidad Acotada" :
                    listaNodos = Profundidad_Acotada(listaNodos, profActual, sucesores, nodoActual);
                    break;
                case "Bounded Depth":
                    listaNodos = Profundidad_Acotada(listaNodos, profActual, sucesores, nodoActual);
                    break;
                case "Costo Uniforme" :
                    listaNodos = Costo_Uniforme(listaNodos, profActual, sucesores, nodoActual);
                    break;
                case "Uniform Cost" :
                    listaNodos = Costo_Uniforme(listaNodos, profActual, sucesores, nodoActual);
                    break;
            }

            }
        return  listaNodos;
        }


    public void CrearSolucion(NodoArbol nodoObjetivo){

        System.out.println("["+nodoObjetivo.getId()+"](["+nodoObjetivo.getAccion()+"]"+nodoObjetivo.getEstado().getCubo().toMD5()+",c="+nodoObjetivo.getCosto_camino()+",p="+nodoObjetivo.getD()+",f="+nodoObjetivo.getF()+")");

        if (!nodoObjetivo.EsPadre()) {
            NodoArbol nodo=nodoObjetivo.getPadre();
            while (!nodo.EsPadre()) {
                System.out.println("[" + nodo.getId() + "]([" + nodo.getAccion() + "]" + nodo.getEstado().getCubo().toMD5() + ",c=" + nodo.getCosto_camino() + ",p=" + nodo.getD() + ",f=" + nodo.getF() + ")");
                nodo = nodo.getPadre();
            }
            System.out.println("[" + nodo.getId() + "]([" + nodo.getAccion() + "]" + nodo.getEstado().getCubo().toMD5() + ",c=" + nodo.getCosto_camino() + ",p=" + nodo.getD() + ",f=" + nodo.getF() + ")");
        }

        }
        public Queue<NodoArbol> Anchura (Queue<NodoArbol> listaNodos, int profActual, Queue<Sucesor> sucesores, NodoArbol nodoActual){
            float f;
            profActual++;
            Sucesor sucesorActu;
            while (!sucesores.isEmpty()) {
                f=(float)profActual;
                sucesorActu=sucesores.poll();
                NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), (sucesorActu.getCoste() + nodoActual.getCosto_camino()), sucesorActu.getMovimiento(), profActual, f, nodoActual);
                if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                    if(f<nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())) //si la f es menor lo inserto
                        listaNodos.add(posibleNodoArbol);
                        nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
                }else{ //sino  se he expandido antes lo inserto directamente
                    listaNodos.add(posibleNodoArbol);
                    nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
                }

            }
            return listaNodos;
        }

    public Queue<NodoArbol> Profundidad_Acotada (Queue<NodoArbol> listaNodos, int profActual, Queue<Sucesor> sucesores, NodoArbol nodoActual){
        float f;
        profActual++;
        Sucesor sucesorActu;
        while (!sucesores.isEmpty()) {
            f=1/(1 + (float)profActual);
            sucesorActu=sucesores.poll();
            NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), (sucesorActu.getCoste() + nodoActual.getCosto_camino()), sucesorActu.getMovimiento(), profActual, f, nodoActual);
            if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                if(f<nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())) //si la f es menor lo inserto
                    listaNodos.add(posibleNodoArbol);
                    nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
            }else{ //sino  se he expandido antes lo inserto directamente
                listaNodos.add(posibleNodoArbol);
                nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
            }

        }
        return listaNodos;
    }
    public Queue<NodoArbol> Costo_Uniforme (Queue<NodoArbol> listaNodos, int profActual, Queue<Sucesor> sucesores, NodoArbol nodoActual){
        float f;
        profActual++;
        Sucesor sucesorActu = null;
        while (!sucesores.isEmpty()) {
            sucesorActu=sucesores.poll();
            f= sucesorActu.getCoste() + nodoActual.getCosto_camino();
            NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), (f), sucesorActu.getMovimiento(), profActual, f, nodoActual);
            if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                if(f<nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())) //si la f es menor lo inserto
                    listaNodos.add(posibleNodoArbol);
                    nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
            }else{ //sino  se he expandido antes lo inserto directamente
                listaNodos.add(posibleNodoArbol);
                nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);
            }

        }
        return listaNodos;
    }
}

