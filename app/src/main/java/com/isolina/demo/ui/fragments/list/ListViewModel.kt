package com.isolina.demo.ui.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isolina.demo.domain.base.Output
import com.isolina.demo.domain.models.Beer
import com.isolina.demo.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    private val _items = MutableLiveData<List<Beer>>()
    val items: LiveData<List<Beer>> = _items

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun beers() {
        viewModelScope.launch {
            _progress.value = true
            val res = useCase.executeBeers()
            if (res.status == Output.Status.SUCCESS) {
                _progress.value = false
                res.data?.let {
                    _items.value = it
                }
            } else {
                _progress.value = false
                _error.value = res.message ?: "Error"
            }
        }
    }

    fun searchBeers(beerName: String) {
        viewModelScope.launch {
            _progress.value = true
            val res = useCase.executeSearchBeers(removeEmpty(beerName))
            if (res.status == Output.Status.SUCCESS) {
                _progress.value = false
                res.data?.let {
                    _items.value = it
                }
            } else {
                _progress.value = false
                _error.value = res.message ?: "Error"
            }
        }
    }

    private fun removeEmpty(text: String) = text.replace(" ", "_")
}