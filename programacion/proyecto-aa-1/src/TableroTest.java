import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TableroTest {

    @Test
    void tableroImplementadoCorrecto() {
        int dimension = 7;
        int numEnemigos = 12;
        var tableroTest = new Tablero('M', 'E', dimension, numEnemigos);
        Assertions.assertEquals(dimension, tableroTest.getDimension());
        Assertions.assertEquals(numEnemigos, tableroTest.getNUMERO_ENEMIGOS());
        Assertions.assertEquals('M', tableroTest.getLetraJugador());
        Assertions.assertEquals('E', tableroTest.getLetraEnemigo());
    }

    @Test
    void generarPosicionesUnicas(){
        var tableroTest = new Tablero('M', 'E', 8, 6);
        int[][] posEnemigos = tableroTest.generarPosicionEnemigos();
        int[] posJugador = tableroTest.generarPosicionJugador();
        int[] posSalida = tableroTest.generarCasillaSalida();
        int[] posBomba = tableroTest.generarCasillaBomba();
        int[][] posVidas = tableroTest.generarCasillasVidas();
        Assertions.assertFalse(Arrays.equals(posJugador, posBomba));
    }
}