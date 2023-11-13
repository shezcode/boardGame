import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {



    @BeforeEach
    void setUp() {
    }

    @Test
    void containsTrue() {
        int[][] arr = {{1,1}, {1, 2}};
        int[] value = {1, 2};
        Assertions.assertTrue(Utils.contains(arr, value));
    }

    @Test
    void validarInputConLengthIncorrecto() {
        Assertions.assertFalse(Utils.validarInput("asd"));
    }

    @Test
    void validarInputFormatoCorrecto() {
        Assertions.assertTrue(Utils.validarInput("1d"));
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