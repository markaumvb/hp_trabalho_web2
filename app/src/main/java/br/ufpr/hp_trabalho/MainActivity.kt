package br.ufpr.hp_trabalho

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    fun sair(view: View) {
        finish()
    }

    fun listarpersonagem(view: View){
        val intent = Intent(this, Personagem_Id::class.java)
        startActivity(intent)
    }

    fun listarprofessor(view: View){

    }

    fun listarestudante(view: View){

    }
}