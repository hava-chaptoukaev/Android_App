package networks

import adapters.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepository {
    private val service: ArticleService
    private val myBaseURL = "https://newsapi.org/"
    //private val myURL = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-09-06&sortBy=publishedAt&apiKey=7f5db0654b464597a9c82c03db98d79f/"
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(myBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun list(): List<Article> {
        val response = service.list().execute()
        return response.body() ?: emptyList()
    }
}