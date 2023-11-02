import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Peter extends Jugador {
    Attribute bgColor = Attribute.BACK_COLOR(18, 18, 18);

    public Peter(){
        this.nombre = "Peter";
        this.enemigo = "Meg";
        this.vidas = 3;
        this.tablero = initTablero();
    }

    @Override
    void printTableroJugador(boolean trucos) {
        for (char[] fila : this.tablero.tablero) {
            String regex = trucos ? "[VX]" : "[MVX]";
            String fila1 = Arrays.toString(fila);
            String fila2 = fila1.replaceAll(regex, "L");
            fila1 = fila2.replaceAll("[\\[\\],]", " ");

            fila = fila1.toCharArray();
            for (int i = 0; i < fila.length; i++) {
                if (fila[i] == this.nombre.charAt(0)) {
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.BRIGHT_BLUE_TEXT(), bgColor));
                } else if (fila[i] == this.enemigo.charAt(0)){
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.BRIGHT_RED_TEXT(), bgColor));
                } else if (fila[i] == 'S'){
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.BRIGHT_GREEN_TEXT(), bgColor));
                } else {
                    System.out.print(colorize(String.valueOf(fila[i]), Attribute.WHITE_TEXT(), bgColor));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}