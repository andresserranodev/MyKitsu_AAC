package com.puzzlebench.kitsu_aac.presentation.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.puzzlebench.kitsu_aac.R
import com.puzzlebench.kitsu_aac.presentation.AnimeListViewModel
import com.puzzlebench.kitsu_aac.databinding.ListFragmentBinding
import com.puzzlebench.kitsu_aac.di.ViewModelInjector
import com.puzzlebench.kitsu_aac.presentation.ListState
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null

    private val binding get() = _binding!!

    private val animeListViewModel: AnimeListViewModel by viewModels {
        ViewModelInjector.provideAnimeListViewModelFactory(
                (requireContext().applicationContext as KitsuApplication).animeRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ListFragmentBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.show()
        }
        val animeAdapter = AnimeListAdapter { anime ->
            val action =
                    ListFragmentDirections.actionListFragmentToDetailFragment(anime.id)
            Navigation.findNavController(binding.root).navigate(action)
        }
        initList(animeAdapter)
        initPagerSubscription(animeAdapter)
        animeListViewModel.viewState.observe(::getLifecycle, ::handleViewState)
        return binding.root
    }

    private fun initList(animeAdapter: AnimeListAdapter) {
        val gridLayoutManager = GridLayoutManager(context, 1)
        with(binding.itemsList) {
            adapter = animeAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()
                    val totalItemCount = gridLayoutManager.itemCount
                    animeListViewModel.listScrolled(lastVisibleItem, totalItemCount)
                }
            })
        }
    }

    private fun handleViewState(detailsViewState: ListState) {
        when (detailsViewState) {
            is ListState.FetchDataError -> displayErrorMessage(detailsViewState.error)
            ListState.HideProgressBar -> binding.progressBar.visibility = View.GONE
            ListState.ShowProgressBar -> binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun displayErrorMessage(error: String) {
        binding.progressBar.visibility = View.GONE
        activity?.let {
            Snackbar.make(
                    it.findViewById(android.R.id.content),
                    getString(R.string.network_error, error),
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.retry)) {
                        animeListViewModel.initRepository()
                    }.show()
        }
    }

    private fun initPagerSubscription(animeAdapter: AnimeListAdapter) {
        lifecycleScope.launch {
            when (val result = animeListViewModel.allAnime) {
                is AnimeState.Error -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_LONG)
                            .show()
                }
                is AnimeState.Success -> result.data.collectLatest {
                    animeAdapter.submitData(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}