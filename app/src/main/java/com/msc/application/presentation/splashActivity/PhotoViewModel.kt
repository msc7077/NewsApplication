package com.msc.application.presentation.splashActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msc.application.data.remote.UnsplashApi
import com.msc.application.data.remote.UnsplashPhoto
import com.msc.application.util.Constants.UNSPLASH_ACCESS_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val unsplashApi: UnsplashApi
): ViewModel() {

//    private val _photo = MutableStateFlow<UnsplashPhoto?>(null)
//    val photo: StateFlow<UnsplashPhoto?> = _photo

    private val _photos = MutableStateFlow<List<UnsplashPhoto>>(emptyList())
    val photos: StateFlow<List<UnsplashPhoto>> = _photos

    private var currentPage = 1 // 초기 페이지는 1페이지

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newPhotos = unsplashApi.getPhotos(UNSPLASH_ACCESS_KEY, currentPage, 3)
                _photos.value = _photos.value + newPhotos
                currentPage++
            }catch (e: Exception) {
                Log.d("TAG", "fetchPhotos Exception: ${e.printStackTrace()}")
            }finally {
                _isLoading.value = false
            }
        }
    }

}