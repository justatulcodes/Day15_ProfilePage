package com.example.day15_profilepage

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.example.day15_profilepage.databinding.ActivityMainBinding

//https://dribbble.com/shots/2509591-Material-Profile/attachments/493983

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting reference of  ExpandableTextView
        // getting reference of  ExpandableTextView
        val expTv = binding.expandTextView
        // calling setText on the ExpandableTextView so that
        // text content will be  displayed to the user

        // calling setText on the ExpandableTextView so that
        // text content will be  displayed to the user
        expTv.text = getString(R.string.biography)

        setStatusBarColorAndAppearance("#FFFFFFFF", true)

        val posterList = listOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p10
        )

        val adapter = MoviePosterAdapter(posterList)
        binding.rvMovies.adapter = adapter

        setUpToolbar(binding)
    }

    private fun setUpToolbar(binding: ActivityMainBinding) {
        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val manuInflater : MenuInflater = menuInflater
        manuInflater.inflate(R.menu.search_menu_icon, menu)
        return true
    }

    fun Activity.setStatusBarColorAndAppearance(statusBarColor: String, isLight: Boolean ) {
        try {
            window.statusBarColor = (Color.parseColor(statusBarColor))// Or we can use from resource color:  ContextCompat.getColor(mContext, R.color.colorPrimary)
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}