package cz.maderajan.mml.database.converters

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun fromListString(input: List<String>): String =
        input.joinToString(";")

    @TypeConverter
    fun toListString(input: String): List<String> =
        input.split(";").toList()
}