package Logica;

import Recursos.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problema {
    private Frontera frontera;
    private NodoArbol nodoInicial;
    private boolean solucion;

    public Problema(Cube cuboInicial) {
        this.frontera = Frontera.getFrontera();
        this.nodoInicial= new NodoArbol(0,new Estado(cuboInicial),(float)0,"",0,(float)0,null);
        this.solucion=false;
        this.frontera.insertar(nodoInicial);

    }

    public boolean solucion(){
        return true;
    }

    public void busqueda(String estrategia,int profMax){
        NodoArbol nodoActual;
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
            //crearSolucion
        }
        else{
            //NoSolucion
        }
    }

    public Queue<NodoArbol> crearListaNodos(Queue<Sucesor> sucesores,NodoArbol nodoActual,int profMax,String estrategia){
        Queue<NodoArbol> listaNodos = new LinkedList<NodoArbol>();
        int idActual = nodoActual.getId();
        int profActual= nodoActual.getD();
        profActual++;
        if(estrategia.equals("Anchura") || estrategia.equals("Breadth")){
            Sucesor sucesorActu;
            while((sucesorActu=sucesores.poll())!=null){
                listaNodos.add(new NodoArbol(idActual++,sucesorActu.getEstado(),(sucesorActu.getCoste()+nodoActual.getCosto_camino()),sucesorActu.getMovimiento(),profActual,profActual,nodoActual));
            }
        }
        return  listaNodos;
    }
}
