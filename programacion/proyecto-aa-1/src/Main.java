public class Main {
    public static void main(String[] args) {
        Homer jugador1 = new Homer();
        jugador1.setTablero();
        //jugador1.printTableroReal();
        jugador1.printTableroJugador();

        Bart jugador2 = new Bart();
        jugador2.setTablero();
        jugador2.printTableroJugador();
    }
}