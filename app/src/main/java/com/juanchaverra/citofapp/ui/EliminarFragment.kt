package com.juanchaverra.citofapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.juanchaverra.citofapp.R
import com.juanchaverra.citofapp.room.Apto
import com.juanchaverra.citofapp.room.AptoDAO
import com.juanchaverra.citofapp.room.SesionRoom
import kotlinx.android.synthetic.main.fragment_actualizar.view.*
import kotlinx.android.synthetic.main.fragment_eliminar.view.*
import kotlinx.android.synthetic.main.fragment_eliminar.view.te_tel


class EliminarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_eliminar, container, false)

        root.bt_eliminar.setOnClickListener {
            val telefono = root.te_tel.text.toString()

            val aptoDao: AptoDAO = SesionRoom.database.AptoDAO()
            val apto: Apto = aptoDao.buscarAptoNum(telefono)

            if (apto!= null){
                val alertDialog: AlertDialog?= activity?.let{
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setMessage("Desea Eliminar a "+ apto.nombre + " Apto: " + apto.cod.toString())
                        setPositiveButton(
                            "Aceptar"
                        ){ dialog, id ->
                            aptoDao.deleteApto(apto)
                            root.te_tel.getText()?.clear()
                            Toast.makeText(requireActivity().applicationContext,
                                "Registro eliminado",
                                Toast.LENGTH_SHORT).show()
                        }
                        setNegativeButton(
                            "Cancelar"
                        ){dialog, id ->

                        }
                    }
                    builder.create()
                }
                alertDialog?.show()
            } else{
                Toast.makeText(requireActivity().applicationContext,
                "Número no encontrado",
                Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}