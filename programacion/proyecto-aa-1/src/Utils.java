public class Utils {
    static boolean contains(int[] array, int value){
        for (int number : array){
            if (number == value){
                return true;
            }
        }
        return false;
    }

}
