package br.ufpr.hp_trabalho.data.api

import br.ufpr.hp_trabalho.data.model.PersonagemCasa
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonagemCasaAPI {
    @GET("/api/characters/house/{house}")
    suspend fun getPersonagemByHouse(@Path("house") house: String): List<PersonagemCasa>
}