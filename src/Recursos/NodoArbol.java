package Recursos;

public class NodoArbol implements Comparable<NodoArbol>  {

    private int id;
    private Estado estado;
    private float Costo_camino;
    private String Accion;
    private int d;
    private double f;
    private double h;
    private NodoArbol padre;
    private static int nodosCreados=0;


    public NodoArbol(Estado estado, String accion, int d, int coste, NodoArbol padre, int estrategia) {
        this.id= nodosCreados++;
        this.estado = estado;
        this.Costo_camino = coste + padre.getCosto_camino();//peta en padre
        this.Accion = accion;
        this.d = d;
        this.padre=padre;
        this.h=estado.getHeuristica();
        this.f=calcularF(estrategia, coste);
    }
    public NodoArbol(Estado estado, int estrategia){
        this.id= nodosCreados++;
        this.estado = estado;
        this.Costo_camino=0;
        this.d=0;
        this.h=estado.getHeuristica();
        this.f=calcularF(estrategia, 0);
    }

    public double calcularF(int estrategia, int coste){
        double f=0;
        switch (estrategia){
            case 1:
                f =(double)d;
                break;
            case 2:
                f=1/(1 + (double)d);
                break;
            case 3:
                f = coste + Costo_camino;
                break;
            case 4:
                f = coste + Costo_camino + h;
                break;
            case 5:
                f = h;
                break;
        }
        return f;
    }
    public boolean EsPadre () { return Costo_camino == 0.0; }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public float getCosto_camino() {
        return Costo_camino;
    }

    public void setCosto_camino(float costo_camino) {
        Costo_camino = costo_camino;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String accion) {
        Accion = accion;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public NodoArbol getPadre(){
        return this.padre;
    }

    public int getId() {
        return id;
    }

    public double getH(){
        return h;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(NodoArbol nodo1) {
        if(this.f > nodo1.getF()) {
            return 1;
        } else if (this.f < nodo1.getF()) {
            return -1;
        } else {
            if(this.id>nodo1.getId()) return 1;
            else return -1;
        }
    }
    @Override
    public String toString() {
        return "NodoArbol{" +
                "id=" + id +
                ", estado=" + estado +
                ", Costo_camino=" + Costo_camino +
                ", Accion='" + Accion + '\'' +
                ", d=" + d +
                ", f=" + f +
                '}';
    }
}
