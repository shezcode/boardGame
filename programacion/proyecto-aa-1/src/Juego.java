import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Juego {
    private static final Scanner scanner = new Scanner(System.in);
    void intro(){
        System.out.println(colorize("JUEGO CON TABLERO", Attribute.BRIGHT_MAGENTA_TEXT()));
        System.out.println(colorize("Es un juego, con un tablero...", Attribute.BRIGHT_MAGENTA_TEXT()));
    }

    int pedirDimensionTablero(){
        System.out.print("Introduce la dimension del tablero(6x6 hasta 10x10): [default: 6x6]");
        String input = scanner.nextLine();
        int dimension;
        try {
            dimension = Integer.parseInt(String.valueOf(input.charAt(0)));
        } catch (InputMismatchException e){
            System.out.println("Input incorrecto, introduciendo dimension por defecto 6x6");
            dimension = 6;
        }
        return dimension;
    }

    int pedirDificultad(){
        System.out.println("Elige la dificultad: Facil - Media - Dificil [F, M, D]");
        String input = null;
        do {
            System.out.print("Tu dificultad: ");
            input = scanner.nextLine();
        } while (input.toUpperCase().charAt(0) != 'F' && input.toUpperCase().charAt(0) != 'M' && input.toUpperCase().charAt(0) != 'D');

        return switch (input.toUpperCase().charAt(0)){
            case 'F' -> 8;
            case 'M' -> 10;
            case 'D' -> 12;
            default -> 6;
        };
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
