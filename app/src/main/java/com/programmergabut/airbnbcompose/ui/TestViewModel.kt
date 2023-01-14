package com.programmergabut.airbnbcompose.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.airbnbcompose.repository.TestRepository
import com.programmergabut.airbnbcompose.domain.model.Post
import com.programmergabut.airbnbcompose.util.ResponseResource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: TestRepository,
): ViewModel() {
    private val _postResponse = mutableStateOf(Post())
    val postResponse: State<Post> = _postResponse

    fun getFriendList() {
        _postResponse.value = Post(isLoading = true)
        viewModelScope.launch {
            repository.getPosts().onEach {
                when (it) {
                    is ResponseResource.Error ->
                        _postResponse.value = it.errorMessage
                    is ResponseResource.Success ->
                        _postResponse.value = it.data
                }
            }.launchIn(viewModelScope)
        }
    }
}