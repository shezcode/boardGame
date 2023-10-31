import com.diogonunes.jcolor.Attribute;

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
        int[] pos = tableroJugador.generarPosicionJugador();
        tableroJugador.generarCasillaSalida();
        tableroJugador.generarCasillaBomba();
        tableroJugador.generarCasillasVidas();
        tableroJugador.insertPosiciones(pos);
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
            System.out.print(colorize( this.nombre + " introduce tu movimiento: ", Attribute.BRIGHT_CYAN_TEXT()));
            input = scanner.nextLine().toLowerCase();
            isValid = Utils.validarInput(input);
        } while (!isValid);
        return new Movimiento(Integer.parseInt(String.valueOf(input.charAt(0))), input.charAt(1));
    }

    void registrarMovimiento(Movimiento movimiento){
        if (movimiento.direccion == 'a'){
            int i = tablero.getPosicionJugador()[0];
            int j = tablero.getPosicionJugador()[1] - movimiento.casillas;
            if (j < 0){
                j = 0;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion);
        }
        if (movimiento.direccion == 'd'){
            int i = tablero.getPosicionJugador()[0];
            int j = tablero.getPosicionJugador()[1] + movimiento.casillas;
            if (j > 5){
                j = 5;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion);
        }
        if (movimiento.direccion == 'w'){
            int i = tablero.getPosicionJugador()[0] - movimiento.casillas;
            int j = tablero.getPosicionJugador()[1];
            if (i < 0){
                i = 0;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion);
        }
        if (movimiento.direccion == 's'){
            int i = tablero.getPosicionJugador()[0] + movimiento.casillas;
            int j = tablero.getPosicionJugador()[1];
            if (i > 5){
                i = 5;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion);
        }
        printTableroJugador();
    }

    void evaluarMovimiento(int[] nuevaPos){
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == this.enemigo.charAt(0)){
           this.vidas = decreaseVidas();
           System.out.println(colorize("Acabas de caer en la posicion de un enemigo. " + this.vidas + " vidas restantes.", Attribute.RED_TEXT()));
        }
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == 'V'){
            this.vidas = increaseVidas();
            System.out.println(colorize("Acabas de caer en una vida extra. " + this.vidas + " restantes.", Attribute.BRIGHT_GREEN_TEXT()));
        }
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == 'X'){
            setHasBomb(true);
        }
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == 'S'){
            setHasWon(true);
        }
    }

    void evaluarPartida(){
        if (hasWon){
            System.out.println(colorize(this.nombre + " ha ganado!", Attribute.BRIGHT_CYAN_TEXT()));
            return;
        }
        if (!isAlive()){
            System.out.println(colorize(this.nombre + " ha perdido!", Attribute.BRIGHT_CYAN_TEXT()));
            alive = false;
            return;
        }
        if (hasBomb){
            System.out.println(this.nombre + " acaba de recibir la bomba");
            return;
        }
        System.out.println(colorize("Partida continua", Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println();
        System.out.println(colorize("------------------------------------", Attribute.BRIGHT_GREEN_TEXT()));
        System.out.println();
    }

    void turnoJugador(){
        printTableroJugador();
        Movimiento movimiento = pedirMovimiento();
        registrarMovimiento(movimiento);
        int[] newpos = this.tablero.getPosicionJugador();
        //System.out.println(Arrays.toString(newpos));
        evaluarMovimiento(newpos);
        evaluarPartida();
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

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
