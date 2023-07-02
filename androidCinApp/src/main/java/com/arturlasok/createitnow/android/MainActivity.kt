package com.arturlasok.createitnow.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arturlasok.createitnow.SingleKtorClient
import com.arturlasok.createitnow.model.KMMAppData
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient: SingleKtorClient by inject()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val simpleDate = SimpleDateFormat("dd/M/yyyy HH:mm:ss", Locale.GERMAN)
                    val currentDate = remember {
                        mutableStateOf(simpleDate.format(Date()))
                    }
                    val appsData =  remember {

                    mutableStateOf(mutableListOf<KMMAppData>()) }


                    LaunchedEffect(key1 = true, block = {
                        var time = ""
                        this.launch {
                            time = ktorClient.getServerTime()
                        }.join()
                        this.launch {

                            appsData.value = ktorClient.getAppsData().toMutableList()

                        }.join()

                        currentDate.value = simpleDate.format(Date(time.toLong()*1000))

                    })
                    Column() {

                             Text(text="> arturlasok.com", style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold, fontSize = 32.sp), modifier= Modifier.padding(start = 20.dp, top = 20.dp))
                            //GreetingView(text = Greeting().greet() + currentDate.value)
                            MyProjectsView(appsData =appsData)

                    }

                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text, textAlign = TextAlign.Center)
    }

}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
