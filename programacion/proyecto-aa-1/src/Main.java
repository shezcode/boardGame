public class Main {
    static char[][] tableroHomer = new char[6][6];
    static char[][] tableroBart = new char[6][6];
    public static void main(String[] args) {
        Tablero tablero1 = new Tablero();
        Tablero tablero2 = new Tablero();
        tablero1.initializeTablero(tableroHomer);
        tablero1.generarPosicionJugador();
        tablero1.generarPosicionEnemigos();
        tablero1.insertPosiciones('H', 'F', tableroHomer);
        tablero1.printTablero(tableroHomer);
    }
}