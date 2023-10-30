
import java.util.Arrays;
import java.util.Random;

public class Tablero {

    char[][] tablero = new char[6][6];
    char letraJugador;
    char letraEnemigo;
    static final int NUMERO_ENEMIGOS= 8;
    int posicionJugador;
    int posicionSalida;

    static int[] posicionEnemigo = new int[NUMERO_ENEMIGOS];

    Random generator = new Random();

    public Tablero(char letraJugador, char letraEnemigo){
        this.letraJugador = letraJugador;
        this.letraEnemigo = letraEnemigo;
    }

    void initializeTablero(){
        for (char[] fila : tablero){
            Arrays.fill(fila, 'L');
        }
    }

    int generarPosicionJugador(){
        posicionJugador = generator.nextInt(36);
        return posicionJugador;
    }

    void generarPosicionEnemigos(){
        for (int i = 0; i < NUMERO_ENEMIGOS; i++){
            int valor;
            do {
                valor = generator.nextInt(36);
            }
            while (valor == posicionJugador || Utils.contains(posicionEnemigo, valor));
            posicionEnemigo[i] = valor;
        }
        Arrays.sort(posicionEnemigo);
    }

    void generarCasillaSalida(){
        do {
           posicionSalida = generator.nextInt(36);
        } while (posicionSalida == posicionJugador || Utils.contains(posicionEnemigo, posicionSalida));
    }

    void insertPosiciones(int posJugador){
        int contador = 0;
        for (int i = 0; i<6; i++){
            for (int j = 0; j < 6; j++){
                if (contador == posJugador){
                    tablero[i][j] = this.letraJugador;
                }
                if (contador == this.posicionSalida){
                    tablero[i][j] = 'S';
                }
                if (Utils.contains(posicionEnemigo, contador)){
                    tablero[i][j] = this.letraEnemigo;
                }
                contador++;
            }
        }
    }

    void moverJugador(int nuevaPosicion){
        this.posicionJugador = nuevaPosicion;
        insertPosiciones(nuevaPosicion);
    }
    void printTablero(){
        for (char[] fila : tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    public int getPosicionJugador() {
        return this.posicionJugador;
    }

    public void setPosicionJugador(int posicionJugador) {
        this.posicionJugador = posicionJugador;
    }
}
