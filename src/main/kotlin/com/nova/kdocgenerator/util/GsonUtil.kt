package com.nova.kdocgenerator.util

import com.google.gson.Gson

object GsonUtil {
    private val gson = Gson()

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }

    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }
}
