package com.example.todolistforandroid.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistforandroid.domain.model.TodoItem
import com.example.todolistforandroid.domain.usecase.GetTodosUseCase
import com.example.todolistforandroid.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    private val _selectedTodo = MutableStateFlow<TodoItem?>(null)
    val selectedTodo: StateFlow<TodoItem?> = _selectedTodo

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _todos.value = getTodosUseCase()
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            loadTodos()
        }
    }

    fun selectTodo(todo: TodoItem) {
        _selectedTodo.value = todo
    }

    fun clearSelectedTodo() {
        _selectedTodo.value = null
    }
}