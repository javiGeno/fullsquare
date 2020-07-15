package morajavier.pdm.fullsquare


class Juego {
    //CREAR TIPO JUGADOR
    //var j1=jugador1
    //var j2=jugador


    var turno= (1..2).random()
    var turnoNuevo=true
    companion object{
        const val TIEMPO_ESPERA=1000 as Long
    }


    fun cambioTurno()
    {
        if(turno==1)
        {
            turno=2
        }
        else
        {
            turno=1
        }
    }
}