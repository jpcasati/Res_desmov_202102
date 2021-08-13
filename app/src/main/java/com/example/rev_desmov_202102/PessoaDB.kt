package com.example.rev_desmov_202102

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pessoa::class], version = 1)
abstract class PessoaDB: RoomDatabase() {

    abstract fun pessoaDao(): PessoaDAO

    companion object {

        private var database: PessoaDB? = null

        private val DATABASE = "PDB2"

        fun getInstance(context: Context): PessoaDB? {

            if(database == null)
                return criaBanco(context)
            else
                return database

        }

        private fun criaBanco(context: Context): PessoaDB {
            return Room.databaseBuilder(context, PessoaDB::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }

}