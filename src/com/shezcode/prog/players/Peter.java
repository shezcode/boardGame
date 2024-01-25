package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Peter extends Jugador {
    public Peter(){
        this.nombre = "Peter";
        this.enemigo = "Meg";
        this.vidas = 3;
        this.textColor = Attribute.BRIGHT_BLUE_TEXT();
    }
}
