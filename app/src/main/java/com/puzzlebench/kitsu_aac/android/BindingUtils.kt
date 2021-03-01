package com.puzzlebench.kitsu_aac.android

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.puzzlebench.kitsu_aac.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(RoundedCorners(19))
            .into(view)
    }
}

@BindingAdapter("typeImage")
fun bindTypeImage(view: ImageView, type: String?) {
    when (type) {
        "TV" -> {
            view.setImageResource(R.drawable.ic_tv_type)
        }
        "movie" -> {
            view.setImageResource(R.drawable.ic_movie_type)
        }
    }
}
