package com.michaellaguerre.symphony.ui.utils

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DateUtilsTest(
    private val inputString: String?,
    private val inputPattern: String,
    private val outputPattern: String,
    private val outputString: String?
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> {
            return listOf(
                arrayOf(
                    "2017-12-28T17:01:18.731Z",
                    DateUtils.API_FORMAT,
                    DateUtils.UI_FORMAT,
                    "12/28/2017 @ 17:01"
                ),
                arrayOf(
                    "2017-11-30T12:56:46.139Z",
                    DateUtils.API_FORMAT,
                    DateUtils.UI_FORMAT,
                    "11/30/2017 @ 12:56"
                ),
                arrayOf(
                    null,
                    DateUtils.API_FORMAT,
                    DateUtils.UI_FORMAT,
                    ""
                ),
                arrayOf(
                    "2017-11-30",
                    DateUtils.API_FORMAT,
                    DateUtils.UI_FORMAT,
                    ""
                )
            )
        }
    }

    @Test
    fun `Dates from API are correctly converted to UI`() {
        assertThat(
            DateUtils.getFormattedDateFromString(inputString, inputPattern, outputPattern),
            equalTo(outputString)
        )
    }
}