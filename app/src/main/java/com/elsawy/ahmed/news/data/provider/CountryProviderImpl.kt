package com.elsawy.ahmed.news.data.provider

import android.content.Context

class CountryProviderImpl(context: Context) : CountryProvider,PreferenceProvider(context) {

    override fun getPreferredCountryString(): String {
        val selectedCountry: String? = preferences.getString("COUNTRY", "us")
        return selectedCountry!!
    }
}