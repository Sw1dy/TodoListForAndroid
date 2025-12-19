package com.example.todolistforandroid.domain.usecase

import com.example.todolistforandroid.domain.repository.TodoRepository

class GetTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<com.example.todolistforandroid.domain.model.TodoItem> = repository.getTodos()
}