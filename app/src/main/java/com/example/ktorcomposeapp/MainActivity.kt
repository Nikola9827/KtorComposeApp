package com.example.ktorcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktorcomposeapp.ui.theme.KtorComposeAppTheme
import com.example.ktorcomposeapp.viewmodel.HelloViewModel
import com.example.ktorcomposeapp.viewmodel.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val helloViewModel : HelloViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorComposeAppTheme {
                HelloScreen(helloViewModel)
            }
        }
    }
}

@Composable
fun HelloScreen (viewModel: HelloViewModel){
    val uiState by viewModel.uiState.collectAsState()

Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
) {

    when (uiState) {
        is UiState.Error -> MyText("Error: ${(uiState as UiState.Error).errorMessage}")
        UiState.Idle -> MyText("Press the button to get message")
        UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> MyText((uiState as UiState.Success).message)
    }

    Button(onClick = { viewModel.fetchHelloMessage() }) {
        Text( text = "Fetch Message")
    }
}
}

@Composable
fun MyText (text: String){
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = 24.dp)
    )
}