package br.ufpr.hp_trabalho

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        enableEdgeToEdge()
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

        lifecycleScope.launch (Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.???
                }

                progressBar.visibility = ProgressBar.GONE



            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                tvResult.text = "Erro ao buscar professor."
            }
        }
    }
    }
}