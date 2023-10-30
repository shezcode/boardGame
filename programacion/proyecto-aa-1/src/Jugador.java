import java.util.Arrays;
import java.util.Scanner;

public abstract class Jugador {
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

    public Tablero setTablero(){
        Tablero tableroJugador = new Tablero(this.nombre.charAt(0), this.enemigo.charAt(0));
        tableroJugador.generarPosicionEnemigos();
        tableroJugador.generarCasillaSalida();
        tableroJugador.insertPosiciones(tableroJugador.generarPosicionJugador());
        return tableroJugador;
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

    public void realizarMovimiento(Movimiento movimiento){
         int posicion = this.tablero.getPosicionJugador();
         int fila = posicion / 6;
         int casillas = movimiento.getCasillas();
         char direccion = movimiento.getDireccion();

        if (direccion == 'a'){
            int posicionFinal = posicion - casillas;
            if (posicionFinal / 6 != fila){
                posicionFinal = fila * 6;
            }

           this.tablero.setPosicionJugador(posicionFinal);
           this.tablero.moverJugador(posicionFinal);
           printTableroJugador();
        }
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
