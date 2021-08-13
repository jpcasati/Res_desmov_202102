package com.example.rev_desmov_202102

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class EditarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        edtId.visibility = View.INVISIBLE

        val id = intent.getIntExtra("id", 0)

        val p = PessoaDB.getInstance(this)!!.pessoaDao().find(id)

        edtNome.setText(p.nome)
        edtFone.setText(p.fone)
        edtId.setText(p.id.toString())

    }

    fun atualizarPessoa() {

        val p = Pessoa(edtNome.text.toString(), edtFone.text.toString(), edtId.text.toString().toInt())
        PessoaDB.getInstance(this)!!.pessoaDao().atualizar(p)

        Toast.makeText(this, "Contato atualizado!", Toast.LENGTH_SHORT)

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuCancelar)
            finish()
        else if(item.itemId == R.id.menuSalvar)
            atualizarPessoa()

        return super.onOptionsItemSelected(item)
    }
}