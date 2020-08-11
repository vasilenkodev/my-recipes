package io.vasilenko.myrecipes.presentation.creation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.CreateCategoryUseCase
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper
import io.vasilenko.myrecipes.presentation.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryCreationViewModel @Inject constructor(
    private val createUseCase: CreateCategoryUseCase,
    private val mapper: CategoriesModelMapper
) : ViewModel() {

    private val _isCreateButtonEnabled = MutableLiveData<Boolean>()
    val isCreateButtonEnabled: LiveData<Boolean> = _isCreateButtonEnabled

    private var name: String? = ""

    init {
        _isCreateButtonEnabled.value = false
    }

    fun afterNameTextChanged(text: String) {
        name = text
        checkData()
    }

    private fun checkData() {
        _isCreateButtonEnabled.value = isNameValid()
    }

    private fun isNameValid(): Boolean? {
        return name?.isNotBlank()
    }

    fun saveCategory(category: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createUseCase.createCategory(mapper.mapCategoryModelToEntity(category))
        }
    }
}