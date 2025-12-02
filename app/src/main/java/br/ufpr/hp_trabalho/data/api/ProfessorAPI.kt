    package br.ufpr.hp_trabalho.data.api

    import br.ufpr.hp_trabalho.data.model.Professor
    import retrofit2.http.GET
        interface ProfessorAPI {

            @GET("api/characters/staff")
            suspend fun getProfessores(): List<Professor>
        }
