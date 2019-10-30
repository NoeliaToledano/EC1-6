package Recursos;

public class NodoArbol implements Comparable<NodoArbol>  {
    private int id;
    private Estado estado;
    private float Costo_camino;
    private String Accion;
    private float d;
    private float f;

    public NodoArbol(int id, Estado estado, float costo_camino, String accion, float d, float f) {
        this.id= id;
        this.estado = estado;
        this.Costo_camino = costo_camino;
        this.Accion = accion;
        this.d = d;
        this.f = f;
    }

    public boolean EsPadre () {
        boolean Padre = false;
        if(Costo_camino == 0){
            Padre = true;
        }
      return Padre;
    }
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

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }



    public int getId() {
        return id;
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
            return 0;
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
