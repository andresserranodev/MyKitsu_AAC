package com.puzzlebench.kitsu_aac.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puzzlebench.kitsu_aac.repository.AnimeItemState
import com.puzzlebench.kitsu_aac.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val remoteFetchAnime = (applicationContext as KitsuApplication).remoteFetchAnime
        binding.testbtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                when (val response = remoteFetchAnime.fetchAnime(15, 0)) {
                    is AnimeItemState.Success -> {
                        response.data.forEach {
                            println(it.name)
                        }
                    }
                    is AnimeItemState.Error -> println(message = response.error.message)
                }
            }
        }
    }
}