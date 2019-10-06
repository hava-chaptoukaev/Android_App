package networks

import adapters.Article
import retrofit2.Call
import retrofit2.http.GET


interface ArticleService {
    @GET("/articles")
    fun list(): Call<List<Article>>
}