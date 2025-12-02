package br.ufpr.hp_trabalho

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.ufpr.hp_trabalho.data.api.PersonagemCasaAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonagemCasa : AppCompatActivity() {


    private lateinit var rgCasas: RadioGroup
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var api: PersonagemCasaAPI


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personagem_casa)


        rgCasas = findViewById(R.id.rgCasas)
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PersonagemCasaAPI::class.java)
    }

    fun listarEstudantes(view: View) {
        val selectedId = rgCasas.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedId)
        val casaSelecionada = radioButton.text.toString()

        progressBar.visibility = ProgressBar.VISIBLE

        lifecycleScope.launch (Dispatchers.Main) {
            try {
                val estudantes = withContext(Dispatchers.IO) {
                    api.getPersonagemByHouse(casaSelecionada)
                }

                progressBar.visibility = ProgressBar.GONE

                if (estudantes.isNotEmpty()) {
                    var resultado = ""
                    for (estudante in estudantes) {
                        resultado += "${estudante.name}\n"
                    }
                    tvResult.text = resultado
                } else {
                    tvResult.text = "Nenhum estudante encontrado"
                }

            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                tvResult.text = "Erro ao buscar estudantes: ${e.message}"
            }
        }
    }
}