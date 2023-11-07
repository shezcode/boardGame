import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;
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

    boolean truco;


    int increaseVidas(){
        return vidas + 1;
    }

    int decreaseVidas(){
        return vidas - 1;
    }

    boolean isAlive(){
        return vidas > 0;
    }

    public Tablero initTablero(int dimension, int enemigos){
        Tablero tableroJugador = new Tablero(this.nombre.charAt(0), this.enemigo.charAt(0), dimension, enemigos);
        int[][] posEnemigos = tableroJugador.generarPosicionEnemigos();
        int[] posJugador = tableroJugador.generarPosicionJugador();
        tableroJugador.generarCasillaSalida();
        tableroJugador.generarCasillaBomba();
        tableroJugador.generarCasillasVidas();
        tableroJugador.insertPosiciones(posJugador, posEnemigos);
        return tableroJugador;
    }


    void printTableroJugador(boolean trucos){}

    // Only useful for debugging reasons.
    //void printTableroReal(){
    //    for (char[] fila : this.tablero.tablero){
    //        System.out.println(Arrays.toString(fila));
    //    }
    //    System.out.println();
    //}

    public Movimiento pedirMovimiento(){
        boolean isValid;
        String input;
        do {
            if (hasBomb){
                System.out.println("Tienes una bomba en el inventario (pulsa 1b para detonar)");
            }
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
            evaluarMovimiento(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion, this.tablero.posicionesEnemigos);
        }
        if (movimiento.direccion == 'd'){
            int i = tablero.getPosicionJugador()[0];
            int j = tablero.getPosicionJugador()[1] + movimiento.casillas;
            if (j > 5){
                j = 5;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            evaluarMovimiento(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion, this.tablero.posicionesEnemigos);
        }
        if (movimiento.direccion == 'w'){
            int i = tablero.getPosicionJugador()[0] - movimiento.casillas;
            int j = tablero.getPosicionJugador()[1];
            if (i < 0){
                i = 0;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            evaluarMovimiento(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion, this.tablero.posicionesEnemigos);
        }
        if (movimiento.direccion == 's'){
            int i = tablero.getPosicionJugador()[0] + movimiento.casillas;
            int j = tablero.getPosicionJugador()[1];
            if (i > 5){
                i = 5;
            }
            int[] nuevaPosicion = {i, j};
            tablero.setPosicionJugador(nuevaPosicion);
            evaluarMovimiento(nuevaPosicion);
            tablero.insertPosiciones(nuevaPosicion, this.tablero.posicionesEnemigos);
        }
        if (movimiento.direccion == 'b'){
            if (!hasBomb){
                System.out.println("No tienes la bomba! :(");
                return;
            }
            int[] pos = tablero.getPosicionJugador();
            int[][] posicion2distancia = tablero.generar2PosicionesDistancia(pos);
            for (int[] fila : this.tablero.posicionesEnemigos){
                if (Utils.contains(posicion2distancia, fila)){
                    tablero.killPosicionesEnemigos(fila);
                }
            }
            setHasBomb(false);
            tablero.insertPosiciones(pos, this.tablero.posicionesEnemigos);
        }
        if (movimiento.direccion == 't'){
            setTruco(true);
            decreaseVidas();
        }
    }

    void evaluarMovimiento(int[] nuevaPos){
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == this.enemigo.charAt(0)){
           this.vidas = decreaseVidas();
           System.out.println(colorize("Acabas de caer en la posicion de un enemigo. " + this.vidas + " vidas restantes.", Attribute.RED_TEXT()));
           tablero.killPosicionesEnemigos(nuevaPos);
        }
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == 'V'){
            this.vidas = increaseVidas();
            System.out.println(colorize("Acabas de caer en una vida extra. " + this.vidas + " restantes.", Attribute.BRIGHT_GREEN_TEXT()));
            tablero.killVida(nuevaPos);
        }
        if (tablero.tablero[nuevaPos[0]][nuevaPos[1]] == 'X'){
            setHasBomb(true);
            tablero.killBomba(nuevaPos);
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

        System.out.println(colorize("Partida continua", Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println();
        System.out.println(colorize("------------------------------------", Attribute.BRIGHT_GREEN_TEXT()));
        System.out.println();
    }

    void turnoJugador(){
        printTableroJugador(isTruco());
        setTruco(false);
        //printTableroReal();
        Movimiento movimiento = pedirMovimiento();
        registrarMovimiento(movimiento);
        this.tablero.getPosicionJugador();

        //printTableroReal();
        printTableroJugador(isTruco());
        evaluarPartida();
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public boolean isTruco() {
        return truco;
    }

    public void setTruco(boolean truco) {
        this.truco = truco;
    }
}
