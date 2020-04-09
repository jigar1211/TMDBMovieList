package info.mellifluous.movie.data.api

import info.mellifluous.movie.data.vo.MovieDetails
import info.mellifluous.movie.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/{sortBy}")
    fun getPopularMovie(@Path("sortBy") sortBy: String,@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}