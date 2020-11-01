package com.michaellaguerre.symphony.core.interactors

import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {


    /**
     * A suspend fun that is in charge of executing the use case scenario, by using the given params
     * as inputs and by returning an [Either] with a failure if something wrong happened, or the
     * result of the processing.
     *
     * @param params the parameters used as input
     * @return an [Either] with a failure if something wrong happened, or the
     * result of the processing
     */
    abstract suspend fun run(params: Params): Either<Failure, Type>


    /**
     * Operator override in order to simplify the use of the UseCase class.
     *
     * The job will be run in an IO thread via Coroutines, and the result will be posted to the
     * UI thread
     *
     * @param params the parameters that will be used as input for this use case
     * @param onResult a listener that will process the result of the use case processing
     */
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        // Run the job on an IO thread
        val job = GlobalScope.async(Dispatchers.IO) {
            run(params)
        }

        // Post the result on the main thread
        GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
    }


    /**
     * Class used to represent a "Void" return type or parameters
     */
    class None
}
