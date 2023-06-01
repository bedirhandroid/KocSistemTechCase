package com.bedirhandroid.kocsistemtechcase.util

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bedirhandroid.kocsistemtechcase.R
import com.bumptech.glide.Glide

infix fun View.visibleIf(bool: Boolean) =
    if (bool) visible() else gone()

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun <T> observerNotNull(observer: (t: T) -> Unit) = Observer<T> {
    it?.let {
        observer(it)
    }
}

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo)
        .into(this)
}