package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Walter extends Jugador {

    public Walter(){
        this.nombre = "Walter";
        this.enemigo = "Gus";
        this.vidas = 3;
        this.textColor = Attribute.TEXT_COLOR(196);
    }
}


