package com.example.skiflaretask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.skiflaretask.data.Users
import com.example.skiflaretask.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(val repository: UserRepository) : ViewModel() {
    private val _userResponse = MutableLiveData<List<Users>>()
    val userResponse: LiveData<List<Users>>
        get() = _userResponse

     fun getUser(){
        viewModelScope.launch {
             repository.getUsers().collect{
                 _userResponse.postValue(it)
             }
        }
    }
}

class UserViewModelFactory(val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}