package com.elsawy.ahmed.news.data.provider

import android.content.Context

class CategoryProviderImpl(context: Context) : CategoryProvider,PreferenceProvider(context) {
    override fun getPreferredCategoryString(): String {
        val selectedCategory: String? = preferences.getString("CATEGORY", "general")
        return selectedCategory!!
    }
}