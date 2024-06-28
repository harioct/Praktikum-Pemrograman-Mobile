package com.example.cartoons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CartoonAdapter(
    private val cartoons: List<Cartoon>,
    private val onItemClick: (Cartoon) -> Unit
) : RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cartoon, parent, false)
        return CartoonViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(cartoons[position], onItemClick)
    }

    override fun getItemCount(): Int = cartoons.size

    class CartoonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cartoonImage: ImageView = itemView.findViewById(R.id.cartoonImage)
        private val cartoonTitle: TextView = itemView.findViewById(R.id.cartoonTitle)
        private val cartoonCreator: TextView = itemView.findViewById(R.id.cartoonCreator)

        fun bind(cartoon: Cartoon, onItemClick: (Cartoon) -> Unit) {
            cartoonTitle.text = cartoon.title
            cartoonCreator.text = cartoon.creator.joinToString(", ")

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)

            Glide.with(itemView.context)
                .load(cartoon.image)
                .apply(requestOptions)
                .into(cartoonImage)

            itemView.setOnClickListener { onItemClick(cartoon) }
        }
    }
}
