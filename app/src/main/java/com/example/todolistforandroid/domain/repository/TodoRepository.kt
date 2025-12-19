package com.example.todolistforandroid.domain.repository

import com.example.todolistforandroid.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}