package com.example.rev_desmov_202102

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var lista: List<Pessoa> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNovo.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        lstPessoa.setOnItemClickListener { parent, view, position, id ->
            val p = lista.get(position)

            val i = Intent(this, EditarActivity::class.java)

            i.putExtra("id", p.id)

            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        carregarLista()
    }

    fun carregarLista() {

        lista = PessoaDB.getInstance(this)!!.pessoaDao().listar()

        val adp = ArrayAdapter<Pessoa>(
            this,
            android.R.layout.simple_list_item_1,
            lista)

        lstPessoa.adapter = adp
    }
}