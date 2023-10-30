public class Utils {
    static boolean contains(int[] array, int value){
        for (int number : array){
            if (number == value){
                return true;
            }
        }
        return false;
    }

    static boolean validarInput(String input){
       if (input.length() != 2) {
           return false;
       }

       try {
          int casillas = Integer.parseInt(String.valueOf(input.charAt(0)));
          if (casillas < 1 || casillas > 3){
              return false;
          }
       } catch (NumberFormatException e){
           return false;
       }

       char direccion = input.charAt(1);
       if (direccion != 'w' && direccion != 'a' && direccion != 's' && direccion != 'd'){
           return false;
       }

       return true;
    }

}
