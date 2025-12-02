package br.ufpr.hp_trabalho.data.api

import br.ufpr.hp_trabalho.data.model.Personagem
import retrofit2.http.GET
import retrofit2.http.Path

class PersonagemAPI {
    interface HPApi {
        @GET("api/character/{id}")
        suspend fun getPersonagemById(@Path("id") id: String): List<Personagem>
    }
}