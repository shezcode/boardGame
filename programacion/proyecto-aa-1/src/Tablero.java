
import java.util.Arrays;
import java.util.Random;

public class Tablero {

    char[][] tablero = new char[6][6];
    char letraJugador;
    char letraEnemigo;
    static final int NUMERO_ENEMIGOS= 8;
    int[] posicionJugador = new int[2];
    int[] posicionSalida = new int[2];

    int[] posicionBomba = new int[2];

    int[][] posicionesVidas = new int[2][2];

    static int[][] posicionesEnemigos = new int[NUMERO_ENEMIGOS][2];

    Random generator = new Random();

    public Tablero(char letraJugador, char letraEnemigo){
        this.letraJugador = letraJugador;
        this.letraEnemigo = letraEnemigo;
    }

    // Generacion de posiciones, orden especefico para evitar problemas en la comprobacion. Se han de llamar los metodos en este mismo orden.
    int[] generarPosicionJugador(){
        posicionJugador[0] = generator.nextInt(6);
        posicionJugador[1] = generator.nextInt(6);
        return posicionJugador;
    }

    void generarCasillaSalida(){
        do {
            posicionSalida[0] = generator.nextInt(6);
            posicionSalida[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionSalida, posicionJugador));
    }

    void generarCasillaBomba(){
        do {
            posicionBomba[0] = generator.nextInt(6);
            posicionBomba[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionBomba, posicionJugador) || Arrays.equals(posicionBomba, posicionSalida));
    }

    void generarCasillasVidas(){
        int[] arr = new int[2];
        for(int i = 0; i < 2; i++){
            do {
                arr[0] = generator.nextInt(6);
                arr[1] = generator.nextInt(6);
            } while (Arrays.equals(arr, posicionJugador) ||
                    Arrays.equals(arr, posicionSalida) ||
                    Utils.contains(posicionesVidas, arr) ||
                    Arrays.equals(arr, posicionBomba));
            posicionesVidas[i][0] = arr[0];
            posicionesVidas[i][1] = arr[1];
        }
    }

    void generarPosicionEnemigos(){
        int[] arr = new int[2];
        for(int i = 0; i < NUMERO_ENEMIGOS; i++){
            do {
                arr[0] = generator.nextInt(6);
                arr[1] = generator.nextInt(6);
            } while (Arrays.equals(arr, posicionJugador) || Arrays.equals(arr, posicionSalida) || Utils.contains(posicionesEnemigos, arr)
                    || Arrays.equals(arr, posicionBomba) || Utils.contains(posicionesVidas, arr));
            posicionesEnemigos[i][0] = arr[0];
            posicionesEnemigos[i][1] = arr[1];
        }
       //Arrays.sort(posicionEnemigo, (a, b) -> a[0] - b[0]);
    }

    void insertPosiciones(int[] posJugador){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                tablero[i][j] = 'L';
                int[] arr = {i, j};
                if (Arrays.equals(posJugador, arr)){
                    tablero[i][j] = this.letraJugador;
                }
                if (Arrays.equals(posicionSalida, arr)){
                    tablero[i][j] = 'S';
                }
                // Llamo X a la posicion de la bomba, para evitar interferencia con la B de Bart.
                if (Arrays.equals(posicionBomba, arr)){
                    tablero[i][j] = 'X';
                }
                if (Utils.contains(posicionesEnemigos, arr)){
                    tablero[i][j] = this.letraEnemigo;
                }
                if (Utils.contains(posicionesVidas, arr)){
                    tablero[i][j] = 'V';
                }
            }
        }
    }

    public int[] getPosicionJugador() {
        return this.posicionJugador;
    }

    public void setPosicionJugador(int[] posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    public static int[][] getPosicionEnemigo() {
        return posicionesEnemigos;
    }

    public static void setPosicionEnemigo(int[][] posicionEnemigo) {
        Tablero.posicionesEnemigos = posicionEnemigo;
    }
}
