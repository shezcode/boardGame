import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    static char[][] tableroJugador1= new char[6][6];
    static char[][] tableroJugador2 = new char[6][6];
    public static void main(String[] args) {
        Tablero tablero1 = new Tablero();
        Tablero tablero2 = new Tablero();
        tablero1.initializeTablero(tableroJugador1);
        tablero1.generarPosicionJugador();
        tablero1.generarPosicionEnemigos();
        char[] datos1 = pedirDatos(1);
        tablero1.insertPosiciones(datos1[0], datos1[1], tableroJugador1);
        tablero1.printTablero(tableroJugador1);
        tablero2.initializeTablero(tableroJugador2);
        tablero2.generarPosicionJugador();
        tablero2.generarPosicionEnemigos();
        char[] datos2 = pedirDatos(2);
        tablero2.insertPosiciones(datos2[0], datos2[1], tableroJugador2);
        tablero2.printTablero(tableroJugador2);
    }

    static char[] pedirDatos(int jugador){
        System.out.printf("Jugador %d, Introduce tu nombre: \n", jugador);
        char letraJugador = scanner.nextLine().charAt(0);
        System.out.printf("Jugador %d, Introduce el nombre de tu enemigo: \n", jugador);
        char letraEnemigo = scanner.nextLine().charAt(0);
        return new char[]{letraJugador, letraEnemigo};
    }
}