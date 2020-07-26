package com.juanchaverra.citofapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.juanchaverra.citofapp.R
import com.juanchaverra.citofapp.room.Apto
import com.juanchaverra.citofapp.room.AptoDAO
import com.juanchaverra.citofapp.room.SesionRoom
import kotlinx.android.synthetic.main.fragment_crear.view.*
import java.sql.Types.NULL

class CrearFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_crear, container, false)

        root.bt_guardar.setOnClickListener{
            val nombre = root.te_nombre.text.toString()
            val tel = root.te_tel.text.toString()
            val cod = root.te_apto.text.toString()

            val apto = Apto(NULL, nombre, tel, cod.toInt())

            val aptoDao: AptoDAO = SesionRoom.database.AptoDAO()

            aptoDao.insertApto(apto)
            Toast.makeText(requireActivity().applicationContext, "Dato registrado", Toast.LENGTH_SHORT).show()
            root.te_nombre.getText()?.clear()
            root.te_tel.getText()?.clear()
            root.te_apto.getText()?.clear()

        }

        return root
    }
}