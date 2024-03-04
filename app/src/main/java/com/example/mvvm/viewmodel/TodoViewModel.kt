package com.example.mvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Todo
import com.example.mvvm.model.TodosApi
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    val todos = mutableStateListOf<Todo>()
    init {
        getTodoList()
    }

    private fun getTodoList(){
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try{
                todosApi = TodosApi!!.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e:Exception){
                Log.d("TODOVIEWMODEL", e.message.toString())
            }
        }
    }
}