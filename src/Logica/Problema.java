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
        //nodosExpan.put(cuboInicial.toMD5(),(float)0);

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
        if(profActual<profMax) { //si no he llegado a la profMax creo la lista de nodoArbol
            float f = 0;
            profActual++;
            Sucesor sucesorActu;
            while (!sucesores.isEmpty()) {
                sucesorActu=sucesores.poll();
                switch (estrategia){

                    case "Anchura" :
                        f =(float)profActual;
                        break;
                    case "Breadth" :
                        f =(float)profActual;
                        break;
                    case "Profundidad Acotada" :
                        f=1/(1 + (float)profActual);
                        break;
                    case "Bounded Depth":
                        f=1/(1 + (float)profActual);
                        break;
                    case "Costo Uniforme" :
                        f= sucesorActu.getCoste() + nodoActual.getCosto_camino();
                        break;
                    case "Uniform Cost" :
                        f= sucesorActu.getCoste() + nodoActual.getCosto_camino();
                        break;
                }



                NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), (sucesorActu.getCoste() + nodoActual.getCosto_camino()), sucesorActu.getMovimiento(), profActual, f, nodoActual);
                if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5())){ //miro si lo he expandido antes
                    if (estrategia.equals("Profundidad Acotada")){
                        if(f>nodosExpan.get(sucesorActu.getEstado().getCubo().toMD5())){//si la f es menor lo inserto
                            listaNodos.add(posibleNodoArbol);
                            nodosExpan.put(sucesorActu.getEstado().getCubo().toMD5(),f);}
                    }else if(estrategia.equals("Anchura") || estrategia.equals("Costo Uniforme")){
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
       
}

