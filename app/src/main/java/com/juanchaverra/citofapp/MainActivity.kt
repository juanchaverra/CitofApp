package com.juanchaverra.citofapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.TelecomManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.juanchaverra.citofapp.room.AptoDAO
import com.juanchaverra.citofapp.room.SesionRoom
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_llamar.setOnClickListener {

            val cod = et_nombre.text.toString()

            val aptoDao: AptoDAO = SesionRoom.database.AptoDAO()
            val apto = aptoDao.buscarApto(cod)

            if (apto != null){
                tv_nombre.text = apto.nombre
                val intent = Intent(Intent.ACTION_CALL)
                val num = apto.tel
                intent.data = Uri.parse("tel:$num")
                val chooser = Intent.createChooser(intent, "tittle")
              //  startActivity(intent)
                val uri = Uri.fromParts("tel", num, null)
                val intent2 = Intent(Intent.ACTION_DIAL)
                intent2.setData(Uri.parse("tel:" + num))
                val extras = Bundle()
                extras.putBoolean(TelecomManager.EXTRA_CALL_BACK_NUMBER, true)
                startActivity(intent, extras)
            }
            else{
                Toast.makeText(this,"Apartamento no encontrado", Toast.LENGTH_SHORT).show()
            }




        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_abrir){
            //Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, DatosActivity::class.java)

            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}