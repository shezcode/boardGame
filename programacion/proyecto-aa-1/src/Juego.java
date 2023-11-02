import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;

import java.util.Objects;
import java.util.Scanner;

public class Juego {
    private static final Scanner scanner = new Scanner(System.in);
    void intro(){
        System.out.println(colorize("JUEGO CON TABLERO", Attribute.BRIGHT_MAGENTA_TEXT()));
        System.out.println(colorize("Es un juego, con un tablero...", Attribute.BRIGHT_MAGENTA_TEXT()));
    }
    enumJugador pedirDatos(int jugador){
        String input;
        enumJugador character;
        do {
            System.out.printf("Jugador %d, elige tu jugador!", jugador);
            System.out.println();
            System.out.print("OPCIONES: ");
            System.out.print(colorize("HOMER ", Attribute.BRIGHT_CYAN_TEXT()));
            System.out.print(colorize("BART ", Attribute.BRIGHT_YELLOW_TEXT()));
            System.out.print(colorize("PETER ", Attribute.BRIGHT_BLUE_TEXT()));
            System.out.print(colorize("SHREK \n", Attribute.BRIGHT_GREEN_TEXT()));
            System.out.print("Tu eleccion: ");
            input = scanner.nextLine().toUpperCase();
            try {
                character = enumJugador.valueOf(input);
            } catch (IllegalArgumentException e){
                character = null;
            }
        } while (character == null);

        return character;
    }
}
