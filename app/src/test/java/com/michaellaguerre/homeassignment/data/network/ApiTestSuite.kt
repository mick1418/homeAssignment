package com.michaellaguerre.homeassignment.data.network

import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    AuthorsApiTest::class,
    PostsApiTest::class,
    CommentsApiTest::class
)
class ApiTestSuite