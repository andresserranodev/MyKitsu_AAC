package com.puzzlebench.kitsu_aac.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.puzzlebench.kitsu_aac.R

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(context, "Load ${args.animeId}", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }
}