
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Tablero {

    char[][] tablero = new char[6][6];
    char letraJugador;
    char letraEnemigo;
    static final int NUMERO_ENEMIGOS= 8;
    int[] posicionJugador = new int[2];
    int[] posicionSalida = new int[2];

    static int[][] posicionEnemigo = new int[NUMERO_ENEMIGOS][2];

    Random generator = new Random();

    public Tablero(char letraJugador, char letraEnemigo){
        this.letraJugador = letraJugador;
        this.letraEnemigo = letraEnemigo;
    }

    int[] generarPosicionJugador(){
        posicionJugador[0] = generator.nextInt(6);
        posicionJugador[1] = generator.nextInt(6);
        return posicionJugador;
    }

    void generarPosicionEnemigos(){
        int[] arr = new int[2];
        for(int i = 0; i < NUMERO_ENEMIGOS; i++){
            do {
                arr[0] = generator.nextInt(6);
                arr[1] = generator.nextInt(6);
            } while (Arrays.equals(arr, posicionJugador) || Arrays.equals(arr, posicionSalida) || Utils.contains(posicionEnemigo, arr));
            posicionEnemigo[i][0] = arr[0];
            posicionEnemigo[i][1] = arr[1];
        }
       //Arrays.sort(posicionEnemigo, (a, b) -> a[0] - b[0]);
    }

    void generarCasillaSalida(){
        do {
            posicionSalida[0] = generator.nextInt(6);
            posicionSalida[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionSalida, posicionJugador));
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
                if (Utils.contains(posicionEnemigo, arr)){
                    tablero[i][j] = this.letraEnemigo;
                }
            }
        }
    }

   void printTablero(){
        for (char[] fila : tablero){
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    public int[] getPosicionJugador() {
        return this.posicionJugador;
    }

    public void setPosicionJugador(int[] posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    public static int[][] getPosicionEnemigo() {
        return posicionEnemigo;
    }

    public static void setPosicionEnemigo(int[][] posicionEnemigo) {
        Tablero.posicionEnemigo = posicionEnemigo;
    }
}
