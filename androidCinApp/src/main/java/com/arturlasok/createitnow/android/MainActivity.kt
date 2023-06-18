package com.arturlasok.createitnow.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.arturlasok.createitnow.Greeting
import com.arturlasok.createitnow.SingleKtorClient
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


                    LaunchedEffect(key1 = true, block = {
                        var time = ""
                        this.launch {
                            time = ktorClient.getServerTime()
                        }.join()
                        currentDate.value = simpleDate.format(Date(time.toLong()*1000))
                    })

                    GreetingView(Greeting().greet() + currentDate.value)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
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
