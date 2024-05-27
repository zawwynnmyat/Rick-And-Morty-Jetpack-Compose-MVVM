package com.zawmyat.rickandmortyuniverse.view.info_page

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.models.Constants
import com.zawmyat.rickandmortyuniverse.models.utils.UrlManager

@Composable
fun InfoPage(navController: NavController) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            InfoAppBar(
                onShareClick = {

                    val text = "Rick and Morty Universe App is great.\n\n" +
                            "You can get it from Google PlayStore.\n\n" +
                            "https://play.google.com/store/apps/details?id=${Constants.appPackageName}"

                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, text)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)

                    startActivity(context, shareIntent, null)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.scaffold_color))
                .verticalScroll(scrollState),
        ) {
            Box(modifier = Modifier.padding(all = 10.dp)) {
                Column {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Green
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.api_name),
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.7f),
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        text = stringResource(id = R.string.tech_name),
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.7f),
                        fontStyle = FontStyle.Italic
                    )


                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(id = R.string.app_version),
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.app_decription),
                        fontSize = 15.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        textAlign = TextAlign.Justify
                    )


                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Developer Contacts",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    //API Source & Play Store
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        //Api Source
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.morty,
                                contentDescription = "Morty",
                                modifier = Modifier
                                    .width(20.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "API Source",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.navigateToWebsite(siteUrl = Constants.apiUrl)
                                })
                            )
                        }

                        //Play Store
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.gplaystore,
                                contentDescription = "Play Store",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Play Store",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.openPlayStore(packageName = Constants.appPackageName)
                                })
                            )
                        }


                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //Github and Facebook Account
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        //GitHub
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.github,
                                contentDescription = "Github",
                                modifier = Modifier
                                    .width(20.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "GitHub",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.navigateToWebsite(siteUrl = Constants.githubProfile)
                                })
                            )

                        }

                        //Play Store
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.facebook,
                                contentDescription = "Facebook",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Zaw Myat",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.openFacebookPage(id = Constants.accountId)
                                })
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //YouTube & Facebook Page
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        //YouTube
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.youtube,
                                contentDescription = "YouTube",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "YouTube",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.openYouTubeChannel(channelId = Constants.youtubeChannelId)
                                })
                            )

                        }

                        //Play Store
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.facebook,
                                contentDescription = "facebook",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Hexa Dev",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.openFacebookPage(id = Constants.pageId)
                                })
                            )
                        }

                    }


                    Spacer(modifier = Modifier.height(18.dp))

                    //Phone Call & SMS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        //Phone Call
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.call,
                                contentDescription = "call",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = Constants.phoneNumber,
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.makePhoneCall(phoneNumber = Constants.phoneNumber)
                                })
                            )

                        }


                        //SMS
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.sms,
                                contentDescription = "sms",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = Constants.phoneNumber,
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.sendSms(phoneNumber = Constants.phoneNumber)
                                })
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //Telegram
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        //Telegram
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.telegramc,
                                contentDescription = "telegram",
                                modifier = Modifier
                                    .width(20.dp)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "@${Constants.telegramUserName}",
                                color = Color.Green.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable(onClick = {
                                    UrlManager.openTelegram(userName = Constants.telegramUserName)
                                })
                            )
                        }


                        //Viber
                        Row(
                            modifier = Modifier.width(screenWidth.div(2)),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                        }

                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //Gmail
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        AsyncImage(
                            model = R.drawable.gmail,
                            contentDescription = "gmail",
                            modifier = Modifier
                                .width(20.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "zawwinmyat.dev@gmail.com",
                            color = Color.Green.copy(alpha = 0.8f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable(onClick = {
                                UrlManager.openGmail()
                            })
                        )

                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Privacy Policy",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.White.copy(alpha = 0.7f))) {
                                append("The privacy policy of this app can be read fully ")
                            }

                            withStyle(SpanStyle(color = Color.Green.copy(alpha = 0.8f))) {
                                append("here")
                            }

                            withStyle(SpanStyle(color = Color.White.copy(alpha = 0.7f))) {
                                append(".")
                            }
                        },
                        modifier = Modifier.clickable(onClick = {
                            UrlManager.navigateToWebsite(siteUrl = Constants.privacyPolicyUrl)
                        })
                    )

                }

            }
        }
    }

}

