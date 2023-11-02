
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    char[][] tablero = new char[6][6];
    char letraJugador;
    char letraEnemigo;

    int NUMERO_ENEMIGOS = 8;
    int[] posicionJugador = new int[2];
    int[] posicionSalida = new int[2];

    int[] posicionBomba = new int[2];

    int[][] posicionesVidas = new int[2][2];

    int[][] posicionesEnemigos = new int[NUMERO_ENEMIGOS][2];

    Random generator = new Random();

    public Tablero(char letraJugador, char letraEnemigo){
        this.letraJugador = letraJugador;
        this.letraEnemigo = letraEnemigo;
   }


    // Generacion de posiciones, orden especefico para evitar problemas en la comprobacion. Se han de llamar los metodos en este mismo orden.

    int[][] generarPosicionEnemigos(){
        int[] arr = new int[2];
        for(int i = 0; i < NUMERO_ENEMIGOS; i++){
            do {
                arr[0] = generator.nextInt(6);
                arr[1] = generator.nextInt(6);
            } while (Utils.contains(this.posicionesEnemigos, arr));
            this.posicionesEnemigos[i][0] = arr[0];
            this.posicionesEnemigos[i][1] = arr[1];
        }
        //Arrays.sort(posicionEnemigo, (a, b) -> a[0] - b[0]);
        return this.posicionesEnemigos;
    }
    int[] generarPosicionJugador(){
        int[] arr = new int[2];
        do {
            arr[0] = generator.nextInt(6);
            arr[1] = generator.nextInt(6);
        } while (Utils.contains(this.posicionesEnemigos, arr));
        posicionJugador[0] = arr[0];
        posicionJugador[1] = arr[1];
        return posicionJugador;
    }

    void generarCasillaSalida(){
        do {
            posicionSalida[0] = generator.nextInt(6);
            posicionSalida[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionSalida, posicionJugador) || Utils.contains(this.posicionesEnemigos, posicionSalida));
    }

    void generarCasillaBomba(){
        do {
            posicionBomba[0] = generator.nextInt(6);
            posicionBomba[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionBomba, posicionJugador)
                    || Arrays.equals(posicionBomba, posicionSalida)
                    || Utils.contains(this.posicionesEnemigos, posicionBomba));
    }

    void generarCasillasVidas(){
        int[] arr = new int[2];
        for(int i = 0; i < 2; i++){
            do {
                arr[0] = generator.nextInt(6);
                arr[1] = generator.nextInt(6);
            } while (Arrays.equals(arr, posicionJugador)
                    || Arrays.equals(arr, posicionSalida)
                    || Utils.contains(posicionesVidas, arr)
                    || Arrays.equals(arr, posicionBomba)
                    || Utils.contains(this.posicionesEnemigos, arr));
            posicionesVidas[i][0] = arr[0];
            posicionesVidas[i][1] = arr[1];
        }
    }


    void insertPosiciones(int[] posJugador, int[][] posEnemigos){
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
                if (Utils.contains(posEnemigos, arr)){
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


    public void killPosicionesEnemigos(int[] posicion){
        int[][] nuevaPosicionEnemigos = new int[NUMERO_ENEMIGOS - 1][2];
        int index = 0;
        for (int i =0; i < NUMERO_ENEMIGOS; i++){
            if (Arrays.equals(this.posicionesEnemigos[i], posicion)){
                continue;
            }
            nuevaPosicionEnemigos[index] = this.posicionesEnemigos[i];
            index++;
        }
        NUMERO_ENEMIGOS--;
        //System.out.println(Arrays.deepToString(this.posicionesEnemigos));
        //System.out.println(Arrays.deepToString(nuevaPosicionEnemigos));
        this.posicionesEnemigos = nuevaPosicionEnemigos;
        //System.out.println(Arrays.deepToString(this.posicionesEnemigos));
    }

    void killVida(int[] posicion){
        int[][] nuevaPosicionVida = new int[posicionesVidas.length - 1][2];
        int index = 0;
        for (int i = 0; i < posicionesVidas.length; i++){
            if (Arrays.equals(posicion, this.posicionesVidas[i])){
                continue;
            }
            nuevaPosicionVida[index] = this.posicionesVidas[i];
            index++;
        }
        this.posicionesVidas = nuevaPosicionVida;
    }

    void killBomba(int[] posicion){
        if (Arrays.equals(posicionBomba, posicion)){
            this.posicionBomba = null;
        }
    }

    public int[][] generar2PosicionesDistancia(int[] position) {
        int numRows = 6;
        int numCols = 6;

        ArrayList<int[]> result = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                int newRow = position[0] + i;
                int newCol = position[1] + j;

                // Check for overflow issues and ensure the position is within the valid range
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                    int[] newPosition = {newRow, newCol};
                    result.add(newPosition);
                }
            }
        }

        // Convert the ArrayList to int[][]
        int[][] resultArray = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }
}
