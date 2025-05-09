package com.droidos.network.di.errorHandler.entities

import org.json.JSONException
import org.json.JSONObject

sealed class ErrorEntity {
    data object Network : ErrorEntity()
    data object NotFound : ErrorEntity()
    data object AccessDenied : ErrorEntity()
    data object Unauthorized : ErrorEntity()
    data object BadRequest : ErrorEntity()
    data object ServiceUnavailable : ErrorEntity()
    data object Timeout : ErrorEntity()
    data object GatewayError : ErrorEntity()
    data object ServerInternalError : ErrorEntity()
    data class Unknown(val message: String? = null) : ErrorEntity()
}

fun Throwable.asErrorEntity(): ErrorEntity =
    (this as? ApiException)?.error ?: ErrorEntity.Unknown(this.message)

fun extractionErrorMessage(error: String): String {
    return try {
        val errorJson = JSONObject(error)
        val message = errorJson.optString("message", "Unknown error")
        message
    } catch (e: JSONException) {
        "Invalid error format"
    }
}


