package com.arturlasok.createitnow.android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arturlasok.createitnow.model.KMMAppData

@Composable
fun MyProjectsView(appsData: MutableState<MutableList<KMMAppData>>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())) {
        appsData.value.onEach { appsData->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                OneAppData(modifier = Modifier.fillMaxWidth(0.5f).padding(6.dp), appsData = appsData, appId = 1, iconimgUrl = appsData)
                OneAppView(modifier = Modifier.fillMaxWidth(1f), imgUrl =appsData.mPhotoLink )

            }
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}