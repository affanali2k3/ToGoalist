package com.example.todoapp.ui.todo_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoItemViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val goalsRepository: UserGoalsRepository
) : ViewModel() {
    private val goals = goalsRepository.getUserGoals()
    fun onEvent(event: TodoItemEvent) = when (event) {
        is TodoItemEvent.OnMarkTodoDone -> markTodoDone(event.todo, event.goalsToUpdate)
        is TodoItemEvent.OnDeleteTodo -> deleteTodo(event.todo)
    }

    private fun markTodoDone(todo: Todo, goalsToUpdate: Map<SingleGoal, Int>) {
        val updatedGoals: MutableList<SingleGoal> = mutableListOf()
        goalsToUpdate.forEach { (goal, pointsToAdd) ->
            updatedGoals.add(goal.copy(currPoints = goal.currPoints + pointsToAdd))
        }

        viewModelScope.launch {
            try {
                goalsRepository.incrementGoalPoints(updatedGoals)
            } catch (e: Exception) {
                println("Error ${e.message}")
            }
        }
        viewModelScope.launch { todoRepository.deleteTodo(todo) }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch { todoRepository.deleteTodo(todo) }
    }

    private suspend fun collectGoals(
        oldGoalsFlow: Flow<List<SingleGoal>>,
        updatedGoals: MutableList<SingleGoal>
    ) {
        viewModelScope.launch {
            oldGoalsFlow.collect { goalsList ->
                for (goal in goalsList) {
                    if (goal in updatedGoals) {
                        val matchedObject = updatedGoals.find { goal.id == it.id }
                        goalsRepository.insertUserGoal(goal.copy(currPoints = matchedObject!!.currPoints))
                    }
                }
            }
        }
    }

}