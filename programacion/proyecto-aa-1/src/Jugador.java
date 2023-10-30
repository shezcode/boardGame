import java.util.Arrays;

public abstract class Jugador {
    int vidas = 3;
    String nombre;
    String enemigo;
    Tablero tablero;

    boolean alive = true;

    boolean hasBomb = false;

    int getVidas() {
        return vidas;
    }

    int increaseVidas(){
        return vidas + 1;
    }

    int decreaseVidas(){
        return vidas - 1;
    }

    boolean isAlive(){
        return vidas > 0;
    }

    public Tablero setTablero(){
        Tablero tableroHomer = new Tablero(this.nombre.charAt(0), this.enemigo.charAt(0));
        tableroHomer.initializeTablero();
        tableroHomer.generarPosicionJugador();
        tableroHomer.generarPosicionEnemigos();
        tableroHomer.generarCasillaSalida();
        tableroHomer.insertPosiciones();
        return tableroHomer;
    }


    void printTableroJugador(){
        for (char[] fila : this.tablero.tablero){
            String fila1 = Arrays.toString(fila);
            String fila2 = fila1.replaceAll("[FSVB]", "L");
            System.out.println(fila2);
        }
        System.out.println();
    }

    void printTableroReal(){
        for (char[] fila : this.tablero.tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    public String getEnemigo() {
        return this.enemigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Tablero getTablero(){
        return this.tablero;
    }

}
