package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import retrofit2.Call

/**
 * Base class used as the Repositories base class.
 * It will check if the network is available and create Failure if it isn't.
 */
abstract class BaseRepository
constructor(private val networkAvailabilityChecker: NetworkAvailabilityChecker) {


    /**
     * Executes a Retrofit call and wraps its content (transformed if needed) in a Either.Right.
     * If something wrong happened, it creates a Either.Left failure instead.
     *
     * @param call the Retrofit call we want to execute
     * @param transformation a lambda that can will be used mainly to transform the data from the data entities to domain entities
     * @param defaultResult the default result return in case of a successful call with no data returned
     * @return an [Either] with either a [Failure] if something wrong happened, or the transformed data
     */
    fun <Type, Result> request(
        call: Call<Type>,
        transformation: (Type) -> Result,
        defaultResult: Type
    ): Either<Failure, Result> {

        // No network available -> return a NetworkConnection failure
        if (!networkAvailabilityChecker.isNetworkAvailable()) {
            return Either.Left(Failure.NetworkConnection)
        }

        try {
            val response = call.execute()

            // The network request was successful -> return the transformed data
            if (response.isSuccessful) {
                return Either.Right(transformation((response.body() ?: defaultResult)))
            }
            // There was an issue with the network request -> return a ServerError failure
            else {
                return Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            // There was an exception -> return a ServerError failure
            return Either.Left(Failure.ServerError)
        }
    }
}