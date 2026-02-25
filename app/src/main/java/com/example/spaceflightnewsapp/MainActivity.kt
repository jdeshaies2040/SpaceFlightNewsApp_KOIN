package com.example.spaceflightnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
//import coil3.compose.AsyncImage
import com.example.spaceflightnewsapp.model.Result
import com.example.spaceflightnewsapp.ui.theme.SpaceFlightNewsAppTheme
import com.example.spaceflightnewsapp.viewmodel.NewsState
import com.example.spaceflightnewsapp.viewmodel.NewsViewModel
import kotlin.getValue
import androidx.compose.runtime.getValue
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    //private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceFlightNewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsScreen(
                        viewModel = koinViewModel { parametersOf(this@MainActivity) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NewsScreen(viewModel: NewsViewModel, modifier: Modifier = Modifier) {

    val state by viewModel.newsState.observeAsState(NewsState.Loading)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when(state){
            is NewsState.Error -> {}
            NewsState.Loading -> CircularProgressIndicator()
            is NewsState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    items((state as NewsState.Success).news){ news ->
                        NewsItems(news)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItems(news: Result) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = news.image_url,
                contentDescription = news.title,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = news.title
            )

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpaceFlightNewsAppTheme {
        Greeting("Android")
    }
}