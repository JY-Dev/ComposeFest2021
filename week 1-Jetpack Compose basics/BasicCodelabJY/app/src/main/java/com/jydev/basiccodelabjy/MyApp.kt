package com.jydev.basiccodelabjy

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface(color = MaterialTheme.colors.background) {
        Column {
            if(shouldShowOnboarding){
                OnBoardingScreen {
                    shouldShowOnboarding = false
                }
            } else
                Greetings()
        }
    }
}



