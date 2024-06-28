package com.example.cartoons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class   MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartoonAdapter: CartoonAdapter
    private lateinit var cartoons: List<Cartoon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                cartoons = RetrofitInstance.api.getCartoons()
                cartoons.forEach { cartoon ->
                    Log.d("MainActivity", "Cartoon: $cartoon")
                }
                cartoonAdapter = CartoonAdapter(cartoons) { cartoon ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("cartoon", cartoon)
                    startActivity(intent)
                }
                recyclerView.adapter = cartoonAdapter
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainActivity", "Error fetching cartoons", e)
            }
        }
    }
}
