import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void containsTrue() {
        int[][] arr = {{1,1}, {1, 2}};
        int[] value = {1, 2};
        Assertions.assertTrue(Utils.contains(arr, value));
    }


    @Test
    void containsCase1True() {
        int[][] arr = {{0,0}, {1,3}, {3,6}, {4,2}, {5, 0}};
        int[] value = {4, 2};
        Assertions.assertTrue(Utils.contains(arr, value));
    }

    @Test
    void containsCase2False() {
        int[][] arr = {{0,0}, {1,3}, {3,6}, {4,2}, {5, 0}};
        int[] value = {2, 2};
        Assertions.assertFalse(Utils.contains(arr, value));
    }

    @Test
    void validarInputConStringVacio() {
        Assertions.assertFalse(Utils.validarInput(""));
    }

    @Test
    void validarInputConLengthIncorrecto() {
        Assertions.assertFalse(Utils.validarInput("asd"));
    }

    @Test
    void validarInputSinNumero() {
        Assertions.assertFalse(Utils.validarInput("sd"));
    }

    @Test
    void validarInputDerecha() {
        Assertions.assertTrue(Utils.validarInput("1d"));
    }

    @Test
    void validarInputIzquierda() {
        Assertions.assertTrue(Utils.validarInput("2a"));
    }

    @Test
    void validarInputArriba() {
        Assertions.assertTrue(Utils.validarInput("3w"));
    }

    @Test
    void validarInputAbajo() {
        Assertions.assertTrue(Utils.validarInput("1s"));
    }

    @Test
    void validarInputTruco() {
        Assertions.assertTrue(Utils.validarInput("1t"));
    }

    @Test
    void validarInputBomba() {
        Assertions.assertTrue(Utils.validarInput("1b"));
    }

}