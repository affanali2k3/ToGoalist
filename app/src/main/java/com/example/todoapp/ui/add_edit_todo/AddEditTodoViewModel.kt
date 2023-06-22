package com.example.todoapp.ui.add_edit_todo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
import com.example.mynewapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var todo by mutableStateOf<Todo?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf<String?>(null)
        private set
    val addTodoDialogOpen: MutableState<Boolean> =
        mutableStateOf(false)
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _uiState = MutableStateFlow(AddEditTodoState())
    val uiState: StateFlow<AddEditTodoState> = _uiState.asStateFlow()

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChange -> setTodoTitle(event.title)
            is AddEditTodoEvent.OnDescriptionChange -> setTodoDescription(event.description)
            is AddEditTodoEvent.OnCategoryChange -> setTodoCategory(event.category)
            is AddEditTodoEvent.OnPointsChange -> setTodoPoints(event.points)
            is AddEditTodoEvent.OnAddCategoryWithPoints -> addCategoryWithPoints()
            is AddEditTodoEvent.OnPriorityChange -> setTodoPriority(event.priority)
            is AddEditTodoEvent.OnDateChange -> setTodoDate(event.date)
            is AddEditTodoEvent.OnTimeChange -> setTodoTime(event.time)
            is AddEditTodoEvent.OnSaveTodo -> saveTodo()
            is AddEditTodoEvent.OnAddTodoClick -> openDialog()
        }
    }

    private fun openDialog(){
        addTodoDialogOpen.value = true
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if (title.isBlank()) {
                sendUiEvent(
                    UiEvent.showSnackbar(
                        message = "Title cannot be empty"
                    )
                )
                return@launch
            }
            repository.insertTodo(
                Todo(
                    title = uiState.value.title,
                    description = uiState.value.description,
                    categoriesWithPoints = uiState.value.categoriesWithPoints,
                    priority = uiState.value.priority,
                    date = uiState.value.date,
                    time = uiState.value.time,
                    isDone = todo?.isDone ?: false,
                    id = todo?.id
                )
            )
            sendUiEvent(UiEvent.PopBackStack)
        }
    }

    private fun setTodoTitle(newTitle: String) {
        _uiState.update {
            it.copy(title = newTitle)
        }

    }

    private fun setTodoPriority(newPriority: String) {
        _uiState.update {
            it.copy(priority = newPriority)
        }
    }

    private fun setTodoDate(newDate: LocalDate) {
        _uiState.update {
            it.copy(date = newDate)
        }
    }

    private fun setTodoTime(newTime: Pair<Int, Int>) {
        _uiState.update {
            it.copy(time = newTime)
        }
    }

    private fun addCategoryWithPoints() {
        _uiState.update {
            it.copy(categoriesWithPoints = it.categoriesWithPoints.plus(_uiState.value.currentCategory to _uiState.value.currentPoints.toInt()))
        }
    }

    private fun setTodoPoints(newPoints: String) {
        _uiState.update {
            it.copy(currentPoints = newPoints)
        }
    }

    private fun setTodoCategory(newCategory: String) {
        _uiState.update {
            it.copy(currentCategory = newCategory)
        }
    }

    private fun setTodoDescription(newDescription: String) {
        _uiState.update {
            it.copy(description = newDescription)
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}