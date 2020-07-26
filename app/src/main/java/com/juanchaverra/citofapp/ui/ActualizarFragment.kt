package com.juanchaverra.citofapp.ui

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_actualizar.view.te_nombre
import kotlinx.android.synthetic.main.fragment_actualizar.view.te_tel
import kotlinx.android.synthetic.main.fragment_crear.view.*

class ActualizarFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_actualizar, container, false)

        var b = 0
        var idApto = 0
        root.bt_actualizar.setOnClickListener {

            val aptoDao: AptoDAO = SesionRoom.database.AptoDAO()
            val telefono = root.te_actualnum.text.toString()

            if (b==0){
                var apto = aptoDao.buscarAptoNum(telefono)
                if (apto != null){
                    idApto = apto.id
                    root.te_nombre.setText(apto.nombre)
                    root.te_tel.setText(apto.tel)
                    root.tv_apto.setText(apto.cod.toString())
                    root.bt_actualizar.text = "ACTUALIZAR"
                    root.te_actualnum.isEnabled = false
                    b = 1
                }
                else{
                    Toast.makeText(requireActivity().applicationContext, "Número no encontrado", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                val apto = aptoDao.updateApto(
                    Apto(
                        idApto,
                        root.te_nombre.text.toString(),//root.te_nombre.text.toString(),
                        root.te_tel.text.toString(),
                        root.tv_apto.text.toString().toInt()
                    )
                )
                b = 0
                root.te_actualnum.isEnabled = true
                root.bt_actualizar.text = "BUSCAR"
                Toast.makeText(requireActivity().applicationContext, "Datos actualizados", Toast.LENGTH_SHORT).show()
                root.te_nombre.getText()?.clear()
                root.te_tel.getText()?.clear()
                root.tv_apto.setText("Apartamento")



            }


        }

        return root
    }
}