import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    static char[][] tableroJugador1= new char[6][6];

    static char[][] tableroJugador2 = new char[6][6];

    public static void main(String[] args) {
        char[] datos1 = pedirDatos(1);
        Tablero tablero1 = new Tablero(datos1[0], datos1[1]);
        tablero1.initializeTablero();
        tablero1.generarPosicionJugador();
        tablero1.generarPosicionEnemigos();
        tablero1.generarCasillaSalida();
        tablero1.insertPosiciones();
        tablero1.printTablero();
    }

    static char[] pedirDatos(int jugador){
        System.out.printf("Jugador %d, Introduce tu nombre: \n", jugador);
        char letraJugador = scanner.nextLine().charAt(0);
        System.out.printf("Jugador %d, Introduce el nombre de tu enemigo: \n", jugador);
        char letraEnemigo = scanner.nextLine().charAt(0);
        return new char[]{letraJugador, letraEnemigo};
    }
}