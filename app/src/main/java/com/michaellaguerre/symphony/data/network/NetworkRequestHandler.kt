package com.michaellaguerre.symphony.data.network

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import retrofit2.Call
import javax.inject.Inject

open class NetworkRequestHandler
@Inject constructor(private val networkAvailabilityChecker: NetworkAvailabilityChecker) {

    fun <Type, Result> request(
        call: Call<Type>,
        transformation: (Type) -> Result,
        defaultResult: Type
    ): Either<Failure, Result> {

        // No network
        if (!networkAvailabilityChecker.isNetworkAvailable()) {
            return Either.Left(Failure.NetworkConnection)
        }

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                return Either.Right(transformation((response.body() ?: defaultResult)))
            } else {
                return Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            return Either.Left(Failure.ServerError)
        }
    }
}