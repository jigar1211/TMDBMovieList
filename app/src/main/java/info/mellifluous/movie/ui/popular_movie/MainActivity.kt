package info.mellifluous.movie.ui.popular_movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import info.mellifluous.movie.R
import info.mellifluous.movie.data.api.TheMovieDBClient
import info.mellifluous.movie.data.api.TheMovieDBInterface
import info.mellifluous.movie.data.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    lateinit var movieRepository: MoviePagedListRepository
    lateinit var movieAdapter:PopularMoviePagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MoviePagedListRepository(apiService)
        viewModel = getViewModel()
        movieAdapter = PopularMoviePagedListAdapter(this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return 1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        };
        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = movieAdapter

        manageList()

        ivSort.setOnClickListener(View.OnClickListener {
            val popup = PopupMenu(this@MainActivity, ivSort)
            popup.menuInflater
                .inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.popular->{ viewModel.sortList("popular")
                        manageList()}
                    R.id.rate->{ viewModel.sortList("top_rated")
                        manageList()}
                }
                true
            }
            popup.show()
        })
    }

    private fun manageList() {

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }


    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(movieRepository) as T
            }
        })[MainActivityViewModel::class.java]
    }

}
