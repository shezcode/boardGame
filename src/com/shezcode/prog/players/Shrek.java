package com.shezcode.prog.players;

import com.diogonunes.jcolor.Attribute;
import com.shezcode.prog.Jugador;

public class Shrek extends Jugador {
    public Shrek(){
        this.nombre = "Shrek";
        this.enemigo = "Principe";
        this.vidas = 3;
        this.textColor = Attribute.GREEN_TEXT();
    }
}
