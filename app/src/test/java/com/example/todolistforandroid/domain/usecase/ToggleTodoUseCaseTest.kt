package com.example.todolistforandroid.domain.usecase


import com.example.todolistforandroid.domain.repository.TodoRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleTodoUseCaseTest {

    private lateinit var repository: TodoRepository
    private lateinit var toggleTodoUseCase: ToggleTodoUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        toggleTodoUseCase = ToggleTodoUseCase(repository)
    }

    @Test
    fun `when toggleTodo called should call repository toggleTodo with correct id`() = runTest {
        val testId = 1

        toggleTodoUseCase(testId)

        coVerify { repository.toggleTodo(testId) }
    }
}