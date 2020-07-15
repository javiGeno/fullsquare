package morajavier.pdm.fullsquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var tablaceldas:MutableList<CeldaDibujo>
    lateinit var adaptadorTabla: AdapterCelda
    lateinit var juegoEmpezar:Juego



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tablaceldas=crearMatrizEnteros()
        juegoEmpezar=Juego()

        Log.e("PRIMER TURNO ", ""+juegoEmpezar.turno)

        tabla.layoutManager = GridLayoutManager(this, 10)
        adaptadorTabla= AdapterCelda(tablaceldas, R.layout.celda, this)
        tabla.adapter=adaptadorTabla


    }


    private fun crearMatrizEnteros(): MutableList<CeldaDibujo> {

        var lista= mutableListOf<CeldaDibujo>()

        for (i in 0 until 100)
        {
            lista.add(CeldaDibujo())

        }

        return lista
    }

}
