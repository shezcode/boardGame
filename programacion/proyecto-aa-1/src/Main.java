import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // char[] datos1 = pedirDatos(1);
       //
       // Jugador jugador2 = new Homer();
       // jugador2.setTablero();
       // jugador2.printTableroJugador();

        Jugador jugador1 = new Homer();
        jugador1.setTablero();
        jugador1.printTableroReal();
        jugador1.printTableroJugador();
        Movimiento mov = jugador1.pedirMovimiento();
        jugador1.realizarMovimiento(mov);
    }

}