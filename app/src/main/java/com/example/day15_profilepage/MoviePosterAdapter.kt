package com.example.day15_profilepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.day15_profilepage.databinding.MoviesListItemviewBinding

class MoviePosterAdapter(private val posterList: List<Int>)
    : RecyclerView.Adapter<MoviePosterAdapter.ViewHolder>(){
    class ViewHolder(binding: MoviesListItemviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MoviesListItemviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return posterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posterId = posterList[position]
        holder.ivPoster.setImageResource(posterId)
    }


}