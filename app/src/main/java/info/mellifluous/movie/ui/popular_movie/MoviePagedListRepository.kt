package info.mellifluous.movie.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import info.mellifluous.movie.data.api.POST_PER_PAGE
import info.mellifluous.movie.data.api.TheMovieDBInterface
import info.mellifluous.movie.data.repository.MovieDataSource
import info.mellifluous.movie.data.repository.MovieDataSourceFactory
import info.mellifluous.movie.data.repository.NetworkState
import info.mellifluous.movie.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

open class MoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable,sortType:String) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable,sortType)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}