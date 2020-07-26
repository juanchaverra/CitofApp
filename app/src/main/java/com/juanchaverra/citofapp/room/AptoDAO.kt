package com.juanchaverra.citofapp.room
import androidx.room.*

@Dao
interface AptoDAO {

    @Insert
    fun insertApto(apto: Apto)

    @Query( "SELECT * FROM tabla_apartamentos WHERE codigo LIKE:cod")
    fun buscarApto(cod:String): Apto

    @Query("SELECT * FROM tabla_apartamentos WHERE telefono LIKE:tel")
    fun buscarAptoNum(tel:String): Apto

    @Update
    fun updateApto(apto: Apto)

    @Delete
    fun deleteApto(apto: Apto)
}