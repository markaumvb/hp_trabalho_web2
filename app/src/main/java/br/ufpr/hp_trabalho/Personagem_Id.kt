package br.ufpr.hp_trabalho

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.ufpr.hp_trabalho.data.api.PersonagemAPI
import br.ufpr.hp_trabalho.data.api.PersonagemCasaAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Personagem_Id : AppCompatActivity() {

    private lateinit var etPersonagemId: EditText
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var api: PersonagemAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personagem_id)


        etPersonagemId = findViewById(R.id.etPersonagemId)
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PersonagemAPI::class.java)

    }

    fun buscarPersonagem(view: View){
        val PersonagemId = etPersonagemId.text.toString()


        if (PersonagemId.isEmpty()) {
            Toast.makeText(this, "Digite o ID do personagem", Toast.LENGTH_SHORT).show()
            return
        }
        progressBar.visibility = ProgressBar.VISIBLE

        lifecycleScope.launch (Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getPersonagemById(PersonagemId)
                }

                progressBar.visibility = ProgressBar.GONE

                if (response.isNotEmpty()) {
                    val character = response[0]
                    tvResult.text = "Nome: ${character.name}\nCasa: ${character.house}"
                } else {
                    tvResult.text = "Personagem não encontrado"
                }

            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                tvResult.text = "Erro ao buscar personagem : ${e.message}"
            }
        }
    }

}
