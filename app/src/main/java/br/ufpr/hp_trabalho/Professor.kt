package br.ufpr.hp_trabalho

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.ufpr.hp_trabalho.data.api.ProfessorAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Professor : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var api: ProfessorAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professor)

        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ProfessorAPI::class.java)
    }

    fun listarProfessores(view: View) {
        progressBar.visibility = ProgressBar.VISIBLE
        tvResult.text = "Carregando professores..."

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val professores = withContext(Dispatchers.IO) {
                    api.getProfessores()
                }

                progressBar.visibility = ProgressBar.GONE

                if (professores.isNotEmpty()) {
                    var lista = ""
                    for (professor in professores) {
                        lista += "${professor.name}\n"
                    }
                    tvResult.text = lista
                } else {
                    tvResult.text = "Nenhum professor encontrado"
                }

            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                tvResult.text = "Erro ao buscar professores: ${e.message}"
            }
        }
    }
}