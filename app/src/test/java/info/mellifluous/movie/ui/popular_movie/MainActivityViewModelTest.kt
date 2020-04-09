package info.mellifluous.movie.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import info.mellifluous.movie.data.api.TheMovieDBClient
import info.mellifluous.movie.data.api.TheMovieDBInterface
import info.mellifluous.movie.data.repository.MovieDataSourceFactory
import info.mellifluous.movie.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class MainActivityViewModelTest {

    val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

    private val compositeDisposable = CompositeDisposable()
    private lateinit var movieRepositoryMock: MoviePagedListRepository
    lateinit var movieRepository: MoviePagedListRepository
    private lateinit var mainActivityViewModel: MainActivityViewModel
    val moviePagedList: LiveData<PagedList<Movie>> = MutableLiveData()

    @Before
    fun setUp() {
        movieRepository = MoviePagedListRepository(apiService)
        movieRepositoryMock = Mockito.mock(MoviePagedListRepository::class.java) as MoviePagedListRepository
        mainActivityViewModel = MainActivityViewModel(movieRepositoryMock)
    }


    @Test
    fun getMoviePagedList() {
        `when`(movieRepositoryMock.fetchLiveMoviePagedList(compositeDisposable,"popular"))
    }

}