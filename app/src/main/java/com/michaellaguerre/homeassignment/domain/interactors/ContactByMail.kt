package com.michaellaguerre.homeassignment.domain.interactors

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import javax.inject.Inject

/**
 * Contact by Mail use case.
 *
 * This use case allows the user to send a mail to a given [Author].
 */
class ContactByMail
@Inject constructor() {

    fun invoke(params: Params) {

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf(params.emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, "")
        }

        val context = params.activityContext

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Log.d("ContactByMail", "No activity available to handle action")
        }
    }

    data class Params(val activityContext: Context, val emailAddress: String)
}