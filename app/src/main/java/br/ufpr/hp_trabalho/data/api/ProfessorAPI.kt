package br.ufpr.hp_trabalho.data.api

import br.ufpr.hp_trabalho.data.model.Professor
import retrofit2.http.GET

class ProfessorAPI {
    interface HPApi {

        @GET("api/characters/staff")
        suspend fun getStaff(): List<Professor>
    }
}