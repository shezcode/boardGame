import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Shrek extends Jugador {
    public Shrek(){
        this.nombre = "Shrek";
        this.enemigo = "Principe";
        this.vidas = 3;
        this.textColor = Attribute.GREEN_TEXT();
    }
}
