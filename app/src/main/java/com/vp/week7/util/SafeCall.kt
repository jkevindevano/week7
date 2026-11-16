package com.vp.week7.util

import retrofit2.HttpException
import java.io.IOException

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String, val code: Int? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: HttpException) {
        Resource.Error(message = "HTTP ${e.code()}", code = e.code())
    } catch (e: IOException) {
        Resource.Error(message = "Koneksi internet bermasalah")
    } catch (e: Exception) {
        Resource.Error(message = e.localizedMessage ?: "Terjadi kesalahan yang tidak diketahui")
    }
}
