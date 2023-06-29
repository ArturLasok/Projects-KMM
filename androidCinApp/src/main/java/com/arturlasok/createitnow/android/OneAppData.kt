package com.arturlasok.createitnow.android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arturlasok.createitnow.model.KMMAppData

@Composable
fun OneAppData(
    modifier: Modifier,
    appsData: MutableState<MutableList<KMMAppData>>,
    appId: Int,
    iconimgUrl: String
) {
       Column(modifier=modifier) {
           if(appsData.value.size>1) {
           Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {




                   Column(modifier = Modifier.width(32.dp).height(32.dp)) {
                       AsyncImage(
                           model = ImageRequest.Builder(LocalContext.current)
                               .data(iconimgUrl)
                               .crossfade(true)
                               .build(),

                           contentDescription = "icon",
                           contentScale = ContentScale.Fit,
                           //modifier = modifier

                       )
                   }



                   Text(text=appsData.value[appId-1].mAppNamePL, style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold), textDecoration = TextDecoration.Underline, modifier= Modifier.padding(start = 4.dp))


               }

               Text(text=appsData.value[appId-1].mAppDescPL, style = MaterialTheme.typography.h6, textAlign = TextAlign.Justify, modifier= Modifier.padding(start = 4.dp, top = 6.dp))
               Text(text="\nPlatform", style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Justify, modifier= Modifier.padding(start = 4.dp, top = 6.dp))


               val platform = remember { listOf<Pair<String,String>>(Pair(appsData.value[appId-1].mAppStoreLinkAndroid,"Android"), Pair(appsData.value[appId-1].mAppStoreLinkIOS,"iOS"),Pair(appsData.value[appId-1].mAppStoreLinkWeb,"Web")) }
               Row(modifier = Modifier.padding(start = 4.dp)) {
                   platform.onEach { platform->
                       if(platform.first.isNotEmpty()) {
                           Text(text=platform.second, style = MaterialTheme.typography.h6, textAlign = TextAlign.Start, modifier= Modifier.padding(top = 6.dp))
                       }
                   }

               }
               Text(text="\nTech stack", style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Justify, modifier= Modifier.padding(start = 4.dp, top = 6.dp))
               Text(text=appsData.value[appId-1].mTechStack, style = MaterialTheme.typography.h6, textAlign = TextAlign.Justify, modifier= Modifier.padding(start = 4.dp, top = 6.dp))
           }


       }


}