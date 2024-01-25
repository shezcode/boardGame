package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Michael extends Jugador {

    public Michael(){
        this.nombre = "Michael";
        this.enemigo = "Toby";
        this.vidas = 3;
        this.textColor = Attribute.TEXT_COLOR(105);
    }
}
