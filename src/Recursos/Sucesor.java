package Recursos;

public class Sucesor {
    private String movimiento;
    private Estado estado;
    private int coste;

    public Sucesor(String movimiento, Estado estado, int coste) {
        this.movimiento = movimiento;
        this.estado = estado;
        this.coste = coste;
    }

    public String toString(){
        return ("Movimiento:"+movimiento+"\n"+estado.getCubo().toString()+"\nCoste:"+coste);
    }

    public String getMovimiento() {
        return movimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getCoste() {
        return coste;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }


    public void setCoste(int coste) {
        this.coste = coste;
    }
}
