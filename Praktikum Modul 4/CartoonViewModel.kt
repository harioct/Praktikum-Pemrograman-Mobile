package com.example.cartoons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartoonViewModel : ViewModel() {

    private val _cartoons = MutableLiveData<List<Cartoon>>()
    val cartoons: LiveData<List<Cartoon>> get() = _cartoons

    init {
        fetchCartoons()
    }

    private fun fetchCartoons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cartoonList = RetrofitInstance.api.getCartoons()
                _cartoons.postValue(cartoonList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filterCartoons(query: String?) {
        if (query.isNullOrEmpty()) {
            fetchCartoons()
        } else {
            val filteredList = _cartoons.value?.filter {
                it.title?.contains(query, ignoreCase = true) == true ||
                        it.genre.any { genre -> genre.contains(query, ignoreCase = true) }
            }
            _cartoons.postValue(filteredList)
        }
    }
}
