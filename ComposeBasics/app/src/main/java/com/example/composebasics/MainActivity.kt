package com.example.composebasics

import android.adservices.adselection.UpdateAdCounterHistogramRequest
import android.content.ClipData.Item
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
//import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.ui.theme.ComposeBasicsTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                //Greeting("World S")
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Counter()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically),
        modifier = Modifier
            //.size(400.dp)
            .border(3.dp, Color.Black)
            .fillMaxSize()

    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
        )
        StyledText1("Hello $name")
        Image(painter = painterResource(id = R.drawable.chatgpt_logo_transparent),
            contentDescription = "ChatGPT Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(5.dp)
                //.border(2.dp, Color.Gray, RoundedCornerShape(50.dp))
        )
        StyledText2("Hello Android")
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp,Alignment.CenterVertically),
            modifier = Modifier
                //.height(400.dp)
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            item {
                StyledText2("Beginning.")
            }
            items(25){ j ->
                LazyRow {
                    items(10){ i ->
                        Icon(imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(255-j*4, 215-i*8, 0),
                            modifier = Modifier
                                .size(60.dp)
                            //.background(Color(0,128,128))
                        )
                    }
                }

            }
            item {
                StyledText1("End.")
            }
        }
    }

}

class MyViewModel : ViewModel() {
    private val _count = mutableStateOf(0)
    val count: State<Int> get() = _count

    fun increment () {
        _count.value++
    }

    fun decrement () {
        _count.value--
    }
}
@Preview
@Composable
fun Counter(counterViewModel: MyViewModel = viewModel()) {
    val count = counterViewModel.count.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(5.dp)
            .wrapContentSize()
    ) {
        StyledText1("Value: $count")
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally)
        ) {
            //StyledButton(onClick = {counterViewModel.increment()}, "+")
            HoldableButton(onClick = {counterViewModel.increment()},
                onHold = {counterViewModel.increment()},
                modifier = Modifier.size(width = 150.dp, height = 60.dp),
                text = "+")
            //StyledButton(onClick = {counterViewModel.decrement()}, "-")
            HoldableButton(onClick = {counterViewModel.decrement()},
                onHold = {counterViewModel.decrement()},
                modifier = Modifier.size(width = 150.dp, height = 60.dp),
                text = "-")
        }

    }
}

// @Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {
        Greeting("World P")
    }
}

@Composable
fun StyledText1 (message: String){
    Text(
        text = message,
        color = Color(0, 128, 128),
        fontSize = 30.sp,
        fontWeight = FontWeight(500),
        textAlign = TextAlign.Center,
        modifier = Modifier
            //.fillMaxWidth()
            .border(3.dp, Color(0, 128, 128), RoundedCornerShape(15.dp))
            .background(Color(150, 255, 255), RoundedCornerShape(15.dp))
            .padding(16.dp)
        //.border(3.dp, Color.DarkGray, RoundedCornerShape(15.dp))
    )
}

@Composable
fun StyledText2 (message: String){
    Text(
        text = message,
        fontSize = 30.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center,

        modifier = Modifier
            //.fillMaxWidth()
            .border(3.dp, Color.DarkGray, RoundedCornerShape(15.dp))
            .background(Color(200,200,200), RoundedCornerShape(15.dp))
            .padding(16.dp)

    )
}
@Composable
fun StyledButton (onClick: () -> Unit, label: String = ""){
    Button (onClick = onClick,
        modifier = Modifier
            .size(width = 150.dp, height = 60.dp)
    ) {
        Text(label, fontSize = 30.sp)
    }
}

@Composable
fun HoldableButton(
    onClick: () -> Unit,
    onHold: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    text: String
) {
    val scope = rememberCoroutineScope()
    var isHeld by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color(0xFF6200EE), shape = RoundedCornerShape(12.dp)) // optional style
            .pointerInput(onHold) {
                detectTapGestures(
                    onPress = {
                        if (onHold == null) {
                            awaitRelease()
                            onClick()
                            return@detectTapGestures
                        }

                        isHeld = true
                        val job = scope.launch {
                            delay(1000) // Initial delay before holding starts
                            while (isHeld) {
                                onHold()
                                delay(100) // Interval during hold
                            }
                        }
                        try {
                            awaitRelease()
                            onClick()
                        } finally {
                            isHeld = false
                            job.cancel()
                        }
                    }
                )
            }
            .padding(8.dp)
    ) {
        Text(text, fontSize = 30.sp, color = Color.White)
    }
}
/*@Composable
fun HoldableButton (
    onClick: () -> Unit,
    onHold: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    text: String
){
    val scope = rememberCoroutineScope()
    var isHeld by remember { mutableStateOf(false) }

    Box(
        //onClick = onClick,
        modifier = modifier
            .pointerInput(onHold) {
                detectTapGestures(
                    onPress = {
                        if (onHold == null) {
                            awaitRelease()
                            onClick()
                            return@detectTapGestures
                        }

                        isHeld = true
                        val job = scope.launch{
                            delay(500)
                            while (isHeld){
                                onHold()
                                delay(100)
                            }
                        }
                        try {
                            awaitRelease()
                            onClick()
                        } finally {
                            isHeld = false
                            job.cancel()
                        }
                    }
                )
            }
    ) {
        Button(
            onClick = {},
            modifier = Modifier.matchParentSize()

        ) {
            Text(text, fontSize = 30.sp)
        }
    }

}*/