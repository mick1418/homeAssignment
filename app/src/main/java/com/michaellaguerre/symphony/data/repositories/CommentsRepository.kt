package com.michaellaguerre.symphony.data.repositories

import com.michaellaguerre.symphony.core.platform.NetworkAvailabilityChecker
import com.michaellaguerre.symphony.core.utils.Either
import com.michaellaguerre.symphony.core.utils.Failure
import com.michaellaguerre.symphony.data.database.daos.CommentDao
import com.michaellaguerre.symphony.data.network.services.CommentsService
import com.michaellaguerre.symphony.domain.entities.Comment
import javax.inject.Inject

class CommentsRepository
@Inject constructor(
    private val service: CommentsService,
    private val dao: CommentDao,
    networkAvailabilityChecker: NetworkAvailabilityChecker
) : BaseRepository(networkAvailabilityChecker) {

    fun getCommentsForPost(postId: Int): Either<Failure, List<Comment>> {
        return request(
            call = service.getCommentsFromPost(postId),
            transformation = {

                // Insert into database
                dao.insertAll(it)

                // Convert to domain entity
                it.map { commentEntity -> commentEntity.toComment() }
            },
            defaultResult = emptyList()
        )
    }

}