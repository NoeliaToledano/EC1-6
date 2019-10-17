package Recursos;

public class Sucesor {
    private String movimiento;
    private Cube cubo;
    private int coste;

    public Sucesor(String movimiento, Cube cubo, int coste) {
        this.movimiento = movimiento;
        this.cubo = cubo;
        this.coste = coste;
    }

    public String toString(){
        return ("Movimiento:"+movimiento+"\n"+cubo.toString()+"\nCoste:"+coste);
    }

    public String getMovimiento() {
        return movimiento;
    }

    public Cube getCubo() {
        return cubo;
    }

    public int getCoste() {
        return coste;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public void setCubo(Cube cubo) {
        this.cubo = cubo;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }
}
