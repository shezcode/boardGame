package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Bart extends Jugador {

    public Bart(){
        this.nombre = "Bart";
        this.enemigo = "Krusty";
        this.vidas = 3;
        this.textColor = Attribute.BRIGHT_YELLOW_TEXT();
    }

}
