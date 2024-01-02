public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.intro();
        int dimensionTablero = juego.pedirDimensionTablero();
        int numeroEnemigos = juego.pedirDificultad();
        enumJugador nombre1 = juego.pedirDatos(1);

        Jugador jugador1 = switch (nombre1) {
            case HOMER -> new Homer();
            case BART -> new Bart();
            case PETER -> new Peter();
            case SHREK -> new Shrek();
        };

        jugador1.tablero = jugador1.initTablero(dimensionTablero, numeroEnemigos);

        enumJugador nombre2 = juego.pedirDatos(2);
        Jugador jugador2 = switch(nombre2) {
            case HOMER -> new Homer();
            case BART -> new Bart();
            case PETER -> new Peter();
            case SHREK -> new Shrek();
        };

         jugador2.tablero = jugador2.initTablero(dimensionTablero, numeroEnemigos);

        do {
            jugador1.turnoJugador();
            if (jugador1.hasWon || !jugador1.alive) break;
            jugador2.turnoJugador();
        } while ((jugador1.alive && !jugador1.hasWon) && ((jugador2.alive && !jugador2.hasWon)));
    }
}