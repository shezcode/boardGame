package com.shezcode.prog;

import com.diogonunes.jcolor.Attribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class JugadorTest {
    public Jugador jugadorTest = new Jugador("Homer", "Flanders", Attribute.BRIGHT_CYAN_TEXT());

    @BeforeEach
    public void init() {
        int[] posJugador = {2, 2};
        jugadorTest.tablero = jugadorTest.initDummy(6, posJugador);
    }

    @Test
    void moverDerecha() {
        Movimiento mov = new Movimiento(1, 'd');
        int[] expected = {2, 3};
        jugadorTest.registrarMovimiento(mov);
        Assertions.assertArrayEquals(jugadorTest.tablero.getPosicionJugador(), expected);
    }


    @Test
    void moverIzq() {
        Movimiento mov = new Movimiento(1, 'a');
        int[] expected = {2, 1};
        jugadorTest.registrarMovimiento(mov);
        Assertions.assertArrayEquals(jugadorTest.tablero.getPosicionJugador(), expected);
    }

    @Test
    void moverArriba() {
        Movimiento mov = new Movimiento(1, 'w');
        int[] expected = {1, 2};
        jugadorTest.registrarMovimiento(mov);
        Assertions.assertArrayEquals(jugadorTest.tablero.getPosicionJugador(), expected);
    }

    @Test
    void moverAbajo() {
        Movimiento mov = new Movimiento(1, 's');
        int[] expected = {3, 2};
        jugadorTest.registrarMovimiento(mov);
        Assertions.assertArrayEquals(jugadorTest.tablero.getPosicionJugador(), expected);
    }

    @Test
    void caerEnEnemigo(){
        jugadorTest.tablero.setNUMERO_ENEMIGOS(1);
        jugadorTest.setVidas(3);
        int [][] posEnemigos = new int[1][2];
        posEnemigos[0] = new int[]{3, 3};
        jugadorTest.tablero.setPosicionesEnemigos(posEnemigos);
        jugadorTest.evaluarMovimiento(new int[]{3,3});
        jugadorTest.tablero.setPosicionJugador(new int[]{3, 3});
        Assertions.assertArrayEquals(posEnemigos[0], jugadorTest.tablero.getPosicionJugador());
        jugadorTest.vidas = jugadorTest.decreaseVidas();
        Assertions.assertEquals(2, jugadorTest.getVidas());
    }

    @Test
    void caerEnBomba(){
        int[] posBomba = new int[]{4,4};
        jugadorTest.tablero.setPosicionJugador(new int[]{4,3});
        jugadorTest.registrarMovimiento(new Movimiento(1, 'd'));
        Assertions.assertArrayEquals(jugadorTest.tablero.getPosicionJugador(), posBomba);
    }

}
