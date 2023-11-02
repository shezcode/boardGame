
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.intro();
        enumJugador nombre1 = juego.pedirDatos(1);

        Jugador jugador1 = switch (nombre1) {
            case HOMER -> new Homer();
            case BART -> new Bart();
            case PETER -> new Peter();
            case SHREK -> new Shrek();
        };

        jugador1.initTablero();

        enumJugador nombre2 = juego.pedirDatos(2);
        Jugador jugador2 = switch(nombre2) {
            case HOMER -> new Homer();
            case BART -> new Bart();
            case PETER -> new Peter();
            case SHREK -> new Shrek();
        };

        jugador2.initTablero();

        do {
            jugador1.turnoJugador();
            if (jugador1.hasWon || !jugador1.alive) break;
            jugador2.turnoJugador();
        } while ((jugador1.alive && !jugador1.hasWon) && ((jugador2.alive && !jugador2.hasWon)));
    }
}