package morajavier.pdm.fullsquare



class CeldaDibujo() {

    var relleno:Int=ESTADO_SIN_JUG_INTERIOR
    var left:Int=ESTADO_SIN_JUG_LATERAL
    var top:Int=ESTADO_SIN_JUG_LATERAL
    var bottom:Int=ESTADO_SIN_JUG_LATERAL
    var right:Int=ESTADO_SIN_JUG_LATERAL



    companion object{
        const val ESTADO_SIN_JUG_INTERIOR=R.color.colorInteriorCelda
        const val ESTADO_SIN_JUG_LATERAL=R.color.colorBotonesNeutro
        const val ESTADO_JUG_1= R.color.colorInteriorCeldaJ1
        const val ESTADO_JUG_2=R.color.colorInteriorCeldaJ2
    }



}