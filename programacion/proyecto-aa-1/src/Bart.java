import com.diogonunes.jcolor.Attribute;
import java.util.Arrays;
import static com.diogonunes.jcolor.Ansi.colorize;

public class Bart extends Jugador {

    public Bart(){
        this.nombre = "Bart";
        this.enemigo = "Krusty";
        this.vidas = 3;
        this.textColor = Attribute.BRIGHT_YELLOW_TEXT();
    }

}
