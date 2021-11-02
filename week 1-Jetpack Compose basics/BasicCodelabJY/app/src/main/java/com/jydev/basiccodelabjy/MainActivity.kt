package com.jydev.basiccodelabjy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jydev.basiccodelabjy.ui.theme.BasicCodelabJYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodelabJYTheme {
                MyApp()
            }

        }
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("Jaeyoung", "Kim")) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val explainHeight = if(expanded.value) 48.dp else 0.dp
    Surface(
        color = Color.Black,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column {
            Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                HelloTextColumn(name = name, modifier = Modifier.weight(1f))
                ShowMoreButton(expanded)
            }
            Box(modifier = Modifier.height(explainHeight))
        }
    }
}

@Composable
private fun HelloTextColumn(name : String , modifier: Modifier){
    Column(
        modifier = modifier
    ) {
        WhiteTextView(text = "Hello")
        WhiteTextView(text = name)
    }
}

@Composable
private fun ShowMoreButton(mutableState: MutableState<Boolean>){
    OutlinedButton(
        onClick = { mutableState.value = !mutableState.value },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Yellow,
            contentColor = Color.Black
        ),
    ) {
        BlackTextView(text = if(mutableState.value) "Show less" else "Show More")
    }
}

@Composable
private fun WhiteTextView(text: String) {
    Text(color = Color.White, text = text)
}

@Composable
private fun BlackTextView(text: String) {
    Text(color = Color.Black, text = text)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicCodelabJYTheme {
        MyApp()
    }
}