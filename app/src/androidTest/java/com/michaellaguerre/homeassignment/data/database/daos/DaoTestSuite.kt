package com.michaellaguerre.homeassignment.data.database.daos

import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    AuthorsDaoTest::class,
    PostsDaoTest::class,
    CommentsDaoTest::class,
    RemoteKeyDaoTest::class
)
class DaoTestSuite