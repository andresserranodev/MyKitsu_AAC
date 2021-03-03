package com.puzzlebench.kitsu_aac.presentation.android

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.puzzlebench.kitsu_aac.R
import com.puzzlebench.kitsu_aac.databinding.DetailFragmentBinding
import com.puzzlebench.kitsu_aac.di.ViewModelInjector
import com.puzzlebench.kitsu_aac.presentation.DetailViewModel
import com.puzzlebench.kitsu_aac.presentation.DetailsViewState
import com.puzzlebench.kitsu_aac.repository.Anime

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
        animeListViewModel.viewState.observe(::getLifecycle, ::handleViewState)
        return binding.root
    }

    private fun handleViewState(detailsViewState: DetailsViewState) {
        when (detailsViewState) {
            is DetailsViewState.LoadInformation -> loadAnimeInfo(detailsViewState.anime)
            is DetailsViewState.OpenVideo -> watchYoutubeVideo(detailsViewState.videoId)
            DetailsViewState.NoVideo ->
                Toast.makeText(context, resources.getString(R.string.no_video_message), Toast.LENGTH_LONG).show()
        }
    }

    private fun loadAnimeInfo(anime: Anime) {
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
            watch.setOnClickListener {
                animeListViewModel.playVideo(anime.youtubeVideoId)
            }
        }
    }

    private fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$id"))
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
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