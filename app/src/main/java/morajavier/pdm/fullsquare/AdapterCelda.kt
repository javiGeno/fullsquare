package morajavier.pdm.fullsquare


import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Handler
import android.system.Os
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.celda.view.*

class AdapterCelda(var celdas:MutableList<CeldaDibujo>, val layout:Int, val parent: MainActivity): RecyclerView.Adapter<AdapterCelda.ViewHolderCelda>() {

    //LISTA QUE AÑADIRA ELEMENTOS VERDADEROS O FALSOS EN EL CASO DE QUE SE HAYA RELLENADO O NO UN CUADRO EN UN TURNO,
    //Y ASI CAMBIAR DE JUGADOR O SEGUIR CON EL MISMO
    var cuadrosRelleno= mutableListOf<Boolean>()
    //PARA LA ESPERA DE TIRADA ENTRE UN JUGADOR Y OTRO
    var esperdor= Handler()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCelda {

        val vistaRecycle = LayoutInflater.from(parent.context).inflate(layout, null, false)


        return ViewHolderCelda(vistaRecycle, this.parent)

    }

    override fun getItemCount(): Int {
        return celdas.count()
    }

    override fun onBindViewHolder(holder: ViewHolderCelda, position: Int) {




        holder.dibujar(position)

        //SE EMPIEZA COMPROBANDDO SI HAY UN NUEVO TURNO, DEBIDO A LA ESPERA QUE QUE IMPLEMENTAMOS ABAJO
        //EN TAL CASO ERMITIMOS PINTAR UN LATERAL DE UN CUADRO A UN JUGADOR AL DEL TURNO CORRESPONDIENTE
        //LUEGO SE ENCUENTRA LA ESPERA QUE PERMITE QUE SE EJECUTE EL HILO DE NOTIFYITEM, PARA SABER SI SE HA RELLENADO UN CUADRO
        //Y CAMBIAR DE TURNO O PERMANECER EN ÉL SI HA RELLENADO UN CUADRO
        holder.btn_left.setOnClickListener {
            Log.e("EVENTO ", "izquierda")

            if(parent.juegoEmpezar.turnoNuevo)
                if(pintarLateral(it))
                {
                    parent.juegoEmpezar.turnoNuevo=false

                    if(parent.juegoEmpezar.turno== 1)
                    {
                        comprobarCeldasColindantes( position, "izquierda",CeldaDibujo.ESTADO_JUG_1, holder )
                    }
                    else{
                        if(parent.juegoEmpezar.turno== 2)
                            comprobarCeldasColindantes(position, "izquierda",CeldaDibujo.ESTADO_JUG_2, holder )
                    }


                    println("TURNO  antes PULSADO "+parent.juegoEmpezar.turno)

                    if(ningunCuadroRelleno(cuadrosRelleno)) {
                        esperdor.postDelayed({
                            parent.juegoEmpezar.cambioTurno()
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }
                    else{
                    esperdor.postDelayed({
                        parent.juegoEmpezar.turnoNuevo = true
                        cuadrosRelleno.clear()
                    }, Juego.TIEMPO_ESPERA)
                }

                    println("TURNO  despues PULSADO "+parent.juegoEmpezar.turno)

                }


        }
        holder.btn_rigth.setOnClickListener {
            Log.e("EVENTO ", "derecha")

            if(parent.juegoEmpezar.turnoNuevo)
                if(pintarLateral(it))
                {
                    parent.juegoEmpezar.turnoNuevo= false


                    if(parent.juegoEmpezar.turno==1)
                    {
                        comprobarCeldasColindantes(position,"derecha",CeldaDibujo.ESTADO_JUG_1, holder )
                    }
                    else{
                        if(parent.juegoEmpezar.turno==2)
                            comprobarCeldasColindantes(position, "derecha",CeldaDibujo.ESTADO_JUG_2, holder  )
                    }
                    println("TURNO  antes PULSADO "+parent.juegoEmpezar.turno)
                    if(ningunCuadroRelleno(cuadrosRelleno)) {
                        esperdor.postDelayed({
                            parent.juegoEmpezar.cambioTurno()
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }
                    else{
                        esperdor.postDelayed({
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }

                    println("TURNO  despues PULSADO "+parent.juegoEmpezar.turno)


                }


        }
        holder.btn_bottom.setOnClickListener {
            Log.e("EVENTO ", "abajo")

            if(parent.juegoEmpezar.turnoNuevo)
                if(pintarLateral(it))
                {
                    parent.juegoEmpezar.turnoNuevo=false

                    if(parent.juegoEmpezar.turno== 1)
                    {
                        comprobarCeldasColindantes( position, "abajo", CeldaDibujo.ESTADO_JUG_1, holder  )
                    }
                    else{
                        if(parent.juegoEmpezar.turno== 2)
                         comprobarCeldasColindantes( position, "abajo",CeldaDibujo.ESTADO_JUG_2, holder  )
                    }


                    println("TURNO  antes PULSADO "+parent.juegoEmpezar.turno)
                    if(ningunCuadroRelleno(cuadrosRelleno)) {
                        esperdor.postDelayed({
                            parent.juegoEmpezar.cambioTurno()
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }
                    else{
                        esperdor.postDelayed({
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }

                    println("TURNO  despues PULSADO "+parent.juegoEmpezar.turno)

                }


        }
        holder.btn_top.setOnClickListener {
            Log.e("EVENTO ", "top")

            if(parent.juegoEmpezar.turnoNuevo)
                if(pintarLateral(it))
                {
                    parent.juegoEmpezar.turnoNuevo=false
                    if(parent.juegoEmpezar.turno== 1)
                    {
                        comprobarCeldasColindantes( position, "arriba",CeldaDibujo.ESTADO_JUG_1, holder )
                    }
                    else{
                        if(parent.juegoEmpezar.turno== 2)
                            comprobarCeldasColindantes( position, "arriba",CeldaDibujo.ESTADO_JUG_2, holder )
                    }



                    println("TURNO  antes PULSADO "+parent.juegoEmpezar.turno)
                    if(ningunCuadroRelleno(cuadrosRelleno)) {
                        esperdor.postDelayed({
                            parent.juegoEmpezar.cambioTurno()
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }
                    else{
                        esperdor.postDelayed({
                            parent.juegoEmpezar.turnoNuevo = true
                            cuadrosRelleno.clear()
                        }, Juego.TIEMPO_ESPERA)
                    }

                    println("TURNO  despues PULSADO "+parent.juegoEmpezar.turno)

                }


        }

    }



    private fun ningunCuadroRelleno(cuadrosRelleno: MutableList<Boolean>): Boolean {

        for(relleno in cuadrosRelleno)
        {
            println("CUADRO RELLENO? "+relleno)
            if(relleno)
                return false
        }

        return true

    }


    class ViewHolderCelda(itemView: View, val parent:MainActivity) : RecyclerView.ViewHolder(itemView) {

        var interiorCelda = itemView.celda
        var btn_left = itemView.btn_left
        var btn_rigth = itemView.btn_rigth
        var btn_bottom = itemView.btn_bottom
        var btn_top = itemView.btn_top

        fun dibujar(posicion: Int):Boolean {

            var celda=parent.adaptadorTabla.celdas[posicion]


            if (posicion >0 && posicion < 10) {
                btn_left.visibility = INVISIBLE
                btn_top.visibility= VISIBLE
                btn_rigth.visibility= VISIBLE
                btn_bottom.visibility= VISIBLE
                btn_bottom.setBackgroundColor(ContextCompat.getColor(parent,celda.bottom))
                btn_rigth.setBackgroundColor(ContextCompat.getColor(parent,celda.right))
                btn_top.setBackgroundColor(ContextCompat.getColor(parent,celda.top))
                if(todosMismoColor(celda.left,celda.bottom, celda.right, celda.top, R.color.colorBotonesNeutro))
                {
                    cambiarColor()
                    return true
                }

                return false

            } else {

                if (posicion % 10 == 0 && posicion != 0) {
                    btn_top.visibility = INVISIBLE
                    btn_left.visibility = VISIBLE
                    btn_rigth.visibility= VISIBLE
                    btn_bottom.visibility= VISIBLE
                    btn_bottom.setBackgroundColor(ContextCompat.getColor(parent,celda.bottom))
                    btn_rigth.setBackgroundColor(ContextCompat.getColor(parent,celda.right))
                    btn_left.setBackgroundColor(ContextCompat.getColor(parent,celda.left))

                    if(todosMismoColor(celda.left,celda.bottom, celda.right, celda.top, R.color.colorBotonesNeutro))
                    {
                       cambiarColor()
                        return true
                    }

                } else {
                    if (posicion != 0) {
                        btn_left.visibility = INVISIBLE
                        btn_top.visibility = INVISIBLE
                        btn_rigth.visibility= VISIBLE
                        btn_bottom.visibility= VISIBLE
                        btn_bottom.setBackgroundColor(ContextCompat.getColor(parent,celda.bottom))
                        btn_rigth.setBackgroundColor(ContextCompat.getColor(parent,celda.right))
                        if(todosMismoColor(celda.left,celda.bottom, celda.right, celda.top, R.color.colorBotonesNeutro))
                        {
                           cambiarColor()
                            return true
                        }

                    }
                    else{
                        btn_left.visibility = VISIBLE
                        btn_top.visibility= VISIBLE
                        btn_rigth.visibility= VISIBLE
                        btn_bottom.visibility= VISIBLE
                        btn_left.setBackgroundColor(ContextCompat.getColor(parent,celda.left))
                        btn_bottom.setBackgroundColor(ContextCompat.getColor(parent,celda.bottom))
                        btn_rigth.setBackgroundColor(ContextCompat.getColor(parent,celda.right))
                        btn_top.setBackgroundColor(ContextCompat.getColor(parent,celda.top))
                        if(todosMismoColor(celda.left,celda.bottom, celda.right, celda.top, R.color.colorBotonesNeutro))
                        {
                           cambiarColor()
                            return true
                        }
                    }


                }
                return false


            }


        }

        private fun cambiarColor() {
            //SI FALLA ESTA IMPLEMENTACIÓN, COMPROBAR EL COLOR DE LOS CUATRO LADOS
            println("TURNO AL RELLENAR "+parent.juegoEmpezar.turno)
            if(parent.juegoEmpezar.turno==1)
            {
                interiorCelda.setBackgroundColor(ContextCompat.getColor(parent, R.color.colorInteriorCeldaJ1))

            }
            else
            {
                if(parent.juegoEmpezar.turno==2)
                {
                    interiorCelda.setBackgroundColor(ContextCompat.getColor(parent, R.color.colorInteriorCeldaJ2))

                }

            }
        }


        fun todosMismoColor(btnLeft: Int?, btnBottom: Int?, btnRigth: Int?, btnTop: Int?, colorNeutro:Int): Boolean {

            return (btnLeft==btnBottom
                    && btnLeft==btnTop
                    && btnLeft==btnRigth
                    && btnBottom==btnTop
                    && btnLeft==btnRigth
                    && btnTop==btnLeft
                    && btnTop==btnRigth
                    && btnLeft== btnRigth
                    && btnLeft!=colorNeutro
                    && btnRigth!=colorNeutro
                    && btnBottom!= colorNeutro
                    && btnTop!= colorNeutro)
        }

    }

    private fun pintarLateral(btn: View):Boolean {


        if (btn.getBackground() is ColorDrawable) {

            var colorBoton = btn.getBackground() as ColorDrawable
            var colorRecurso=ContextCompat.getColor(parent, R.color.colorBotonesNeutro)


            if (colorBoton.color == colorRecurso ) {


                if (parent.juegoEmpezar.turno == 1) {
                    btn.setBackgroundColor(ContextCompat.getColor(parent, R.color.colorInteriorCeldaJ1))

                    return true

                } else//SERIA JUGADOR 2
                {
                    if(parent.juegoEmpezar.turno ==2)
                    {
                        btn.setBackgroundColor(ContextCompat.getColor(parent, R.color.colorInteriorCeldaJ2))
                        return true
                    }
                }
            }
            else{
                    return false
            }

        }

        return false
    }

    private fun cuadroRelleno(celda:CeldaDibujo, holder:ViewHolderCelda):Boolean
    {
        return holder.todosMismoColor(celda.left,celda.bottom, celda.right,celda.top, R.color.colorBotonesNeutro)

    }

    private fun comprobarCeldasColindantes( posicion: Int, btnPulsado:String, color:Int, holder:ViewHolderCelda) {


        var posicionIzquierda=posicion-1
        var posicionDerecha=posicion + 1
        var posicionAbajo=posicion + 10
        var posicionArriba=posicion-10

        lateinit var celdaColindanteDerecha :CeldaDibujo
        if(posicion.toString().get(posicion.toString().length-1)!='9')
            celdaColindanteDerecha=celdas[posicion+1]

        lateinit var celdaColindanteAbajo :CeldaDibujo
        if(posicion<90)
            celdaColindanteAbajo = celdas[posicion + 10]

        lateinit var celdaColindanteArriba:CeldaDibujo
        if(posicion>=10)
            celdaColindanteArriba=celdas[posicion-10]

        lateinit var celdaColindanteIzquierda:CeldaDibujo
        if(posicion.toString().get(posicion.toString().length-1)!= '0')
           celdaColindanteIzquierda=celdas[posicion-1]


            if(posicion==0)
            {
               when (btnPulsado){

                   "izquierda"->{
                       celdas[posicion].left=color
                       notifyItemChanged(posicion)
                       cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))



                   }
                   "derecha"->{
                       celdas[posicion].right=color
                       celdaColindanteDerecha.left=color
                       notifyItemChanged(posicion)
                       notifyItemChanged(posicionDerecha)

                       cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                       cuadrosRelleno.add(cuadroRelleno(celdas[posicionDerecha], holder))
                   }
                   "arriba"->{
                       celdas[posicion].top=color
                       notifyItemChanged(posicion)

                       cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))

                   }
                   "abajo"->{
                       celdas[posicion].bottom=color
                       celdaColindanteAbajo.top=color
                       notifyItemChanged(posicion)
                       notifyItemChanged(posicionAbajo)

                       cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                       cuadrosRelleno.add(cuadroRelleno(celdas[posicionAbajo], holder))


                   }
               }

            }
            else {

                if (posicion > 0 && posicion < 10) {

                    when (btnPulsado){

                        "izquierda"->{
                            celdas[posicion].left=color
                            celdaColindanteIzquierda.right=color
                            notifyItemChanged(posicion)
                            notifyItemChanged(posicionIzquierda)

                            cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                            cuadrosRelleno.add(cuadroRelleno(celdas[posicionIzquierda], holder))

                        }
                        "derecha"->{
                            celdas[posicion].right=color
                            notifyItemChanged(posicion)

                            cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))

                            if(posicionDerecha<10) {
                                celdaColindanteDerecha.left = color
                                notifyItemChanged(posicionDerecha)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicionDerecha], holder))
                            }
                        }
                        "arriba"->{
                            celdas[posicion].top=color
                            notifyItemChanged(posicion)

                            cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))

                        }
                        "abajo"->{
                            celdas[posicion].bottom=color
                            celdaColindanteAbajo.top=color
                            notifyItemChanged(posicion)
                            notifyItemChanged(posicionAbajo)

                            cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                            cuadrosRelleno.add(cuadroRelleno(celdas[posicionAbajo], holder))

                        }
                    }

                } else {

                    if (posicion % 10 == 0) {

                        when (btnPulsado){

                            "izquierda"->{
                                celdas[posicion].left=color
                                notifyItemChanged(posicion)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))

                            }
                            "derecha"->{
                                celdas[posicion].right=color
                                celdaColindanteDerecha.left=color
                                notifyItemChanged(posicion)
                                notifyItemChanged(posicionDerecha)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                                cuadrosRelleno.add(cuadroRelleno(celdas[posicionDerecha], holder))
                            }
                            "arriba"->{
                                celdas[posicion].top=color
                                celdaColindanteArriba.bottom=color
                                notifyItemChanged(posicion)
                                notifyItemChanged(posicionArriba)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                                cuadrosRelleno.add(cuadroRelleno(celdas[posicionArriba], holder))


                            }
                            "abajo"->{
                                celdas[posicion].bottom=color
                                notifyItemChanged(posicion)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))


                                if(posicionAbajo<100) {
                                    celdaColindanteAbajo.top = color
                                    notifyItemChanged(posicionAbajo)

                                    cuadrosRelleno.add(cuadroRelleno(celdas[posicionAbajo], holder))
                                }

                            }
                        }


                    } else {

                        when (btnPulsado){

                            "izquierda"->{
                                celdas[posicion].left=color
                                celdaColindanteIzquierda.right=color
                                notifyItemChanged(posicion)
                                notifyItemChanged(posicionIzquierda)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                                cuadrosRelleno.add(cuadroRelleno(celdas[posicionIzquierda], holder))
                            }
                            "derecha"->{
                                celdas[posicion].right=color
                                notifyItemChanged(posicion)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))

                                if(posicion.toString().get(posicion.toString().length-1)!='9') {
                                    celdaColindanteDerecha.left = color
                                    notifyItemChanged(posicionDerecha)

                                    cuadrosRelleno.add(cuadroRelleno(celdas[posicionDerecha], holder))
                                }
                            }
                            "arriba"->{
                                celdas[posicion].top=color
                                celdaColindanteArriba.bottom=color
                                notifyItemChanged(posicion)
                                notifyItemChanged(posicionArriba)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))
                                cuadrosRelleno.add(cuadroRelleno(celdas[posicionArriba], holder))
                            }
                            "abajo"->{
                                celdas[posicion].bottom=color
                                notifyItemChanged(posicion)

                                cuadrosRelleno.add(cuadroRelleno(celdas[posicion], holder))


                                if(posicionAbajo<100) {
                                    celdaColindanteAbajo.top = color
                                    notifyItemChanged(posicionAbajo)

                                    cuadrosRelleno.add(cuadroRelleno(celdas[posicionAbajo], holder))

                                }

                            }
                        }


                    }

                }
            }


    }

    /*private fun rellenarLado( posicion: Int, color:Int, lado: String) {

        when (lado){

            "izquierda"->{
                celdas[posicion].left=color
            }
            "derecha"->{
                celdas[posicion].right=color
            }
            "arriba"->{
                celdas[posicion].top=color
            }
            "abajo"->{
                celdas[posicion].bottom=color
            }
        }









    }*/

   /* private fun ladosIgualesCeldasColindantes(btn_bottom:Int, btn_top:Int, btn_left:Int, btn_rigth:Int, btnPulsado:View, posadoColindante:Int): Boolean {



        if(posadoColindante==0)
        {
            return (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                parent,
                btn_bottom
            ) &&
                    (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                parent,
                btn_left
            ) &&
                    (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                parent,
                btn_rigth
            ) &&
                    (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                parent,
                btn_top
            )
        }
        else {

            if (posadoColindante > 0 && posadoColindante < 10) {

                return (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                    parent,
                    btn_bottom
                ) &&
                        (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                    parent,
                    btn_rigth
                ) &&
                        (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                    parent,
                    btn_top
                )

            } else {

                if (posadoColindante % 10 == 0) {

                    return (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                        parent,
                        btn_bottom
                    ) &&
                            (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                        parent,
                        btn_left
                    ) &&
                            (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                        parent,
                        btn_rigth
                    )

                } else {

                    return (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                        parent,
                        btn_bottom
                    ) &&
                            (btnPulsado.getBackground() as ColorDrawable).color == ContextCompat.getColor(
                        parent,
                        btn_rigth)


                }
            }
        }

    }*/



}