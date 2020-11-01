package com.michaellaguerre.symphony.core.utils

/**
 * Class representing a value of one of two possible types (a disjoint union).
 * It is the equivalent of the Either Monad from Functional Programming.
 *
 * Instances of [Either] are either an instance of [Left] or [Right].
 * Functional programming convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out LeftType, out RightType> {


    //**********************************************************************************************
    // CLASSES
    //**********************************************************************************************

    /**
     * Class representing the left side of [Either] class which by convention is a "Failure".
     */
    data class Left<out LeftType>(val leftValue: LeftType) : Either<LeftType, Nothing>()

    /**
     * Class representing the right side of [Either] class which by convention is a "Success".
     */
    data class Right<out RightType>(val rightValue: RightType) : Either<Nothing, RightType>()


    //**********************************************************************************************
    // PROPERTIES
    //**********************************************************************************************

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<RightType>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<LeftType>


    //**********************************************************************************************
    // FUNCTIONS
    //**********************************************************************************************

    /**
     * Creates a Left type.
     * @see Left
     */
    fun <LeftType> left(leftValue: LeftType) = Left(leftValue)


    /**
     * Creates a Left type.
     * @see Right
     */
    fun <RightType> right(rightValue: RightType) = Right(rightValue)

    /**
     * Applies functionLeft if this is a Left or functionRight if this is a Right.
     * @see Left
     * @see Right
     */
    fun fold(functionLeft: (LeftType) -> Any, functionRight: (RightType) -> Any): Any =
        when (this) {
            is Left -> functionLeft(leftValue)
            is Right -> functionRight(rightValue)
        }
}

//**********************************************************************************************
// UTILITIES
//**********************************************************************************************

/**
 * Composes 2 functions
 * See <a href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</a>
 */
fun <A, B, C> ((A) -> B).c(function: (B) -> C): (A) -> C = {
    function(this(it))
}

/**
 * Right-biased flatMap() functional programming convention, which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.flatMap(function: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(leftValue) // Return the left value unchanged
        is Either.Right -> function(rightValue) // Transform the right value
    }

/**
 * Right-biased map() functional programming convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.map(function: (R) -> (T)): Either<L, T> =
    this.flatMap(function.c(::right))

/** Returns the value from this `Right` or the given argument if this is a `Left`.
 *  Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
fun <LeftType, RightType> Either<LeftType, RightType>.getOrElse(value: RightType): RightType =
    when (this) {
        is Either.Left -> value
        is Either.Right -> rightValue
    }

/**
 * Left-biased onFailure() functional programming convention dictates that when this class is Left, it'll perform
 * the onFailure functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
fun <LeftType, RightType> Either<LeftType, RightType>.onFailure(function: (failure: LeftType) -> Unit): Either<LeftType, RightType> =
    this.apply { if (this is Either.Left) function(leftValue) }

/**
 * Right-biased onSuccess() functional programming convention dictates that when this class is Right, it'll perform
 * the onSuccess functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
fun <LeftType, RightType> Either<LeftType, RightType>.onSuccess(function: (success: RightType) -> Unit): Either<LeftType, RightType> =
    this.apply { if (this is Either.Right) function(rightValue) }
