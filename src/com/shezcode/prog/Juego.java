package com.shezcode.prog;

import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    private static final Scanner scanner = new Scanner(System.in);
    void intro(){
        System.out.println(colorize("Board Game", Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println(colorize("Reach the exit square 'E' before the other player and without losing all your lives! \n" +
                        "To move, insert to number of squares to move ( from 1 to 3 ) and the direction (w, a, s, d) \n" +
                        "For example: 2d to move 2 squares to the right.", Attribute.BRIGHT_MAGENTA_TEXT()));
    }

    int pedirDimensionTablero(){
        System.out.print(colorize("Introduce board dimensions (6x6 to 10x10) [default: 6x6]: ", Attribute.BRIGHT_GREEN_TEXT()));
        String input = scanner.nextLine();
        int dimension;
        try {
            dimension = Integer.parseInt(String.valueOf(input));
            if (dimension < 6 || dimension > 10) {
                System.out.println(colorize("Invalid input, defaulting to 6x6", Attribute.BRIGHT_RED_TEXT()));
                dimension = 6;
            }
        } catch (InputMismatchException | StringIndexOutOfBoundsException |NumberFormatException e){
            System.out.println(colorize("Invalid input, defaulting to 6x6", Attribute.BRIGHT_RED_TEXT()));
            dimension = 6;
        }
        return dimension;
    }

    int pedirDificultad(){
        String input;
        try {
            do {
                System.out.print(colorize("Select a difficulty: Easy, Medium or Hard (E, M, H) [default: E]: ", Attribute.BRIGHT_GREEN_TEXT()));
                input = scanner.nextLine();
            } while (input.toUpperCase().charAt(0) != 'E' && input.toUpperCase().charAt(0) != 'M' && input.toUpperCase().charAt(0) != 'H');
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(colorize("Invalid input, defaulting to Easy", Attribute.BRIGHT_RED_TEXT()));
            input = "E";
        }

        return switch (input.toUpperCase().charAt(0)){
            case 'M' -> 12;
            case 'H' -> 16;
            default -> 8;
        };
    }
    enumJugador pedirDatos(int jugador){
        String input;
        enumJugador character;
        do {
            System.out.printf(colorize("Player %d, choose your character!", Attribute.BRIGHT_GREEN_TEXT()), jugador);
            System.out.println();
            System.out.print("OPTIONS: ");
            System.out.print(colorize("HOMER ", Attribute.BRIGHT_CYAN_TEXT()));
            System.out.print(colorize("BART ", Attribute.BRIGHT_YELLOW_TEXT()));
            System.out.print(colorize("PETER ", Attribute.BRIGHT_BLUE_TEXT()));
            System.out.print(colorize("MICHAEL ", Attribute.TEXT_COLOR(105)));
            System.out.print(colorize("SHERLOCK ", Attribute.TEXT_COLOR(208)));
            System.out.print(colorize("WALTER ", Attribute.TEXT_COLOR(196)));
            System.out.print(colorize("SHREK \n", Attribute.BRIGHT_GREEN_TEXT()));
            System.out.print("Your choice: ");
            input = scanner.nextLine().toUpperCase();
            try {
                character = enumJugador.valueOf(input);
            } catch (IllegalArgumentException e){
                System.out.println(colorize("Invalid input! Choose a valid option: ", Attribute.BRIGHT_RED_TEXT()));
                character = null;
            }
        } while (character == null);

        return character;
    }

   Jugador switchCharacters(enumJugador nombre) {
       return switch(nombre) {
           case HOMER -> new Jugador("Homer", "Flanders", Attribute.BRIGHT_CYAN_TEXT());
           case BART -> new Jugador("Bart", "Krusty", Attribute.BRIGHT_YELLOW_TEXT());
           case PETER -> new Jugador("Peter", "Meg", Attribute.BRIGHT_BLUE_TEXT());
           case SHREK -> new Jugador("Shrek", "Principe", Attribute.GREEN_TEXT());
           case MICHAEL -> new Jugador("Michael", "Toby", Attribute.TEXT_COLOR(105));
           case SHERLOCK -> new Jugador("Sherlock", "Moriarty", Attribute.TEXT_COLOR(208));
           case WALTER -> new Jugador("Walter", "Gus", Attribute.TEXT_COLOR(196));
       };
   }
}
