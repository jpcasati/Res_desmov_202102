package com.example.rev_desmov_202102

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa(
    val nome: String,
    val fone: String,
    @PrimaryKey
    val id: Int) {

    override fun toString(): String {
        return "$id / $nome - $fone"
    }

}