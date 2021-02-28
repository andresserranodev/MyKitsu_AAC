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
        val animeRepository = (applicationContext as KitsuApplication).animeRepository
        binding.testbtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                when (val response = animeRepository.getAnimeList()) {
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