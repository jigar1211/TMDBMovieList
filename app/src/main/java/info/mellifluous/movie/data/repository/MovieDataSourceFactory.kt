package info.mellifluous.movie.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import info.mellifluous.movie.data.api.TheMovieDBInterface
import info.mellifluous.movie.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable,
                              private val sortType:String)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable,sortType)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}