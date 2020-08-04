package io.vasilenko.myrecipes.presentation.catalog.adapter

import io.vasilenko.myrecipes.presentation.common.ListItem

data class CatalogRecipeItem(
    val id: Long,
    val title: String,
    val image: String
) : ListItem {
    override val itemId: Long = id
}