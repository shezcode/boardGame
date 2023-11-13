import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    private int dimension;
    char[][] tablero;
    private char letraJugador;
    private char letraEnemigo;
    private int NUMERO_ENEMIGOS;
    private int[] posicionJugador = new int[2];
    private int[] posicionSalida = new int[2];
    private int[] posicionBomba = new int[2];
    private int[][] posicionesVidas = new int[2][2];
    int[][] posicionesEnemigos = new int[NUMERO_ENEMIGOS][2];
    Random generator = new Random();

    public Tablero(char letraJugador, char letraEnemigo, int dimension, int numEnemigos){
        this.letraJugador = letraJugador;
        this.letraEnemigo = letraEnemigo;
        this.dimension = dimension;
        this.NUMERO_ENEMIGOS = numEnemigos;
        this.tablero = new char[dimension][dimension];
   }

    // Generacion de posiciones, orden especefico para evitar problemas en la comprobacion. Se han de llamar los metodos en este mismo orden.

    int[][] generarPosicionEnemigos(){
        this.posicionesEnemigos = new int[NUMERO_ENEMIGOS][2];
        int[] arr = new int[2];
        for(int i = 0; i < this.NUMERO_ENEMIGOS; i++){
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

    int[] generarCasillaSalida(){
        do {
            posicionSalida[0] = generator.nextInt(6);
            posicionSalida[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionSalida, posicionJugador) || Utils.contains(this.posicionesEnemigos, posicionSalida));
        return posicionSalida;
    }

    int[] generarCasillaBomba(){
        do {
            posicionBomba[0] = generator.nextInt(6);
            posicionBomba[1] = generator.nextInt(6);
        } while (Arrays.equals(posicionBomba, posicionJugador)
                    || Arrays.equals(posicionBomba, posicionSalida)
                    || Utils.contains(this.posicionesEnemigos, posicionBomba));
        return posicionBomba;
    }

    int[][] generarCasillasVidas(){
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
        return posicionesVidas;
    }


    void insertPosiciones(int[] posJugador, int[][] posEnemigos){
        for (int i = 0; i < this.dimension; i++){
            for (int j = 0; j < this.dimension; j++){
                int[] arr = {i, j};
                if (Arrays.equals(posJugador, arr)) {
                    tablero[i][j] = this.letraJugador;
                } else if (Arrays.equals(posicionSalida, arr)){
                    tablero[i][j] = 'S';
                } else if (Arrays.equals(posicionBomba, arr)){
                    // Llamo X a la posicion de la bomba, para evitar interferencia con la B de Jugadores. Bart.
                    tablero[i][j] = 'X';
                } else if (Utils.contains(posEnemigos, arr)){
                    tablero[i][j] = this.letraEnemigo;
                } else if (Utils.contains(posicionesVidas, arr)){
                    tablero[i][j] = 'V';
                } else {
                    tablero[i][j] = 'L';
                }
            }
        }
    }

    int[] getPosicionJugador() {
        return this.posicionJugador;
    }

    void setPosicionJugador(int[] posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    void killPosicionesEnemigos(int[] posicion){
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
        this.posicionesEnemigos = nuevaPosicionEnemigos;
    }

    void killVida(int[] posicion){
        int[][] nuevaPosicionVida = new int[posicionesVidas.length - 1][2];
        int index = 0;
        for (int[] posicionesVida : posicionesVidas) {
            if (Arrays.equals(posicion, posicionesVida)) {
                continue;
            }
            nuevaPosicionVida[index] = posicionesVida;
            index++;
        }
        this.posicionesVidas = nuevaPosicionVida;
    }

    void killBomba(int[] posicion){
        if (Arrays.equals(posicionBomba, posicion)){
            this.posicionBomba = null;
        }
    }

    int[][] generar2PosicionesDistancia(int[] position) {
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

    int getDimension() {
        return dimension;
    }

    void setDimension(int dimension) {
        this.dimension = dimension;
    }

    int getNUMERO_ENEMIGOS() {
        return NUMERO_ENEMIGOS;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public char getLetraJugador() {
        return letraJugador;
    }

    public char getLetraEnemigo() {
        return letraEnemigo;
    }

    public int[] getPosicionSalida() {
        return posicionSalida;
    }

    public int[] getPosicionBomba() {
        return posicionBomba;
    }

    public int[][] getPosicionesVidas() {
        return posicionesVidas;
    }

    public int[][] getPosicionesEnemigos() {
        return posicionesEnemigos;
    }

    public Random getGenerator() {
        return generator;
    }

}
