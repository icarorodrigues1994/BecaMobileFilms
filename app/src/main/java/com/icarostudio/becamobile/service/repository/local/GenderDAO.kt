package com.icarostudio.becamobile.service.repository.local

import androidx.room.*
import com.icarostudio.becamobile.service.repository.model.GenreModel

@Dao
interface GenderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(genres: List<GenreModel>) // SALVANDO NO BANCO

    @Query("DELETE FROM Genrer") // DELETANDO DO BANCO
    fun clear()

    @Query("SELECT * FROM Genrer")  // RECUPERANDO DO BANCO
    fun list(): List<GenreModel>

    @Query("SELECT name FROM Genrer WHERE id= :id")  // RECUPERANDO PELO ID NO BANCO
    fun getDescription(id: Int): String

}