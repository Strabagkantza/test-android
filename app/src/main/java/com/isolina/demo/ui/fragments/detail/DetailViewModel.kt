package com.isolina.demo.ui.fragments.detail

import androidx.lifecycle.ViewModel
import com.isolina.demo.domain.models.Beer
import com.isolina.demo.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    private lateinit var beer: Beer

    fun setBeer(beer: Beer) {
        this.beer = beer
    }

    fun getBeer(): Beer = beer
}