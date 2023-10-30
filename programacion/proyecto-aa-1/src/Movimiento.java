public class Movimiento {
    public int casillas;
    public char direccion;

    public Movimiento(int casillas, char direccion){
        this.casillas = casillas;
        this.direccion = direccion;
    }


    public int getCasillas() {
        return casillas;
    }

    public void setCasillas(int casillas) {
        this.casillas = casillas;
    }

    public char getDireccion() {
        return direccion;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }
}
