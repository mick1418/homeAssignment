package com.michaellaguerre.homeassignment.utilities

import android.app.Activity
import android.widget.Toolbar
import com.michaellaguerre.homeassignment.data.entities.*


//**********************************************************************************************
// AUTHORS
//**********************************************************************************************

val authorA = AuthorEntity(
    1,
    "Author A",
    "author_a",
    "author.a@example.com",
    "https://i.pravatar.cc/150?img=1",
    AddressEntity("1", "1")
)
val authorB = AuthorEntity(
    2,
    "Author B",
    "author_b",
    "author.b@example.com",
    "https://i.pravatar.cc/150?img=2",
    AddressEntity("2", "2")
)
val authorC = AuthorEntity(
    3,
    "Author C",
    "author_c",
    "author.c@example.com",
    "https://i.pravatar.cc/150?img=3",
    AddressEntity("3", "3")
)
val authorD = AuthorEntity(
    4,
    "Author D",
    "author_d",
    "author.d@example.com",
    "https://i.pravatar.cc/150?img=4",
    AddressEntity("4", "4")
)

val authorE = AuthorEntity(
    5,
    "Author E",
    "author_E",
    "author.e@example.com",
    "https://i.pravatar.cc/150?img=5",
    AddressEntity("5", "5")
)

val authorF = AuthorEntity(
    6,
    "Author F",
    "author_F",
    "author.f@example.com",
    "https://i.pravatar.cc/150?img=6",
    AddressEntity("6", "6")
)

val testAuthorsNotSorted = arrayListOf(
    authorB, authorA, authorD, authorC
)


//**********************************************************************************************
// POSTS
//**********************************************************************************************

val post1author1 = PostEntity(
    1,
    "2017-12-20T00:00:00.000Z",
    "post1author1 title",
    "post1author1 body",
    "https://i.pravatar.cc/150?img=1",
    1
)

val post2author1 = PostEntity(
    2,
    "2017-12-21T00:00:00.000Z",
    "post2author1 title",
    "post2author1 body",
    "https://i.pravatar.cc/150?img=2",
    1
)

val post3author1 = PostEntity(
    3,
    "2017-12-22T00:00:00.000Z",
    "post3author1 title",
    "post3author1 body",
    "https://i.pravatar.cc/150?img=3",
    1
)

val post1author2 = PostEntity(
    4,
    "2017-12-23T00:00:00.000Z",
    "post1author2 title",
    "post1author2 body",
    "https://i.pravatar.cc/150?img=4",
    2
)

val post2author2 = PostEntity(
    5,
    "2017-12-24T00:00:00.000Z",
    "post2author2 title",
    "post2author2 body",
    "https://i.pravatar.cc/150?img=5",
    2
)

val post3author2 = PostEntity(
    6,
    "2017-12-25T00:00:00.000Z",
    "post3author2 title",
    "post3author2 body",
    "https://i.pravatar.cc/150?img=6",
    2
)

val testPostsAuthor1NotSorted = arrayListOf(
    post3author1, post1author1, post2author1
)

val testPostsAuthor2 = arrayListOf(post1author2, post2author2, post3author2)


//**********************************************************************************************
// COMMENTS
//**********************************************************************************************


val comment1post1 = CommentEntity(
    1,
    "2017-12-20T00:00:00.000Z",
    "comment1post1 body",
    "usernameA",
    "email_a@example.com",
    "https://i.pravatar.cc/150?img=1",
    1
)

val comment2post1 = CommentEntity(
    2,
    "2017-12-21T00:00:00.000Z",
    "comment2post1 body",
    "usernameB",
    "email_b@example.com",
    "https://i.pravatar.cc/150?img=2",
    1
)

val comment3post1 = CommentEntity(
    3,
    "2017-12-22T00:00:00.000Z",
    "comment3post1 body",
    "usernameC",
    "email_c@example.com",
    "https://i.pravatar.cc/150?img=3",
    1
)

val comment1post2 = CommentEntity(
    4,
    "2017-12-23T00:00:00.000Z",
    "comment1post2 body",
    "usernameD",
    "email_a@example.com",
    "https://i.pravatar.cc/150?img=4",
    2
)

val comment2post2 = CommentEntity(
    5,
    "2017-12-24T00:00:00.000Z",
    "comment2post2 body",
    "usernameE",
    "email_e@example.com",
    "https://i.pravatar.cc/150?img=5",
    2
)

val comment3post2 = CommentEntity(
    6,
    "2017-12-25T00:00:00.000Z",
    "comment3post2 body",
    "usernameF",
    "email_f@example.com",
    "https://i.pravatar.cc/150?img=6",
    2
)

val testCommentsPost1NotSorted = arrayListOf(
    comment2post1, comment1post1, comment3post1
)

val testCommentsPost2 = arrayListOf(comment1post2, comment2post2, comment3post2)


//**********************************************************************************************
// REMOTE KEYS
//**********************************************************************************************


val remoteKey1 = RemoteKey("label1")
val remoteKey2 = RemoteKey("label2")
val remoteKey3 = RemoteKey("label3")

val testRemoteKeys = arrayListOf(
    remoteKey1, remoteKey2, remoteKey3
)

/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
    activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String

