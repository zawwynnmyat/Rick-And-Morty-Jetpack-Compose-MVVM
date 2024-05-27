package com.zawmyat.rickandmortyuniverse.models.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import com.zawmyat.rickandmortyuniverse.models.AppContext
import com.zawmyat.rickandmortyuniverse.models.Constants

object UrlManager {

    fun openFacebookPage(id: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("${Constants.facebookBaseUrl}$id")
        )
        AppContext.applicationContext.startActivity(intent)
    }

    fun navigateToWebsite(siteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(siteUrl)
        )
        AppContext.applicationContext.startActivity(intent)
    }

    fun openYouTubeChannel(channelId: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("${Constants.youtubeBaseUrl}$channelId")
        )
        AppContext.applicationContext.startActivity(intent)
    }

    fun openPlayStore(packageName: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("${Constants.playStoreBaseUrl}${Constants.appPackageName}")
        )

        val context : Context = AppContext.applicationContext

        // Check if Google Play Store is available
        val packageManager: PackageManager = context.packageManager
        val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        val resolveInfo = packageManager.resolveActivity(playStoreIntent, PackageManager.MATCH_DEFAULT_ONLY)

        if (resolveInfo != null) {
            // Google Play Store is available
            intent.setPackage(Constants.playStorePakageName)
        }
        context.startActivity(intent)
    }


    fun openGmail() {

        val context : Context = AppContext.applicationContext

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, Constants.mailSubject)
            putExtra(Intent.EXTRA_TEXT, Constants.mailBody)
        }

        // Check if Gmail app is available
        val packageManager: PackageManager = context.packageManager
        val resolveInfo = packageManager.resolveActivity(emailIntent, PackageManager.MATCH_DEFAULT_ONLY)

        if (resolveInfo != null && resolveInfo.activityInfo.packageName.contains(Constants.gmailPackageName)) {
            // Gmail app is available
            emailIntent.setPackage("com.google.android.gm")
        } else {
            // Gmail app is not available, fallback to generic email app
            val genericEmailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_SUBJECT, Constants.mailSubject)
                putExtra(Intent.EXTRA_TEXT, Constants.mailBody)
            }
            if (genericEmailIntent.resolveActivity(packageManager) != null) {
                context.startActivity(genericEmailIntent)
            } else {
                Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
                return
            }
        }

        context.startActivity(emailIntent)

    }

    fun openTelegram(userName: String) {
        val context : Context = AppContext.applicationContext

        val telegramUri = Uri.parse("tg://resolve?domain=$userName")
        val intent = Intent(Intent.ACTION_VIEW, telegramUri)
        val packageManager = context.packageManager
        val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        if (resolveInfo != null) {
            // Telegram is available
            context.startActivity(intent)
        } else {
            // Telegram is not available, show a toast message
            Toast.makeText(context, "Telegram is not installed on your device", Toast.LENGTH_LONG).show()
        }
    }


    fun makePhoneCall(phoneNumber: String) {
        val context : Context = AppContext.applicationContext

        val phoneUri = Uri.parse("tel:$phoneNumber")
        val intent = Intent(Intent.ACTION_DIAL, phoneUri)
        context.startActivity(intent)
    }


    fun sendSms(phoneNumber: String) {
        val context : Context = AppContext.applicationContext

        val smsUri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, smsUri).apply {
            putExtra("sms_body", Constants.smsInitialMessage)
        }
        context.startActivity(intent)
    }

    fun forwardToViber(message: String) {

        val context : Context = AppContext.applicationContext

        val viberUri = Uri.parse("viber://forward?text=$message")
        val intent = Intent(Intent.ACTION_VIEW, viberUri)
        val packageManager = context.packageManager
        val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        if (resolveInfo != null) {
            // Viber is available
            context.startActivity(intent)
        } else {
            // Viber is not available, show a toast message
            Toast.makeText(context, "Viber is not installed on your device", Toast.LENGTH_LONG).show()
        }
    }




}