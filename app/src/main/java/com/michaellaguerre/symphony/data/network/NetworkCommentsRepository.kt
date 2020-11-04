package com.michaellaguerre.symphony.data.network

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.network.services.CommentsService
import com.michaellaguerre.symphony.data.repositories.CommentsRepository
import com.michaellaguerre.symphony.domain.entities.Comment
import javax.inject.Inject

class NetworkCommentsRepository
@Inject constructor(
    private val service: CommentsService,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : CommentsRepository, NetworkRequestHandler(networkAvailabilityChecker) {

    override fun getCommentsForPost(postId: Int): Either<Failure, List<Comment>> {
        return request(
            call = service.getCommentsFromPost(postId),
            transformation = { it.map { commentEntity -> commentEntity.toComment() } },
            defaultResult = emptyList()
        )
    }

}