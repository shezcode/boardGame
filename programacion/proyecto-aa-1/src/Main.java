import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Homer jugador1 = new Homer();
        jugador1.initTablero();

        Bart jugador2 = new Bart();
        jugador2.initTablero();

        do {
            jugador1.turnoJugador();
            if (jugador1.hasWon || !jugador1.alive) break;
            jugador2.turnoJugador();
        } while ((jugador1.alive && !jugador1.hasWon) && ((jugador2.alive && !jugador2.hasWon)));

        if (jugador1.hasWon){
            System.out.println("DALE PAAAAAA");
            return;
        }

        if (!jugador1.isAlive()){
            System.out.println("xd pelele");
        }

    }
}