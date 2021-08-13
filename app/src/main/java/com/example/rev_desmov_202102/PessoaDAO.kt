package com.example.rev_desmov_202102

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PessoaDAO {

    @Insert
    fun salvar(p: Pessoa)

    @Update
    fun atualizar(p: Pessoa)

    @Query("SELECT * FROM Pessoa WHERE id = :id")
    fun find(id: Int): Pessoa

    @Query("SELECT * FROM Pessoa")
    fun listar(): List<Pessoa>
}