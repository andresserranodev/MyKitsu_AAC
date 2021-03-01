package com.puzzlebench.kitsu_aac.android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puzzlebench.kitsu_aac.AnimeListViewModel
import com.puzzlebench.kitsu_aac.databinding.ActivityMainBinding
import com.puzzlebench.kitsu_aac.di.AnimeListViewModelFactory
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val animeRepository = (applicationContext as KitsuApplication).animeRepository
        val viewModelFactory = AnimeListViewModelFactory(animeRepository)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AnimeListViewModel::class.java)
        val gridLayoutManager = GridLayoutManager(this, 1)
        val animeAdapter = AnimeListAdapter()
        with(binding.itemsList) {
            adapter = animeAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()
                    val totalItemCount = gridLayoutManager.itemCount
                    viewModel.listScrolled(lastVisibleItem, totalItemCount)
                }
            })
        }
        lifecycleScope.launch {
            when (val result = viewModel.allAnime) {
                is AnimeState.Error -> {
                    Toast.makeText(applicationContext, result.error.message, Toast.LENGTH_LONG)
                        .show()
                }
                is AnimeState.Success -> result.data.collectLatest {
                    animeAdapter.submitData(it)
                }
            }
        }
    }
}