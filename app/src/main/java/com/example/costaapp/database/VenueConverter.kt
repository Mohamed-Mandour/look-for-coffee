package com.example.costaapp.database

import androidx.room.TypeConverter
import com.example.costaapp.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class VenueConverter {

    companion object {
        val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToVenueList(category: String?): List<Category> {
            if (category == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<Category>>() {}.type
            return gson.fromJson(category, listType)
        }

        @TypeConverter
        @JvmStatic
        fun venueListToString(category: List<Category>?): String {
            if (category == null) {
                return gson.toJson(Collections.emptyList<Category>())
            }
            return gson.toJson(category)
        }
    }

}