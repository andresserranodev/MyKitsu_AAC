package com.puzzlebench.kitsu_aac.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.puzzlebench.kitsu_aac.DetailViewModel
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
                context?.let {
                    Glide.with(it)
                        .load(anime.coverImageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(coverImageItem)
                }

                name.text = anime.name
                rating.text = anime.ageRating
                ratingDescription.text = anime.ageRatingGuide
                status.text = resources.getString(R.string.item_status, anime.status)
                episodesCount.text =
                    resources.getString(R.string.item_episode, anime.episodeCount.toString())
                description.text = anime.description
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}