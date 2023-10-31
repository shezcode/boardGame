import java.util.Arrays;

public class Main {
    int turno = 0;
    public static void main(String[] args) {
        Homer jugador1 = new Homer();
        jugador1.initTablero();
        jugador1.printTableroReal();
        do {
            Movimiento movimiento = jugador1.pedirMovimiento();
            jugador1.registrarMovimiento(movimiento);
            int[] newpos = jugador1.tablero.getPosicionJugador();
            System.out.println(Arrays.toString(newpos));
            jugador1.evaluarMovimiento(newpos);
            jugador1.evaluarPartida();
        } while (jugador1.alive && !jugador1.hasWon);

        if (jugador1.hasWon){
            System.out.println("DALE PAAAAAA");
            return;
        }

        if (!jugador1.isAlive()){
            System.out.println("xd pelele");
        }

        //Bart jugador2 = new Bart();
        //jugador2.initTablero();
        //jugador2.printTableroJugador();
    }
}