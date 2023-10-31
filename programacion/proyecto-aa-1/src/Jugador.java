import java.util.Arrays;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Jugador {
    private static final Scanner scanner = new Scanner(System.in);
    int vidas = 3;
    String nombre;
    String enemigo;
    Tablero tablero;


    boolean alive = true;

    boolean hasBomb = false;

    boolean hasWon = false;

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

    public Tablero initTablero(){
        Tablero tableroJugador = new Tablero(this.nombre.charAt(0), this.enemigo.charAt(0));
        tableroJugador.generarPosicionEnemigos();
        tableroJugador.generarPosicionJugador();
        tableroJugador.generarCasillaSalida();
        tableroJugador.generarCasillaBomba();
        tableroJugador.generarCasillasVidas();
        tableroJugador.insertPosiciones();
        return tableroJugador;
    }


    void printTableroJugador(){}

    void printTableroReal(){
        for (char[] fila : this.tablero.tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    static char[] pedirDatos(int jugador){
        System.out.printf("Jugador %d, Introduce tu nombre: \n", jugador);
        char letraJugador = scanner.nextLine().charAt(0);
        System.out.printf("Jugador %d, Introduce el nombre de tu enemigo: \n", jugador);
        char letraEnemigo = scanner.nextLine().charAt(0);
        return new char[]{letraJugador, letraEnemigo};
    }
    public Movimiento pedirMovimiento(){
        boolean isValid;
        String input;
        do {
            System.out.print(this.nombre + " introduce tu movimiento: ");
            input = scanner.nextLine().toLowerCase();
            isValid = Utils.validarInput(input);
        } while (!isValid);
        return new Movimiento(Integer.parseInt(String.valueOf(input.charAt(0))), input.charAt(1));
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
