package com.michaellaguerre.symphony.core.utils

/**
 * Base Class used for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    /**
     * Class representing a network connection failure (network unreachable)
     */
    object NetworkConnection : Failure()


    /**
     * Class representing a server error (4XX or 5XX errors)
     */
    object ServerError : Failure()


    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure : Failure()
}
