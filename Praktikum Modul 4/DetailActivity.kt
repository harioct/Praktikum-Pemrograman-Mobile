package com.example.cartoons

import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var cartoonImage: ImageView
    private lateinit var cartoonTitle: TextView
    private lateinit var cartoonCreator: TextView
    private lateinit var cartoonYear: TextView
    private lateinit var cartoonGenre: TextView
    private lateinit var cartoonEpisodes: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        toolbar.navigationIcon?.setColorFilter(resources.getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP)

        cartoonImage = findViewById(R.id.cartoonImage)
        cartoonTitle = findViewById(R.id.cartoonTitle)
        cartoonCreator = findViewById(R.id.cartoonCreator)
        cartoonYear = findViewById(R.id.cartoonYear)
        cartoonGenre = findViewById(R.id.cartoonGenre)
        cartoonEpisodes = findViewById(R.id.cartoonEpisodes)

        val cartoon = intent.getSerializableExtra("cartoon") as Cartoon
        cartoonTitle.text = cartoon.title
        cartoonCreator.text = cartoon.creator.joinToString(", ")
        cartoonYear.text = cartoon.year.toString()
        cartoonGenre.text = cartoon.genre.joinToString(", ") ?: "No genre available"
        cartoonEpisodes.text = "Total Episodes: ${cartoon.episodes}"

        toolbar.title = cartoon.title

        Glide.with(this)
            .load(cartoon.image)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(cartoonImage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
