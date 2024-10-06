package com.jetpackcomposeileandroiduyggelistirmekursuudmy.kisileruygtasarim

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcomposeileandroiduyggelistirmekursuudmy.kisileruygtasarim.entity.Kisiler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kisidetaysayfa(gelenkisi:Kisiler){ //nesneleri aldik
    val tfkisiad= remember { mutableStateOf("") }
    val tfkisitel= remember { mutableStateOf("") }
    val localfocusmanager= LocalFocusManager.current

    LaunchedEffect(key1 = true) { //arayuzde nesneleri gostermek icin
        tfkisiad.value=gelenkisi.kisi_ad
        tfkisitel.value=gelenkisi.kisi_tel

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kisi Detay") })
        }

    )
    {paddingValues -> Column(modifier = Modifier.padding(paddingValues)) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = tfkisiad.value, onValueChange ={tfkisiad.value=it} , label = { Text(text = "kisi ad") })

            TextField(value = tfkisitel.value, onValueChange ={tfkisitel.value=it} , label = { Text(text = "kisi tel") })

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
                .height(52.dp),
                onClick = { localfocusmanager.clearFocus() }) {
                Text(text = "Guncelle")

            }

        }
    }

    }



}

