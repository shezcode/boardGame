import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Bart extends Jugador {
    Attribute bgColor = Attribute.BACK_COLOR(18, 18, 18);
    public Bart(){
        this.nombre = "Bart";
        this.enemigo = "Krusty";
        this.vidas = 3;
        this.tablero = initTablero();
    }

    @Override
    void printTableroJugador() {
        for (char[] fila : this.tablero.tablero) {
            String fila1 = Arrays.toString(fila);
            String fila2 = fila1.replaceAll("[KSVX]", "L");
            fila1 = fila2.replaceAll("[\\[\\],]", " ");

            fila = fila1.toCharArray();
            for (int i = 0; i < fila.length; i++) {
                if (fila[i] == this.nombre.charAt(0)) {
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.BRIGHT_YELLOW_TEXT(), bgColor));
                } else {
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.WHITE_TEXT(), bgColor));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
