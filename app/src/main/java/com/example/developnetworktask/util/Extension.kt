package com.example.developnetworktask.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.developnetworktask.R
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*


object Extension {

    fun Fragment.withFragment(dataBinding: ViewDataBinding) {
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

     fun showSnackBar(message: String,activity:Activity) {
        val snackbar = Snackbar.make(
           activity .findViewById<View>(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }


    fun hideProgressBar(progressBar: ProgressBar) {

        progressBar.visibility = View.INVISIBLE
    }

    fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }
    fun ImageView.loadImage(url: String, context: Context) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions().placeholder(R.drawable.progress_animation))
            .into(this)
    }

    fun RecyclerView.init(
        context: Context?,
        adapter: RecyclerView.Adapter<*>?,
    ) {
        val layoutManager = LinearLayoutManager(context)
        this.layoutManager = layoutManager
        this.setHasFixedSize(true)
        this.adapter = adapter
    }


}