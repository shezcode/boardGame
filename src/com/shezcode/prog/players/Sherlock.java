package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Sherlock extends Jugador {

    public Sherlock(){
        this.nombre = "Sherlock";
        this.enemigo = "Moriarty";
        this.vidas = 3;
        this.textColor = Attribute.TEXT_COLOR(208);
    }
}

