
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    static final int NUMERO_ENEMIGOS= 8;

    static int posicionJugador;

    static int[] posicionEnemigo = new int[NUMERO_ENEMIGOS];

    static Random generator = new Random();



    void initializeTablero(char[][] tablero){
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

    void insertPosiciones(char letraJugador, char letraEnemigo, char[][] tablero){
        int contador = 0;
        for (int i = 0; i<6; i++){
            for (int j = 0; j < 6; j++){
                if (contador == posicionJugador){
                    tablero[i][j] = letraJugador;
                }
                if (Utils.contains(posicionEnemigo, contador)){
                    tablero[i][j] = letraEnemigo;
                }
                contador++;
            }
        }
    }

    void printTablero(char[][] tablero){
        for (char[] fila : tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

}
