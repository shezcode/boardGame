import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Homer extends Jugador {
   Attribute bgColor = Attribute.BACK_COLOR(18, 18, 18);

   public Homer(){
      this.nombre = "Homer";
      this.enemigo = "Flanders";
      this.vidas = 3;
      this.textColor = Attribute.BRIGHT_CYAN_TEXT();
   }
}
