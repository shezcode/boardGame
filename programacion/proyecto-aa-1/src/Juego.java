import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    private static final Scanner scanner = new Scanner(System.in);
    void intro(){
        System.out.println(colorize("JUEGO CON TABLERO", Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println(colorize("Llega hasta la salida antes de perder tus vidas para ganar la partida. \n" +
                        "Para moverte, introduce la cantidad de casillas a desplazarse ( de 1 a 3 ) y la direccion (w, a, s, d) \n" +
                        "Por ejemplo: 2d para desplazarte 2 casillas hacia la derecha."
   , Attribute.BRIGHT_MAGENTA_TEXT()));
    }

    int pedirDimensionTablero(){
        System.out.print(colorize("Introduce la dimension del tablero (6x6 hasta 10x10) [default: 6x6]: ", Attribute.BRIGHT_GREEN_TEXT()));
        String input = scanner.nextLine();
        int dimension;
        try {
            dimension = Integer.parseInt(String.valueOf(input.charAt(0)));
        } catch (InputMismatchException e){
            System.out.println(colorize("Input incorrecto, introduciendo dimension por defecto 6x6", Attribute.BRIGHT_RED_TEXT()));
            dimension = 6;
        }
        return dimension;
    }

    int pedirDificultad(){
        System.out.println(colorize("Elige la dificultad: Facil - Media - Dificil [F, M, D]", Attribute.BRIGHT_GREEN_TEXT()));
        String input;
        do {
            System.out.print("Tu dificultad: ");
            input = scanner.nextLine();
        } while (input.toUpperCase().charAt(0) != 'F' && input.toUpperCase().charAt(0) != 'M' && input.toUpperCase().charAt(0) != 'D');

        return switch (input.toUpperCase().charAt(0)){
            case 'F' -> 8;
            case 'M' -> 12;
            case 'D' -> 16;
            default -> 10;
        };
    }
    enumJugador pedirDatos(int jugador){
        String input;
        enumJugador character;
        do {
            System.out.printf(colorize("Jugador %d, elige tu jugador!", Attribute.BRIGHT_GREEN_TEXT()), jugador);
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
