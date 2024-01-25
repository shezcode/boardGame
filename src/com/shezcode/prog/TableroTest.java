package com.shezcode.prog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TableroTest {

    Tablero tableroTest;
    int[][] posEnemigos;
    int[] posJugador;
    int[] posSalida;
    int[] posBomba;
    int[][] posVidas;

    @BeforeEach
    public void init(){
        tableroTest = new Tablero('M', 'E', 8, 8);
        posEnemigos = tableroTest.generarPosicionEnemigos();
        posJugador = tableroTest.generarPosicionJugador();
        posSalida = tableroTest.generarCasillaSalida();
        posBomba = tableroTest.generarCasillaBomba();
        posVidas = tableroTest.generarCasillasVidas();
    }

    @Test
    void tableroImplementadoCorrecto() {
        Assertions.assertEquals(8, tableroTest.getDimension());
        Assertions.assertEquals(8, tableroTest.getNUMERO_ENEMIGOS());
        Assertions.assertEquals('M', tableroTest.getLetraJugador());
        Assertions.assertEquals('E', tableroTest.getLetraEnemigo());
    }

    @Test
    void generarPosicionesUnicasEnemigos(){
        Assertions.assertFalse(Utils.contains(posEnemigos, posJugador));
        Assertions.assertFalse(Utils.contains(posEnemigos, posSalida));
        Assertions.assertFalse(Utils.contains(posEnemigos, posBomba));
        Assertions.assertFalse(Arrays.deepEquals(posEnemigos, posVidas));
    }

    @Test
    void generarPosicionesUnicasJugador(){
        Assertions.assertFalse(Arrays.equals(posJugador, posBomba));
        Assertions.assertFalse(Utils.contains(posEnemigos, posJugador));
        Assertions.assertFalse(Utils.contains(posEnemigos, posSalida));
        Assertions.assertFalse(Utils.contains(posEnemigos, posBomba));
        Assertions.assertFalse(Arrays.deepEquals(posEnemigos, posVidas));
    }

}