package com.droidos.network.di.errorHandler.entities

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun invoke(throwable: Throwable, message: String?): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.Network

            is IllegalStateException -> ErrorEntity.Unknown(message)

            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val parsedMessage = errorBody?.let { extractionErrorMessage(it) }

                when (throwable.code()) {

                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound

                    HttpURLConnection.HTTP_BAD_REQUEST -> ErrorEntity.BadRequest

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied

                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable

                    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ErrorEntity.Timeout

                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.Unauthorized

                    HttpURLConnection.HTTP_BAD_GATEWAY -> ErrorEntity.GatewayError

                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> ErrorEntity.Timeout

                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.ServerInternalError

                    else -> ErrorEntity.Unknown(parsedMessage)
                }
            }

            else -> ErrorEntity.Unknown(message)
        }
    }
}