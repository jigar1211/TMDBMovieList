package info.mellifluous.movie.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import info.mellifluous.movie.data.repository.NetworkState
import info.mellifluous.movie.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val movieRepository: MoviePagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var moviePagedList: LiveData<PagedList<Movie>>

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    init {
        moviePagedList = movieRepository.fetchLiveMoviePagedList(compositeDisposable,"popular")
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun sortList(sortBy:String) {
        moviePagedList = movieRepository.fetchLiveMoviePagedList(compositeDisposable,sortBy)
    }
}