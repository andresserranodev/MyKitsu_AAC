package com.puzzlebench.kitsu_aac.presentation.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.puzzlebench.kitsu_aac.presentation.DetailViewModel
import com.puzzlebench.kitsu_aac.R
import com.puzzlebench.kitsu_aac.databinding.DetailFragmentBinding
import com.puzzlebench.kitsu_aac.di.ViewModelInjector

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: DetailFragmentBinding? = null

    private val binding get() = _binding!!

    private val animeListViewModel: DetailViewModel by viewModels {
        ViewModelInjector.provideDetailViewModelFactory(
            (requireContext().applicationContext as KitsuApplication).animeRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)

        animeListViewModel.getAnimeDetails(args.animeId)
        animeListViewModel.data.observe(viewLifecycleOwner) { anime ->
            with(binding) {
                name.text = anime.name
                rating.text = anime.ageRating
                ratingDescription.text = anime.ageRatingGuide
                status.text = resources.getString(R.string.item_status, anime.status)
                episodesCount.text =
                    resources.getString(R.string.item_episode, anime.episodeCount.toString())
                description.text = anime.description
                bindHeroFromUrl(coverImageItem, anime.coverImageUrl)
                bindTypeImage(showTypeItemImage, anime.showType)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinateMotion()
    }

    private fun coordinateMotion() {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / binding.appbarLayout.totalScrollRange.toFloat()
            binding.motionLayout.progress = seekPosition
        }
        binding.appbarLayout.addOnOffsetChangedListener(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}