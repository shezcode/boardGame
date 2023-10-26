
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

    void generarPosicionJugador(){
        posicionJugador = generator.nextInt(36);
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

    void insertPosiciones(){
        int contador = 0;
        for (int i = 0; i<6; i++){
            for (int j = 0; j < 6; j++){
                if (contador == posicionJugador){
                    tablero[i][j] = letraJugador;
                }
                if (contador == posicionSalida){
                    tablero[i][j] = 'S';
                }
                if (Utils.contains(posicionEnemigo, contador)){
                    tablero[i][j] = letraEnemigo;
                }
                contador++;
            }
        }
    }

    void printTablero(){
        for (char[] fila : tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

}
