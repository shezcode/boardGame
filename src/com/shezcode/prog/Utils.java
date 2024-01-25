package com.shezcode.prog;

import java.util.Arrays;

public class Utils {
    static boolean contains(int[][] array, int[] value){
       for (int[] fila : array){
           if (Arrays.equals(fila, value)){
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
       if (direccion != 'w' && direccion != 'a' && direccion != 's' && direccion != 'd' && direccion != 'b' && direccion != 't'){
           return false;
       }

       return true;
    }

    static void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
