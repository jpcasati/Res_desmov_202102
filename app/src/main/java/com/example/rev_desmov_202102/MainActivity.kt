package com.example.rev_desmov_202102

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var lista: List<Pessoa> = ArrayList()
    var ordenacaoPadrao: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ordenacaoPadrao = getSharedPreferences("ordenacao", Context.MODE_PRIVATE)

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
        val ordem = ordenacaoPadrao!!.getString("ordem", "id").toString()
        carregarLista(ordem)
    }

    fun carregarLista(ordem: String) {

        ordenacaoPadrao!!.edit().putString("ordem", ordem).apply()

        if(ordem.equals("nome")) {
            lista = PessoaDB.getInstance(this)!!.pessoaDao().listarPorNome()
        } else {
            lista = PessoaDB.getInstance(this)!!.pessoaDao().listar()
        }



        val adp = ArrayAdapter<Pessoa>(
            this,
            android.R.layout.simple_list_item_1,
            lista)

        lstPessoa.adapter = adp
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listagem, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuListaNome) {

            carregarLista("nome")

            Toast.makeText(this, "Lista ordenada por Nome", Toast.LENGTH_LONG).show()

        } else if(item.itemId == R.id.menuListaId) {

            carregarLista("id")

            Toast.makeText(this, "Lista ordenada por ID", Toast.LENGTH_LONG).show()

        }

        return super.onOptionsItemSelected(item)
    }
}