package dev.hossain.android.catalog.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Type converters to allow Room to reference complex data types.
 */
class DataConverters {
    private val gson = Gson()
    private val stringListType: Type = object : TypeToken<List<String>>() {}.type
    private val intListType: Type = object : TypeToken<List<Int>>() {}.type

    @TypeConverter
    fun listOfStringToJsonString(stringItems: List<String>): String {
        return gson.toJson(stringItems, stringListType)
    }

    @TypeConverter
    fun stringListJsonToStringList(itemListJson: String): List<String> {
        val listType: Type = object : TypeToken<List<String>>() {}.type

        val items: List<String> = gson.fromJson(itemListJson, listType)
        return items
    }

    @TypeConverter
    fun listOfIntToJsonInt(intItems: List<Int>): String {
        return gson.toJson(intItems, intListType)
    }

    @TypeConverter
    fun intListJsonToIntList(itemListJson: String): List<Int> {
        val listType: Type = object : TypeToken<List<Int>>() {}.type

        val items: List<Int> = gson.fromJson(itemListJson, listType)
        return items
    }
}
