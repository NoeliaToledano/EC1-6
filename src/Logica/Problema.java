package Logica;

import Recursos.*;

import java.util.*;

public class Problema {
    private Frontera frontera;
    private NodoArbol nodoInicial;
    private boolean solucion;
    private HashMap<String, Double> nodosExpan;
    private boolean poda;
    public Problema(Cube cuboInicial, int estrategia, boolean poda) {
        double f=0.0;
        this.frontera = Frontera.getFrontera();
        Estado estado = new Estado(cuboInicial);
        switch(estrategia){
            case 1:
                f = 0.0;
                break;
            case 2:
                f=1.0;
                break;
            case 3:
                f= 0.0;
                break;
            case 4:
                f = estado.getHeuristica();
                break;
            case 5:
                f =estado.getHeuristica();
                break;}
        this.nodoInicial= new NodoArbol(estado,(float)0,"",0, f,null);
        this.solucion=false;
        this.frontera.insertar(nodoInicial);
        this.nodosExpan= new HashMap<String, Double>();
        this.poda=poda;
        //nodosExpan.put(cuboInicial.toMD5(),(float)0);

    }

    public boolean solucion(){
        return true;
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
            float coste;
            while (!sucesores.isEmpty()) {
                sucesorActu=sucesores.poll();
                coste=sucesorActu.getCoste() + nodoActual.getCosto_camino();
                switch (estrategia){

                    case 1:
                        f =(double)profActual;
                        break;
                    case 2:
                        f=1/(1 + (double)profActual);
                        break;
                    case 3:
                        f= sucesorActu.getCoste() + nodoActual.getCosto_camino();
                        break;
                    case 4:
                        f =coste + (double)sucesorActu.getEstado().getCubo().calcularHeuristica();
                        break;
                    case 5:
                        f =(double)sucesorActu.getEstado().getCubo().calcularHeuristica();
                        break;
                }



                NodoArbol posibleNodoArbol= new NodoArbol(sucesorActu.getEstado(), coste, sucesorActu.getMovimiento(), profActual,  f, nodoActual);
                if(nodosExpan.containsKey(sucesorActu.getEstado().getCubo().toMD5()) && poda){ //miro si lo he expandido antes y si hay poda
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

